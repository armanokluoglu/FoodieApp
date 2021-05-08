package model.domain.pizza.pizza_toppings;

import model.domain.pizza.IPizza;
import model.domain.pizza.PizzaDecorator;

public class Olive extends PizzaDecorator {

    public Olive(IPizza pizza) {
        super(pizza);
    }

    public String decorate() {
        return super.decorate() + decorateWithOlive();
    }

    private String decorateWithOlive() {
        return " with Olive";
    }
}
