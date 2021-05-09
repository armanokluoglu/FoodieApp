package model.domain.hamburger.hamburger_toppings;

import model.domain.hamburger.HamburgerDecorator;
import model.domain.hamburger.IHamburger;

public class Mayonnaise extends HamburgerDecorator {

	private final double cost = 0.2;
	
    public Mayonnaise(IHamburger hamburger) {
        super(hamburger);
    }

    private String decorateWithMayonnaise() {
        return " + Mayonnaise";
    }
    
    @Override
    public String decorate() {
        return super.decorate() + decorateWithMayonnaise();
    }
    
    @Override
    public double getCost() {
		return super.getCost() + cost;
	}
}
