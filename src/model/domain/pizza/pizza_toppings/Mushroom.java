package model.domain.pizza.pizza_toppings;

import model.domain.pizza.IPizza;
import model.domain.pizza.PizzaDecorator;

public class Mushroom extends PizzaDecorator {

    public Mushroom(IPizza pizza) {
        super(pizza);
    }

    public String decorate() {
        return super.decorate() + decorateWithMushroom();
    }

    private String decorateWithMushroom() {
        return " with Mushroom";
    }
}
