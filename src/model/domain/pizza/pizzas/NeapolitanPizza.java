package model.domain.pizza.pizzas;

import model.domain.pizza.IPizza;

public class NeapolitanPizza implements IPizza {

	private final double cost = 3.9;
	
	@Override
	public String decorate() {
		return "Neapolitan Pizza";
	}
	
	@Override
	public double getCost() {
		return this.cost;
	}
}
