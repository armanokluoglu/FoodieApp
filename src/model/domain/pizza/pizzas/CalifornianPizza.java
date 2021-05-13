package model.domain.pizza.pizzas;

import model.domain.pizza.IPizza;

public class CalifornianPizza implements IPizza {

	private final double cost = 4.5;

	@Override
    public String decorate() {
        return "Californian Pizza";
    }
	
	@Override
	public double getCost() {
		return this.cost;
	}


}
