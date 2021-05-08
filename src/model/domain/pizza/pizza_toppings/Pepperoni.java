package model.domain.pizza.pizza_toppings;

import model.domain.pizza.IPizza;
import model.domain.pizza.PizzaDecorator;

public class Pepperoni extends PizzaDecorator {
	
	public Pepperoni(IPizza pizza) {
		super(pizza);
	}

	public String decorate() {
		return super.decorate() + decorateWithPepperoni();
	}
	
	private String decorateWithPepperoni() {
		return " with Pepperoni";
	}
}
