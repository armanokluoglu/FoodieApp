package model.domain;

import java.util.List;

public abstract class User {
	private String name;
	private String username;
	private String password;
	private String address;
	private List<Order> orderHistory;

	public User(String name, String username, String password, String address, List<Order> orderHistory) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.orderHistory = orderHistory;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Order> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(List<Order> orderHistory) {
		this.orderHistory = orderHistory;
	}
}