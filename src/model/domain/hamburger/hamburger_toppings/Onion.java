package model.domain.hamburger.hamburger_toppings;

import model.domain.hamburger.HamburgerDecorator;
import model.domain.hamburger.IHamburger;

public class Onion extends HamburgerDecorator {

	private final double cost = 0.7;
	
    public Onion(IHamburger hamburger) {
        super(hamburger);
    }

    private String decorateWithOnion() {
        return " + Onion";
    }
    
    @Override
    public String decorate() {
        return super.decorate() + decorateWithOnion();
    }
    
    @Override
    public double getCost() {
		return super.getCost() + cost;
	}
}
