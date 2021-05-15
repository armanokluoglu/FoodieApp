package model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.utilities.FoodCostPair;
import model.utilities.Observer;
import model.utilities.ToppingPricePair;

public class Restaurant extends User {

	private List<Menu> menu;
	private List<Observer> observers;

	public Restaurant(String name, String username, String password, String address, List<Order> orderHistory,
			List<Menu> menu) {
		super(name, username, password, address, orderHistory);
		this.menu = menu;
		this.observers = new ArrayList<>();
	}

	public Restaurant(int id, String name, String username, String password, String address, List<Order> orderHistory,
			List<Menu> menu) {
		super(id, name, username, password, address, orderHistory);
		this.menu = menu;
		this.observers = new ArrayList<>();
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
		notifyObservers();
	}

	public void addMenu(Menu newMenu) {
		List<Menu> menu = getMenu();
		menu.add(newMenu);
		setMenu(menu);
	}

	@SuppressWarnings("unused")
	public IFood createFood(String foodName, double cost, List<ToppingPricePair> toppings) {
		for (Menu menu : menu) {
			for (FoodCostPair item : menu.getItems().keySet()) {
				if (menu.getItems().keySet().contains(foodName)) {
					FoodFactory factory = FactoryProvider.getFactory(menu.getName().replaceAll(" .*", ""));
					IFood food = (IFood) factory.create(foodName, cost, toppings);
					return food;
				}
			}
		}
		return null;
	}

	public void createFoodAndAddToMenu(String menuName, String foodName, double cost) {
		for (Menu menu : menu) {
			if (menu.getName().equalsIgnoreCase(menuName)) {
				Map<FoodCostPair, List<ToppingPricePair>> items = menu.getItems();

				FoodFactory factory = FactoryProvider.getFactory(menu.getName().replaceAll(" .*", ""));
				IFood food = (IFood) factory.create(foodName, cost, new ArrayList<>());
				FoodCostPair pair = new FoodCostPair(food);
				
				items.put(pair, new ArrayList<>());
				menu.setItems(items);
				notifyObservers();
			}
		}
	}

	@Override
	public void register(Observer obj) {
		if (obj == null) {
			throw new NullPointerException("The given observer is null.");
		}
		List<Observer> observers = this.observers;
		if (!observers.contains(obj)) {
			observers.add(obj);
			this.observers = observers;
		}
	}

	@Override
	public void unregister(Observer obj) {
		if (obj == null) {
			throw new NullPointerException("The given observer is null.");
		}
		List<Observer> observers = this.observers;
		if (!observers.contains(obj)) {
			observers.remove(obj);
			this.observers = observers;
		}
	}

	@Override
	public void notifyObservers() {
		List<Observer> observers = this.observers;
		for (Observer observer : observers) {
			observer.update();
		}
	}

}