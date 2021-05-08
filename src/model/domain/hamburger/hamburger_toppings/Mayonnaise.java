package model.domain.hamburger.hamburger_toppings;

import model.domain.hamburger.HamburgerDecorator;
import model.domain.hamburger.IHamburger;

public class Mayonnaise extends HamburgerDecorator {

    public Mayonnaise(IHamburger hamburger) {
        super(hamburger);
    }
    public String decorate() {
        return super.decorate() + decorateWithMayonnaise();
    }

    private String decorateWithMayonnaise() {
        return " with Mayonnaise";
    }
}
