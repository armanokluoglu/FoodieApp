package model.domain.hamburger;

import model.domain.FoodFactory;
import model.domain.hamburger.hamburger_toppings.*;
import model.domain.hamburger.hamburgers.BeefBurger;
import model.domain.hamburger.hamburgers.BigMacHamburger;
import model.domain.hamburger.hamburgers.TurkeyBurger;
import model.domain.hamburger.hamburgers.VeggieBurger;

import java.util.List;

public class HamburgerFactory implements FoodFactory<IHamburger> {

    @Override
    public IHamburger create(String type, List<String> toppings) {
        IHamburger hamburger = null;
        if (type.equalsIgnoreCase("bigmac")) {
            hamburger = new BigMacHamburger();
        }
        else if (type.equalsIgnoreCase("beef")) {
            hamburger = new BeefBurger();
        }
        else if (type.equalsIgnoreCase("turkey")) {
            hamburger = new TurkeyBurger();
        }
        else if (type.equalsIgnoreCase("veggie")) {
            hamburger = new VeggieBurger();
        }
        for (String string : toppings) {
            if (string.equalsIgnoreCase("mayonnaise")) {
                hamburger = new Mayonnaise(hamburger);
            }
            else if (string.equalsIgnoreCase("cheese")) {
                hamburger = new Cheese(hamburger);
            }
            else if (string.equalsIgnoreCase("cucumber")) {
                hamburger = new Cucumber(hamburger);
            }
            else if (string.equalsIgnoreCase("lettuce")) {
                hamburger = new Lettuce(hamburger);
            }
            else if (string.equalsIgnoreCase("onion")) {
                hamburger = new Onion(hamburger);
            }
            else if (string.equalsIgnoreCase("tomato")) {
                hamburger = new Tomato(hamburger);
            }
        }
        return hamburger;
    }
}
