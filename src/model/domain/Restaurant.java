package model.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.utilities.Observer;

public class Restaurant extends User {

	private List<Menu> menu;
	private List<Observer> observers;
	
	public Restaurant(String name, String username, String password, String address, List<Order> orderHistory, List<Menu> menu) {
		super(name, username, password, address, orderHistory);
		this.menu = menu;
		this.observers = new ArrayList<>();
	}
	public Restaurant(int id,String name, String username, String password, String address, List<Order> orderHistory, List<Menu> menu) {
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

	public IFood createFood(String foodName, List<String> toppings){
		for(Menu menu:menu){
			for (String item : menu.getItems().keySet()) {
				if(menu.getItems().keySet().contains(foodName)){
					FoodFactory factory = FactoryProvider.getFactory(menu.getName().replaceAll(" .*", ""));
					IFood food = (IFood) factory.create(foodName, toppings);
					return food;
				}
			}
		}
		return null;
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