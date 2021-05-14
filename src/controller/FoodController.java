package controller;

import model.domain.*;
import model.utilities.FoodCostPair;
import model.utilities.Observer;
import model.utilities.Subject;
import model.utilities.ToppingPricePair;
import view.FoodFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FoodController implements Observer {

	private SessionManager session;
	private Subject subject;
	private String food;
	private double foodCost;
	private FoodFrame view;
	private List<ToppingPricePair> selectedToppings = new ArrayList<>();

	public FoodController(FoodFrame view, SessionManager session, User restaurant, String food, double foodCost) {
		this.subject = restaurant;
		this.view = view;
		this.session = session;
		this.food = food;
		this.foodCost = foodCost;
		
		restaurant.register(this);
		
		setSidebarListeners();
		setContentListeners();
	}

	private void setSidebarListeners() {
		view.addOpenRestaurantsActionListener(new FoodController.OpenRestaurantsListener());
		view.addOpenUserProfileActionListener(new FoodController.OpenUserProfileListener());
		view.addLogoutActionListener(new FoodController.LogoutListener());
		view.addOpenShoppingCartActionListener(new FoodController.OpenShoppingCartListener());

	}

	private void setContentListeners() {
		Restaurant restaurant = (Restaurant) subject;
		List<ToppingPricePair> toppings = new ArrayList<>();
		List<Menu> menus = restaurant.getMenu();
		for (Menu menu : menus) {
			Map<FoodCostPair, List<ToppingPricePair>> items = menu.getItems();
			for (FoodCostPair item : items.keySet()) {
				if (item.getFood().equals(food)) {
					toppings = items.get(item);
				}
			}
		}
		
		for (ToppingPricePair topping : toppings) {
			view.addSelectToppingActionListener(new SelectToppingActionListener(topping), topping.getTopping());
			view.addUnSelectToppingActionListener(new UnSelectToppingActionListener(topping), topping.getTopping());
		}
		view.addAddToCartActionListener(new AddToCartActionListener(food, foodCost,selectedToppings, (User) subject));
	}

	class AddToCartActionListener implements ActionListener {
		public String foodName;
		public double foodCost;
		public List<ToppingPricePair> selectedToppings;
		public User restaurant;

		public AddToCartActionListener(String foodName, double foodCost, List<ToppingPricePair> selectedToppings, User restaurant) {
			this.foodName = foodName;
			this.foodCost = foodCost;
			this.selectedToppings = selectedToppings;
			this.restaurant = restaurant;
		}

		public void actionPerformed(ActionEvent e) {
			IFood cartFood = ((Restaurant) restaurant).createFood(foodName, foodCost, selectedToppings);
			((Customer) session.getCurrentUser()).addItemToOrder(cartFood);
			session.restaurantPage(restaurant);
		}
	}

	class SelectToppingActionListener implements ActionListener {
		public ToppingPricePair topping;

		public SelectToppingActionListener(ToppingPricePair topping) {
			this.topping = topping;

		}

		public void actionPerformed(ActionEvent e) {
			selectedToppings.add(topping);
		}
	}

	class UnSelectToppingActionListener implements ActionListener {
		public ToppingPricePair topping;

		public UnSelectToppingActionListener(ToppingPricePair topping) {
			this.topping = topping;

		}

		public void actionPerformed(ActionEvent e) {
			if (selectedToppings.contains(topping))
				selectedToppings.remove(topping);
		}
	}

	class OpenShoppingCartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			session.shoppingCartPage();
		}
	}

	class OpenRestaurantsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			session.restaurantsPage();
		}
	}

	class LogoutListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			session.loginPage();
		}
	}

	class OpenUserProfileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			session.userProfilePage();
		}
	}

	@Override
	public void update() {
		setContentListeners();
	}

	@Override
	public void addSubject(Subject sub) {
		this.subject = sub;
	}

	@Override
	public void removeSubject(Subject sub) {
		this.subject = null;
	}
}
