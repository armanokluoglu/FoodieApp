package model.domain.pizza.pizza_toppings;

import model.domain.pizza.IPizza;
import model.domain.pizza.PizzaDecorator;

public class Corn extends PizzaDecorator {

	private final double cost = 0.5;
	
    public Corn(IPizza pizza) {
        super(pizza);
    }

    private String decorateWithCorn() {
        return " + Corn";
    }
    
    @Override
    public String decorate() {
        return super.decorate() + decorateWithCorn();
    }
    
    @Override
    public double getCost() {
		return super.getCost() + cost;
	}
}