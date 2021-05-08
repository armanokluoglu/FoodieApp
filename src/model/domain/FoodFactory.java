package model.domain;

import java.util.List;

public interface FoodFactory<T extends Food> {

	public T create(String type, List<String> toppings);
}
