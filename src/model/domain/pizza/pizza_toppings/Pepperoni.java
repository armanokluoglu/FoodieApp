package model.domain.pizza.pizza_toppings;

import model.domain.pizza.IPizza;
import model.domain.pizza.PizzaDecorator;

public class Pepperoni extends PizzaDecorator {
	
	private final double cost = 1.5;
	
	public Pepperoni(IPizza pizza) {
		super(pizza);
	}
	
	private String decorateWithPepperoni() {
		return " + Pepperoni";
	}
	
	@Override
	public String decorate() {
		return super.decorate() + decorateWithPepperoni();
	}
	
	@Override
	public double getCost() {
		return super.getCost() + cost;
	}
}