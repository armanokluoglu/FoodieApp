package model.domain.pizza;

import java.util.List;

import model.domain.FoodFactory;
import model.domain.pizza.pizza_toppings.*;
import model.domain.pizza.pizzas.CalifornianPizza;
import model.domain.pizza.pizzas.NeapolitanPizza;
import model.domain.pizza.pizzas.NewYorkStylePizza;
import model.domain.pizza.pizzas.SicilianPizza;

public class PizzaFactory implements FoodFactory<IPizza> {

	@Override
	public IPizza create(String type, List<String> toppings) {
		IPizza pizza = null;
		if (type.equalsIgnoreCase("neapolitan")) {
			pizza = new NeapolitanPizza();
		}
		else if (type.equalsIgnoreCase("californian")) {
			pizza = new CalifornianPizza();
		}
		else if (type.equalsIgnoreCase("newyorkstyle")) {
			pizza = new NewYorkStylePizza();
		}
		else if (type.equalsIgnoreCase("sicilian")) {
			pizza = new SicilianPizza();
		}
		for (String string : toppings) {
			if (string.equalsIgnoreCase("pepperoni")) {
				pizza = new Pepperoni(pizza);
			}
			else if (string.equalsIgnoreCase("mozzarella")) {
				pizza = new Mozzarella(pizza);
			}
			else if (string.equalsIgnoreCase("corn")) {
				pizza = new Corn(pizza);
			}
			else if (string.equalsIgnoreCase("mushroom")) {
				pizza = new Mushroom(pizza);
			}
			else if (string.equalsIgnoreCase("olive")) {
				pizza = new Olive(pizza);
			}
		}
		
		return pizza;
	}
}
