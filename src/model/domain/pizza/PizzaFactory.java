package model.domain.pizza;

import java.util.List;

import model.domain.FoodFactory;
import model.domain.pizza.pizza_toppings.*;
import model.domain.pizza.pizzas.CalifornianPizza;
import model.domain.pizza.pizzas.NeapolitanPizza;
import model.domain.pizza.pizzas.NewYorkStylePizza;
import model.domain.pizza.pizzas.SicilianPizza;
import model.utilities.ToppingPricePair;

public class PizzaFactory implements FoodFactory {

	@Override
	public IPizza create(String type, double pizzaCost, List<ToppingPricePair> toppings) {
		IPizza pizza = null;
		if (type.equalsIgnoreCase("neapolitan")) {
			pizza = new NeapolitanPizza(pizzaCost);
		}
		else if (type.equalsIgnoreCase("californian")) {
			pizza = new CalifornianPizza(pizzaCost);
		}
		else if (type.equalsIgnoreCase("newyorkstyle")) {
			pizza = new NewYorkStylePizza(pizzaCost);
		}
		else if (type.equalsIgnoreCase("sicilian")) {
			pizza = new SicilianPizza(pizzaCost);
		}
		for (ToppingPricePair topping : toppings) {
			if (topping.getTopping().equalsIgnoreCase("pepperoni")) {
				pizza = new Pepperoni(pizza,topping.getCost());
			}
			else if (topping.getTopping().equalsIgnoreCase("mozzarella")) {
				pizza = new Mozzarella(pizza,topping.getCost());
			}
			else if (topping.getTopping().equalsIgnoreCase("corn")) {
				pizza = new Corn(pizza,topping.getCost());
			}
			else if (topping.getTopping().equalsIgnoreCase("mushroom")) {
				pizza = new Mushroom(pizza,topping.getCost());
			}
			else if (topping.getTopping().equalsIgnoreCase("olive")) {
				pizza = new Olive(pizza,topping.getCost());
			}
		}
		
		return pizza;
	}
}
