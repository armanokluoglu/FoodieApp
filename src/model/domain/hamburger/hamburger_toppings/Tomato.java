package model.domain.hamburger.hamburger_toppings;

import model.domain.hamburger.HamburgerDecorator;
import model.domain.hamburger.IHamburger;

public class Tomato  extends HamburgerDecorator {

    public Tomato(IHamburger hamburger) {
        super(hamburger);
    }
    public String decorate() {
        return super.decorate() + decorateWithTomato();
    }

    private String decorateWithTomato() {
        return " with Tomato";
    }
}
