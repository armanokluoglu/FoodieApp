package app;
 class Person{

	public Person(){
		System.out.println("person");
	}

	void mm1(){
		System.out.println("m1p");
	}

}
 class Child extends Person{

	public Child(){
		System.out.println("child");
	}
	 void mm1(){
		 System.out.println("m1c");
	 }
}
public class Main {


	public static void main(String[] args) {

		Person p = new Child();
		p.mm1();
		FoodieApp app = new FoodieApp();
		app.start();
	}
}
