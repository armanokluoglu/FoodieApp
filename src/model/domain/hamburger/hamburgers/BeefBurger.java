package model.domain.hamburger.hamburgers;

import model.domain.hamburger.IHamburger;

public class BeefBurger implements IHamburger {
	private double cost = 4;
	
	@Override
	public String decorate() {
		return "Beef Burger";
	}
	
	@Override
	public double getCost() {
		return this.cost;
	}
}
