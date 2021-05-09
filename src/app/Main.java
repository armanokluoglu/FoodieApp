package app;

import java.util.Arrays;
import model.domain.FactoryProvider;
import model.domain.IFood;
import model.domain.FoodFactory;

public class Main {
	public static void main(String[] args) {
		FoodFactory factory = FactoryProvider.getFactory("pizza");
		FoodFactory factory1 = FactoryProvider.getFactory("hamburger");

		IFood pizza1 = (IFood) factory.create("neapolitan", Arrays.asList("pepperoni","mozzarella","mushroom","olive","corn"));
		IFood pizza2 = (IFood) factory.create("californian", Arrays.asList("pepperoni","mozzarella","mushroom","olive","corn"));
		IFood pizza3 = (IFood) factory.create("newyorkstyle", Arrays.asList("pepperoni","mozzarella","mushroom","olive","corn"));

		IFood hamburger1 = (IFood) factory1.create("bigmac", Arrays.asList("mayonnaise","cheese","pickles","lettuce","onion","tomato"));
		IFood hamburger2 = (IFood) factory1.create("turkey", Arrays.asList("mayonnaise","cheese","pickles","lettuce","onion","tomato"));

		System.out.println(pizza1.decorate());
		System.out.println(pizza1.getCost());
		
		System.out.println(pizza2.decorate());
		System.out.println(pizza2.getCost());
		
		System.out.println(pizza3.decorate());
		System.out.println(pizza3.getCost());
		
		System.out.println(hamburger1.decorate());
		System.out.println(hamburger1.getCost());
		
		System.out.println(hamburger2.decorate());
		System.out.println(hamburger2.getCost());
	}
}
