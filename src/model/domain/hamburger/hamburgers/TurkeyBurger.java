package model.domain.hamburger.hamburgers;

import model.domain.hamburger.IHamburger;

public class TurkeyBurger implements IHamburger {
	private double cost = 3;
	
	@Override
	public String decorate() {
		return "Turkey Burger";
	}
	
	@Override
	public double getCost() {
		return this.cost;
	}
}
