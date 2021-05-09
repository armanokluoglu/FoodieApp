package model.domain;

import java.util.List;
import java.util.Map;

public class Menu {

	private String name;
	private Map<String, List<String>> items;
	
	public Menu(String name, Map<String, List<String>> items) {
		this.name = name;
		this.items = items;
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
