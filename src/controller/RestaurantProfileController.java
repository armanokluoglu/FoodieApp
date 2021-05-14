package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.domain.FoodieService;
import model.domain.User;
import model.utilities.Observer;
import model.utilities.Subject;
import view.RestaurantProfileFrame;

public class RestaurantProfileController implements Observer {

	private SessionManager session;
	private RestaurantProfileFrame view;
	private FoodieService model;
	private Subject currentRestaurant;

	public RestaurantProfileController(FoodieService model, RestaurantProfileFrame view, SessionManager session) {
		this.session = session;
		this.view = view;
		this.currentRestaurant = session.getCurrentUser();
		this.model = model;

		currentRestaurant.register(this);
		
		setSidebarListeners();
		setContentListeners();
	}

	private void setSidebarListeners() {
		view.addLogoutActionListener(new LogoutListener());
		view.addOpenOrderHistoryActionListener(new OpenOrderHistoryListener());
	}

	private void setContentListeners() {
		view.addChangeNameActionListener(new ChangeNameListener());
		view.addChangeUsernameActionListener(new ChangeUsernameListener());
		view.addChangeAddressActionListener(new ChangeAddressListener());
	}
	
	class ChangeNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String newName = view.showInputDialog("Enter new name:");
        	if (newName == null || newName == "")  {
        		return;
        	}
        	newName = newName.trim();
			((FoodieService) model).changeNameOfUser(newName, (User) currentRestaurant);
        }
    }
	
	class ChangeUsernameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String newUsername = view.showInputDialog("Enter new username:");
        	if (newUsername == null || newUsername == "")  {
        		return;
        	}
        	newUsername = newUsername.trim();
			((FoodieService) model).changeUsernameOfUser(newUsername, (User) currentRestaurant);
        }
    }
	
	class ChangeAddressListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String newAddress = view.showInputDialog("Enter new address:");
        	if (newAddress == null || newAddress == "")  {
        		return;
        	}
        	newAddress = newAddress.trim();
			((FoodieService) model).changeAddressOfUser(newAddress, (User) currentRestaurant);
        }
    }
	
	class OpenOrderHistoryListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			session.orderHistoryPage();
		}
	}

	class LogoutListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			session.loginPage();
		}
	}

	@Override
	public void update() {
		setContentListeners();
	}

	@Override
	public void addSubject(Subject sub) {
		this.currentRestaurant = sub;
	}

	@Override
	public void removeSubject(Subject sub) {
		this.currentRestaurant = null;
	}
}
