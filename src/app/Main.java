package app;

import java.util.Arrays;
import model.domain.FactoryProvider;
import model.domain.FoodFactory;
import model.domain.pizza.IPizza;

public class Main {
	public static void main(String[] args) {
		FoodFactory factory = FactoryProvider.getFactory("pizza");
		IPizza pizza = (IPizza) factory.create("neapolitan", Arrays.asList("pepperoni"));
		System.out.println(pizza.decorate());
	}
}
