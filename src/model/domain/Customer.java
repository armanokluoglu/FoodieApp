package model.domain;

import model.utilities.FoodCostPair;

import java.util.List;

public class Customer extends User {
	
	private Order currentOrder;

	public Customer(String name, String username, String password, String address, List<Order> orderHistory) {
		super(name, username, password, address, orderHistory);
	}
	public Customer(int id, String name, String username, String password, String address, List<Order> orderHistory) {
		super(id, name, username, password, address, orderHistory);
	}
	public void initializeOrder(String restaurantName) {
		this.currentOrder = new Order(getAddress(), getName(), restaurantName);
	}
	public void setCurrentOrder(Order order) {
		this.currentOrder = order;
	}
	public Order getCurrentOrder() {
		return currentOrder;
	}
	
	public void addItemToOrder(IFood food) {
		currentOrder.addItemToOrder(new FoodCostPair(food));
	}
	
	public void removeItemFromOrder(IFood food) {
		currentOrder.removeItemFromOrder(food);
	}
	
	public void placeOrder() {
		currentOrder.setPlaced();
		getOrderHistory().add(currentOrder);
	}
}