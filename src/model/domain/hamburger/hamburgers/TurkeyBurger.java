package model.domain.hamburger.hamburgers;

import model.domain.hamburger.IHamburger;

public class TurkeyBurger implements IHamburger {
    @Override
    public String decorate() {
        return "Turkey Burger";
    }

}
