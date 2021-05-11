package app;

import java.util.*;

import model.data_access.InputOutput;
import model.domain.*;
import model.utilities.FoodCostPair;

public class Main {
	public static void main(String[] args) {
		FoodFactory factory = FactoryProvider.getFactory("pizza");
		FoodFactory factory1 = FactoryProvider.getFactory("hamburger");

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


		Map<String, List<String>> pizzaMenu1 = new HashMap<>();
		Map<String, List<String>> burgerMenu1 = new HashMap<>();

		pizzaMenu1.put("neapolitan",Arrays.asList("pepperoni","mozzarella","mushroom","olive","corn"));
		pizzaMenu1.put("californian", Arrays.asList("pepperoni","mozzarella","mushroom","olive","corn"));
		burgerMenu1.put("bigmac", Arrays.asList("mayonnaise","cheese","pickles","lettuce","onion","tomato"));
		burgerMenu1.put("turkey", Arrays.asList("mayonnaise","cheese","pickles","lettuce","onion","tomato"));

		Menu menu1 = new Menu("Pizza Menu",pizzaMenu1);
		Menu menu2 = new Menu("Burger Menu",burgerMenu1);

		User restaurant = new Restaurant("Calipso","calipso","1234","gülbahçe",new ArrayList<>(), Arrays.asList(menu1,menu2));
		User restaurant2 = new Restaurant("Calipso2","calipso2","1234","gülbahçe",new ArrayList<>(), Arrays.asList(menu1));
		User customer = new Customer("customer","3535","1234","gülbahçe2",new ArrayList<>());
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

		List<User> users = new ArrayList<>();
		users.add(restaurant);
		users.add(restaurant2);
		users.add(customer);
		users.add(customer2);

		InputOutput io = new InputOutput();
		io.outputUsers(users);
		io.inputUsers();

		System.out.println(pizza1.decorate());
		System.out.println(pizza1.getCost());
		
		System.out.println(pizza2.decorate());
		System.out.println(pizza2.getCost());
		
		System.out.println(pizza3.decorate());
		System.out.println(pizza3.getCost());
		
		System.out.println(hamburger1.decorate());
		System.out.println(hamburger1.getCost());
		
		System.out.println(hamburger2.decorate());
		System.out.println(hamburger2.getCost());
	}
}
