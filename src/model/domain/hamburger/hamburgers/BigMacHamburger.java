package model.domain.hamburger.hamburgers;

import model.domain.hamburger.IHamburger;

public class BigMacHamburger implements IHamburger {
	private double cost = 3.5;
	
	@Override
	public String decorate() {
		return "Big Mac Hamburger";
	}
	
	@Override
	public double getCost() {
		return this.cost;
	}
}
