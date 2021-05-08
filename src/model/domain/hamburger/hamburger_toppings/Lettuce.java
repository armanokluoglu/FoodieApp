package model.domain.hamburger.hamburger_toppings;

import model.domain.hamburger.HamburgerDecorator;
import model.domain.hamburger.IHamburger;

public class Lettuce extends HamburgerDecorator {

    public Lettuce(IHamburger hamburger) {
        super(hamburger);
    }
    public String decorate() {
        return super.decorate() + decorateWithLettuce();
    }

    private String decorateWithLettuce() {
        return " with Lettuces";
    }
}
