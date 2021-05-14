package model.domain.hamburger;

import model.domain.FoodFactory;
import model.domain.hamburger.hamburger_toppings.*;
import model.domain.hamburger.hamburgers.BeefBurger;
import model.domain.hamburger.hamburgers.BigMacHamburger;
import model.domain.hamburger.hamburgers.TurkeyBurger;
import model.domain.hamburger.hamburgers.VeggieBurger;
import model.utilities.ToppingPricePair;

import java.util.List;

public class HamburgerFactory implements FoodFactory {

    @Override
    public IHamburger create(String type, double burgerCost, List<ToppingPricePair> toppings) {
        IHamburger hamburger = null;
        if (type.equalsIgnoreCase("bigmac")) {
            hamburger = new BigMacHamburger(burgerCost);
        }
        else if (type.equalsIgnoreCase("beef")) {
            hamburger = new BeefBurger(burgerCost);
        }
        else if (type.equalsIgnoreCase("turkey")) {
            hamburger = new TurkeyBurger(burgerCost);
        }
        else if (type.equalsIgnoreCase("veggie")) {
            hamburger = new VeggieBurger(burgerCost);
        }
        for (ToppingPricePair topping : toppings) {
            if (topping.getTopping().equalsIgnoreCase("mayonnaise")) {
                hamburger = new Mayonnaise(hamburger,topping.getCost());
            }
            else if (topping.getTopping().equalsIgnoreCase("cheese")) {
                hamburger = new Cheese(hamburger,topping.getCost());
            }
            else if (topping.getTopping().equalsIgnoreCase("pickles")) {
                hamburger = new Pickles(hamburger,topping.getCost());
            }
            else if (topping.getTopping().equalsIgnoreCase("lettuce")) {
                hamburger = new Lettuce(hamburger,topping.getCost());
            }
            else if (topping.getTopping().equalsIgnoreCase("onion")) {
                hamburger = new Onion(hamburger,topping.getCost());
            }
            else if (topping.getTopping().equalsIgnoreCase("tomato")) {
                hamburger = new Tomato(hamburger,topping.getCost());
            }
        }
        return hamburger;
    }
}
