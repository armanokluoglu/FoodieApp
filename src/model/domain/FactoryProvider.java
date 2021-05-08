package model.domain;

import model.domain.hamburger.HamburgerFactory;
import model.domain.pizza.PizzaFactory;

public class FactoryProvider {
	
	public static <T extends Food> FoodFactory<T> getFactory(String choice){
        
        if(choice.equalsIgnoreCase("pizza")) {
            return (FoodFactory<T>) new PizzaFactory();
        }
        else if(choice.equalsIgnoreCase("hamburger")) {
            return (FoodFactory<T>) new HamburgerFactory();
        }
        return null;
    }
}
