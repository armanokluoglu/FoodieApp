package model.domain;

import java.util.List;

public interface FoodFactory {

	public IFood create(String type, List<String> toppings);
}
