package model.domain.pizza.pizza_toppings;

import model.domain.pizza.IPizza;
import model.domain.pizza.PizzaDecorator;

public class Mozzarella extends PizzaDecorator {

    public Mozzarella(IPizza pizza) {
        super(pizza);
    }

    public String decorate() {
        return super.decorate() + decorateWithMozzarella();
    }

    private String decorateWithMozzarella() {
        return " with Mozzarella";
    }
}
