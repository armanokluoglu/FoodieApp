package model.domain.hamburger.hamburgers;

import model.domain.hamburger.IHamburger;

public class VeggieBurger implements IHamburger {
	private double cost = 4;
	
	@Override
	public String decorate() {
		return "Veggie Burger";
	}
	
	@Override
	public double getCost() {
		return this.cost;
	}
}
