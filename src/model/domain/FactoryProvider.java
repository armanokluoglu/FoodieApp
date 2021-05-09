package model.domain;

import model.domain.hamburger.HamburgerFactory;
import model.domain.pizza.PizzaFactory;

public class FactoryProvider {
	
	public static FoodFactory getFactory(String choice){
        
        if(choice.equalsIgnoreCase("pizza")) {
            return (FoodFactory) new PizzaFactory();
        }
        else if(choice.equalsIgnoreCase("hamburger")) {
            return (FoodFactory) new HamburgerFactory();
        }
        return null;
    }
}
