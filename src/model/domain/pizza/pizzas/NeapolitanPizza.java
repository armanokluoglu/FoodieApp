package model.domain.pizza.pizzas;

import model.domain.pizza.IPizza;

public class NeapolitanPizza implements IPizza {

	@Override
	public String decorate() {
		return "Neapolitan Pizza";
	}
}
