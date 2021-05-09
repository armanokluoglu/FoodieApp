package model.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private List<IFood> items;
	private String address;
	private String customerName;
	private String restaurantName;
	private Date orderDate;

	public Order() {
		this.items = new ArrayList<>();
		this.address = null;
		this.customerName = null;
		this.restaurantName = null;
		this.orderDate = null;
	}
	
	public Order(String address, String customerName, String restaurantName) {
		this.items = new ArrayList<>();
		this.address = address;
		this.customerName = customerName;
		this.restaurantName = restaurantName;
		this.orderDate = null;
	}
	
	public Order(String address, String customerName, String restaurantName, List<IFood> items, Date orderDate) {
		this.items = items;
		this.address = address;
		this.customerName = customerName;
		this.restaurantName = restaurantName;
		this.orderDate = orderDate;
	}

	public List<IFood> getItems() {
		return items;
	}

	public void setItems(List<IFood> items) {
		this.items = items;
	}
	
	public void addItemToOrder(IFood item) {
		items.add(item);
	}
	
	public void removeItemFromOrder(IFood item) {
		items.remove(item);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setPlaced() {
		this.orderDate = new Date();
	}

	public Date getOrderDate() {
		return orderDate;
	}
}