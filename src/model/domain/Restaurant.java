package model.domain;

import java.util.List;

public class Restaurant extends User {

	private List<Menu> menu;
	
	public Restaurant(String name, String username, String password, String address, List<Order> orderHistory, List<Menu> menu) {
		super(name, username, password, address, orderHistory);
		this.menu = menu;
	}
	public Restaurant(int id,String name, String username, String password, String address, List<Order> orderHistory, List<Menu> menu) {
		super(id, name, username, password, address, orderHistory);
		this.menu = menu;
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}
	
}