package model.data_access;

import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.domain.Customer;
import model.domain.FactoryProvider;
import model.domain.FoodFactory;
import model.domain.IFood;
import model.domain.Menu;
import model.domain.Order;
import model.domain.Restaurant;
import model.domain.User;
import model.utilities.FoodCostPair;
import model.utilities.ToppingPricePair;

public class Repository {

	private List<User> restaurants;
	private List<User> customers;
	
	public Repository(IO io) {
		FoodFactory factory = FactoryProvider.getFactory("pizza");
		FoodFactory factory1 = FactoryProvider.getFactory("burger");

		IFood pizza1 = (IFood) factory.create("neapolitan", Arrays.asList("pepperoni","mozzarella","mushroom","olive","corn"));
		IFood pizza2 = (IFood) factory.create("californian", Arrays.asList("pepperoni","mozzarella","mushroom","olive","corn"));
		IFood pizza3 = (IFood) factory.create("newyorkstyle", Arrays.asList("pepperoni","mozzarella","mushroom","olive","corn"));

		IFood hamburger1 = (IFood) factory1.create("bigmac", Arrays.asList("mayonnaise","cheese","pickles","lettuce","onion","tomato"));
		IFood hamburger2 = (IFood) factory1.create("turkey", Arrays.asList("mayonnaise","cheese","pickles","lettuce","onion","tomato"));

		FoodCostPair pizza1P = new FoodCostPair(pizza1);
		FoodCostPair pizza2P = new FoodCostPair(pizza2);
		FoodCostPair pizza3P = new FoodCostPair(pizza3);
		FoodCostPair hamburger1P = new FoodCostPair(hamburger1);
		FoodCostPair hamburger2P = new FoodCostPair(hamburger2);

		Map<String, List<ToppingPricePair>> pizzaMenu1 = new HashMap<>();
		Map<String, List<ToppingPricePair>> burgerMenu1 = new HashMap<>();

		ToppingPricePair toppingPricePair0 = new ToppingPricePair(0.2,"pepperoni");
		ToppingPricePair toppingPricePair1 = new ToppingPricePair(0.3,"mushroom");
		ToppingPricePair toppingPricePair2 = new ToppingPricePair(0.1,"olive");
		ToppingPricePair toppingPricePair3 = new ToppingPricePair(0.6,"corn");
		ToppingPricePair toppingPricePair4 = new ToppingPricePair(0.2,"mayonnaise");
		ToppingPricePair toppingPricePair5 = new ToppingPricePair(0.3,"cheese");
		ToppingPricePair toppingPricePair6 = new ToppingPricePair(0.1,"pickles");
		ToppingPricePair toppingPricePair7 = new ToppingPricePair(0.4,"lettuce");
		ToppingPricePair toppingPricePair8 = new ToppingPricePair(0.3,"onion");
		ToppingPricePair toppingPricePair9 = new ToppingPricePair(0.2,"tomato");
		ToppingPricePair toppingPricePair10 = new ToppingPricePair(0.2,"mozzarella");

		pizzaMenu1.put("neapolitan",Arrays.asList(toppingPricePair0,toppingPricePair10,toppingPricePair1,toppingPricePair2,toppingPricePair3));
		pizzaMenu1.put("californian", Arrays.asList(toppingPricePair0,toppingPricePair10,toppingPricePair1,toppingPricePair2,toppingPricePair3));
		burgerMenu1.put("bigmac", Arrays.asList(toppingPricePair4,toppingPricePair5,toppingPricePair6,toppingPricePair7,toppingPricePair8,toppingPricePair9));
		burgerMenu1.put("turkey", Arrays.asList(toppingPricePair4,toppingPricePair5,toppingPricePair6,toppingPricePair7,toppingPricePair8,toppingPricePair9));

		Menu menu1 = new Menu("Pizza Menu",pizzaMenu1);
		Menu menu2 = new Menu("Burger Menu",burgerMenu1);

		User restaurant = new Restaurant("Calipso","calipso","1234","gülbahçe",new ArrayList<>(), Arrays.asList(menu1,menu2));
		User restaurant2 = new Restaurant("Calipso2","calipso2","1234","gülbahçe",new ArrayList<>(), Arrays.asList(menu1));
		
		User customer = new Customer("Arman Okluoğlu","armanokluoglu","1234","Adres falan filan",new ArrayList<>());
		User customer2 = new Customer("customer2","3535","1234","gülbahçe23",new ArrayList<>());

		Order order = new Order("adress","customer1","calipso",Arrays.asList(hamburger1P,pizza1P),new Date());
		Order order2 = new Order("adress","customer2","calipso",Arrays.asList(hamburger2P,pizza1P),new Date());
		Order order3 = new Order("adress","customer3","calipso",Arrays.asList(hamburger1P,pizza2P),new Date());
		Order order4 = new Order("adress","customer4","calipso",Arrays.asList(hamburger1P,pizza2P,pizza1P),new Date());
		Order order5 = new Order("adress","customer5","calipso",Arrays.asList(hamburger2P,pizza3P),new Date());

		restaurant.addOrder(order);
		restaurant.addOrder(order2);
		restaurant.addOrder(order3);
		restaurant.addOrder(order4);
		restaurant.addOrder(order5);

		customer.addOrder(order);
		customer.addOrder(order2);
		customer2.addOrder(order3);
		customer2.addOrder(order4);
		((Customer)customer2).initializeOrder("Calipso2");
		((Customer)customer2).addItemToOrder(hamburger2);
		
		List<User> restaurants = new ArrayList<>();
		restaurants.add(restaurant);
		restaurants.add(restaurant2);
		this.restaurants = restaurants;
		
		List<User> customers = new ArrayList<>();
		customers.add(customer);
		customers.add(customer2);
		List<User> all = new ArrayList<>();
		all.addAll(restaurants);all.addAll(customers);
		io.outputUsers(all);
		io.inputUsers();
		this.customers = customers;
	}

	public User findUserByUsername(String username) throws IllegalStateException {
		for (User user : getAllUsers()) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		throw new IllegalStateException("No user with the given username exists.");
	}

	public List<User> getAllRestaurants() {
		return restaurants;
	}
	
	public List<User> getAllCustomers() {
		return customers;
	}
	
	public List<User> getAllUsers() {
		List<User> allUsers = new ArrayList<>();
		allUsers.addAll(restaurants);
		allUsers.addAll(customers);
		return allUsers;
	}

	public void setRestaurants(List<User> restaurants) {
		this.restaurants = restaurants;
	}

}
