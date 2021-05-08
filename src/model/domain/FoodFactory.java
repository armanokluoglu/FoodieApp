package model.domain;

import java.util.List;

public interface FoodFactory<T> {

	public T create(String type, List<String> toppings);
}
