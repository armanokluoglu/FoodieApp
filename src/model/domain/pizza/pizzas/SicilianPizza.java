package model.domain.pizza.pizzas;

import model.domain.pizza.IPizza;

public class SicilianPizza  implements IPizza {

    @Override
    public String decorate() {
        return "Sicilian Pizza";
    }
}
