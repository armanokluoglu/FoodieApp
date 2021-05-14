package model.domain;

import model.utilities.ToppingPricePair;

import java.util.List;
import java.util.Map;

public class Menu {

	private static int idCounter=0;
	private int id;
	private String name;
	private Map<String, List<ToppingPricePair>> items;
	
	public Menu(String name, Map<String, List<ToppingPricePair>> items) {
		this.id=idCounter;
		idCounter++;
		this.name = name;
		this.items = items;
	}
	public Menu(int id,String name, Map<String, List<ToppingPricePair>> items) {
		this.id=id;
		this.name = name;
		this.items = items;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, List<ToppingPricePair>> getItems() {
		return items;
	}

	public void setItems(Map<String, List<ToppingPricePair>> items) {
		this.items = items;
	}
}
