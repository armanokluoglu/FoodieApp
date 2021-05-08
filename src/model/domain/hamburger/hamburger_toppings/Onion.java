package model.domain.hamburger.hamburger_toppings;

import model.domain.hamburger.HamburgerDecorator;
import model.domain.hamburger.IHamburger;

public class Onion extends HamburgerDecorator {

    public Onion(IHamburger hamburger) {
        super(hamburger);
    }
    public String decorate() {
        return super.decorate() + decorateWithOnion();
    }

    private String decorateWithOnion() {
        return " with Onion";
    }
}
