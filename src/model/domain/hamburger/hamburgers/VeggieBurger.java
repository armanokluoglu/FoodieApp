package model.domain.hamburger.hamburgers;

import model.domain.hamburger.IHamburger;

public class VeggieBurger implements IHamburger {
    @Override
    public String decorate() {
        return "Veggie Burger";
    }

}
