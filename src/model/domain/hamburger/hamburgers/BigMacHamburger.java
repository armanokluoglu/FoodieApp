package model.domain.hamburger.hamburgers;

import model.domain.hamburger.IHamburger;

public class BigMacHamburger implements IHamburger {

    @Override
    public String decorate() {
        return "Big Mac Hamburger";
    }
}
