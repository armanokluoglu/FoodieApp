package controller;

import jdk.nashorn.internal.ir.IfNode;
import model.domain.*;
import model.utilities.Observer;
import model.utilities.Subject;
import view.FoodFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FoodController  implements Observer {

    private SessionManager session;
    private Subject subject;
    private String food;
    private FoodFrame view;
    private List<String> toppings;
    private List<String> selectedToppings = new ArrayList<>();


    public FoodController(FoodFrame view, SessionManager session, User restaurant, String food, List<String> toppings) {
        this.subject = restaurant;
        this.view = view;
        this.session = session;
        this.food = food;
        this.toppings = toppings;
        restaurant.register(this);
        setSidebarListeners();
        setContentListeners();
    }
    private void setSidebarListeners() {
        view.addOpenRestaurantsActionListener(new FoodController.OpenRestaurantsListener());
        view.addOpenUserProfileActionListener(new FoodController.OpenUserProfileListener());
        view.addLogoutActionListener(new FoodController.LogoutListener());
        view.addOpenShoppingCartActionListener(new FoodController.OpenShoppingCartListener());
        //view.addAddToCardActionListener(new FoodController.AddToCartActionListener());

    }
    private void setContentListeners() {
        for(String topping: toppings){
            view.addSelectToppingActionListener(new SelectToppingActionListener(topping),topping);
            view.addUnSelectToppingActionListener(new UnSelectToppingActionListener(topping),topping);
        }
        view.addAddToCardActionListener(new AddToCartActionListener(food,selectedToppings,(User) subject));
    }

    class AddToCartActionListener implements ActionListener {
        public String foodName;
        public List<String> selectedToppings;
        public User restaurant;
        public AddToCartActionListener(String foodName, List<String> selectedToppings,User restaurant) {
            this.foodName = foodName;
            this.selectedToppings = selectedToppings;
            this.restaurant = restaurant;
        }

        public void actionPerformed(ActionEvent e) {
            IFood cartFood = ((Restaurant)restaurant).createFood(foodName,selectedToppings);
            ((Customer)session.getCurrentUser()).addItemToOrder(cartFood);
            session.restaurantPage(restaurant);
        }
    }
    class SelectToppingActionListener implements ActionListener {
        public String topping;
        public SelectToppingActionListener(String topping) {
            this.topping = topping;

        }
        public void actionPerformed(ActionEvent e) {
            selectedToppings.add(topping);
        }
    }

    class UnSelectToppingActionListener implements ActionListener {
        public String topping;
        public UnSelectToppingActionListener(String topping) {
            this.topping = topping;

        }
        public void actionPerformed(ActionEvent e) {
            if(selectedToppings.contains(topping))
                selectedToppings.remove(topping);
        }
    }

    class OpenShoppingCartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            session.shoppingCartPage();
        }
    }

    class OpenRestaurantsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            session.restaurantsPage();
        }
    }

    class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            session.loginPage();
        }
    }

    class OpenUserProfileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            session.userProfilePage();
        }
    }

    @Override
    public void update() {
        setContentListeners();
    }

    @Override
    public void addSubject(Subject sub) {
        this.subject = sub;
    }

    @Override
    public void removeSubject(Subject sub) {
        this.subject = null;
    }
}
