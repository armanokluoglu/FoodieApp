package model.domain;

import model.domain.pizza.PizzaFactory;

public class FactoryProvider {
	
	public static <T> FoodFactory<T> getFactory(String choice){
        
        if(choice.equalsIgnoreCase("pizza")) {
            return (FoodFactory<T>) new PizzaFactory();
        }
        
        return null;
    }
}
