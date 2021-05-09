package model.domain.pizza.pizzas;

import model.domain.pizza.IPizza;

public class NewYorkStylePizza implements IPizza {

	private final double cost = 4.5;
	
	@Override
    public String decorate() {
        return "New York Style Pizza";
    }
	
	@Override
	public double getCost() {
		return this.cost;
	}
}
