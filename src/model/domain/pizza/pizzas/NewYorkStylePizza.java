package model.domain.pizza.pizzas;

import model.domain.pizza.IPizza;

public class NewYorkStylePizza implements IPizza {

    @Override
    public String decorate() {
        return "New York Style Pizza";
    }
}
