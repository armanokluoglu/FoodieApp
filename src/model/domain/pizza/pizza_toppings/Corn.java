package model.domain.pizza.pizza_toppings;

import model.domain.pizza.IPizza;
import model.domain.pizza.PizzaDecorator;

public class Corn extends PizzaDecorator {

    public Corn(IPizza pizza) {
        super(pizza);
    }

    public String decorate() {
        return super.decorate() + decorateWithCorn();
    }

    private String decorateWithCorn() {
        return " with Corn";
    }
}
