package model.domain.pizza;

import java.util.List;

import model.domain.FoodFactory;
import model.domain.pizza.pizza_toppings.Pepperoni;

public class PizzaFactory implements FoodFactory<IPizza> {

	@Override
	public IPizza create(String type, List<String> toppings) {
		IPizza pizza = null;
		if (type.equalsIgnoreCase("neapolitan")) {
			pizza = new NeapolitanPizza();
		}
		
		for (String string : toppings) {
			if (string.equalsIgnoreCase("pepperoni")) {
				pizza = new Pepperoni(pizza);
			}
		}
		
		return pizza;
	}
}
