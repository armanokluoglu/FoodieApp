package model.domain;

import java.util.List;
import java.util.Map;

public class Menu {

	private static int idCounter=0;
	private int id;
	private String name;
	private Map<String, List<String>> items;
	
	public Menu(String name, Map<String, List<String>> items) {
		this.id=idCounter;
		idCounter++;
		this.name = name;
		this.items = items;
	}
	public Menu(int id,String name, Map<String, List<String>> items) {
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

	public Map<String, List<String>> getItems() {
		return items;
	}

	public void setItems(Map<String, List<String>> items) {
		this.items = items;
	}
}
