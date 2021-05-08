package model.domain.pizza.pizzas;

import model.domain.pizza.IPizza;

public class CalifornianPizza implements IPizza {

    @Override
    public String decorate() {
        return "Californian Pizza";
    }
}
