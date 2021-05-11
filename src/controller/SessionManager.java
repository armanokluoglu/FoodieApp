package controller;

import model.domain.FoodieService;
import model.domain.IFood;
import model.domain.Restaurant;
import model.domain.User;
import view.*;

@SuppressWarnings("unused")
public class SessionManager {
	private User currentUser;
	private FrameManager fm;
	private FoodieService model;

	public SessionManager(User currentUser, FrameManager fm, FoodieService model) {
		this.setCurrentUser(currentUser);
		this.fm = fm;
		this.model = model;
	}

	public void loginPage() {
		LoginFrame loginView = new LoginFrame(fm);
		LoginController loginController = new LoginController(model, loginView, this);
	}

	public void restaurantsPage() {
		RestaurantsFrame restaurantsView = new RestaurantsFrame(model, fm);
		RestaurantsController restaurantsController = new RestaurantsController(model, restaurantsView, this);
	}

	public void userProfilePage() {
		UserProfileFrame userView = new UserProfileFrame(fm, currentUser);
		UserProfileController userController = new UserProfileController(model, userView, this);
	}

	public void restaurantProfilePage() {
		RestaurantProfileFrame restaurantProfileView = new RestaurantProfileFrame(model, fm, currentUser);
		RestaurantProfileController restaurantProfileController = new RestaurantProfileController(model, restaurantProfileView, this);
	}

	public void restaurantPage(User restaurant) {
		RestaurantFrame collectionView = new RestaurantFrame(fm, currentUser, restaurant);
		RestaurantController collectionController = new RestaurantController(model, collectionView, this, restaurant);
	}

	public void foodPage(IFood food) {
		FoodFrame foodView = new FoodFrame(fm, food, currentUser);
		FoodController foodController = new FoodController(model, foodView, this, food);
	}

	public void shoppingCartPage() {
		ShoppingCartFrame shoppingCartView = new ShoppingCartFrame(fm, model);
		ShoppingCartController shoppingCartControler = new ShoppingCartController(shoppingCartView, this);
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
}