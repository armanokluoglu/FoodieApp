package model.domain.pizza.pizza_toppings;

import model.domain.pizza.IPizza;
import model.domain.pizza.PizzaDecorator;

public class Mozzarella extends PizzaDecorator {

	private final double cost = 0.8;
	
    public Mozzarella(IPizza pizza) {
        super(pizza);
    }
    
    private String decorateWithMozzarella() {
        return " + Mozzarella";
    }
    
    @Override
    public String decorate() {
        return super.decorate() + decorateWithMozzarella();
    }
    
    @Override
    public double getCost() {
    	return super.getCost() + cost;
	}
}
