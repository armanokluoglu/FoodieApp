package model.domain.hamburger.hamburger_toppings;

import model.domain.hamburger.HamburgerDecorator;
import model.domain.hamburger.IHamburger;

public class Cheese extends HamburgerDecorator {

    public Cheese(IHamburger hamburger) {
        super(hamburger);
    }
    public String decorate() {
        return super.decorate() + decorateWithCheese();
    }

    private String decorateWithCheese() {
        return " with Cheese";
    }
}
