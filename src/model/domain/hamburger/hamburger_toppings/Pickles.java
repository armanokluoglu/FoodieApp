package model.domain.hamburger.hamburger_toppings;

import model.domain.hamburger.HamburgerDecorator;
import model.domain.hamburger.IHamburger;

public class Pickles extends HamburgerDecorator {
	
	private final double cost = 0.4;

    public Pickles(IHamburger hamburger) {
        super(hamburger);
    }
    
    private String decorateWithPickles() {
        return " + Pickles";
    }
    
    @Override
    public String decorate() {
        return super.decorate() + decorateWithPickles();
    }
    
    @Override
    public double getCost() {
		return super.getCost() + cost;
	}
}
