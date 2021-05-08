package app;

import java.util.Arrays;
import model.domain.FactoryProvider;
import model.domain.FoodFactory;
import model.domain.hamburger.IHamburger;
import model.domain.pizza.IPizza;

public class Main {
	public static void main(String[] args) {
		FoodFactory factory = FactoryProvider.getFactory("pizza");
		FoodFactory factory1 = FactoryProvider.getFactory("hamburger");

		IPizza pizza = (IPizza) factory.create("neapolitan", Arrays.asList("pepperoni","mozzarella","mushroom","olive","corn"));
		IPizza pizza1 = (IPizza) factory.create("californian", Arrays.asList("pepperoni","mozzarella","mushroom","olive","corn"));
		IPizza pizza2 = (IPizza) factory.create("newyorkstyle", Arrays.asList("pepperoni","mozzarella","mushroom","olive","corn"));

		IHamburger hamburger = (IHamburger) factory1.create("bigmac", Arrays.asList("mayonnaise","cheese","cucumber","lettuce","onion","tomato"));
		IHamburger hamburger2 = (IHamburger) factory1.create("turkey", Arrays.asList("mayonnaise","cheese","cucumber","lettuce","onion","tomato"));

		System.out.println(pizza.decorate());
		System.out.println(pizza1.decorate());
		System.out.println(pizza2.decorate());
		System.out.println(hamburger.decorate());
		System.out.println(hamburger2.decorate());

	}
}
