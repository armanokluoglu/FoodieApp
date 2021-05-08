package model.domain.hamburger.hamburgers;

import model.domain.hamburger.IHamburger;

public class BeefBurger implements IHamburger {
    @Override
    public String decorate() {
        return "Beef Burger";
    }

}
