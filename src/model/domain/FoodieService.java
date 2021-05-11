package model.domain;

import java.util.*;
import model.data_access.IO;
import model.data_access.Repository;
import model.utilities.Observer;
import model.utilities.Subject;

public class FoodieService implements Observer, Subject {

	private Repository repo;
	private List<Observer> observers;
	private List<Subject> subjects;
	private IO io;
	
	public FoodieService() {
		io = new IO();
		this.repo = new Repository(io);
		setObservers(new ArrayList<Observer>());
		setSubjects(new ArrayList<Subject>());
		registerAll();
	}

	private void registerAll() {
		List<User> users = repo.getAllUsers();
		for (User user : users) {
			user.register(this);
		}
	}

	public void outputData(){
		//repo.output();
	}
	
	public User login(String username, String password) throws IllegalArgumentException, IllegalStateException {
		username = "armanokluoglu";
		password = "1234";
		User user = repo.findUserByUsername(username);
		if (!user.getPassword().equals(password)) {
			throw new IllegalArgumentException("Invalid password.");
		}
		return user;
	}
	
	public void changeNameOfUser(String newName, User currentUser) {
		currentUser.setName(newName);
	}

	public void changeUsernameOfUser(String newUsername, User currentUser) {
		currentUser.setUsername(newUsername);
	}

	public void changeAddressOfUser(String newAddress, User currentUser) {
		currentUser.setAddress(newAddress);
	}
	
	public List<User> getAllRestaurants() {
		return repo.getAllRestaurants();
	}

	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	// SUBJECT METHODS

	@Override
	public void register(Observer obj) {
		if (obj == null) {
			throw new NullPointerException("The given observer is null.");
		}
		List<Observer> observers = getObservers();
		if (!observers.contains(obj)) {
			observers.add(obj);
			setObservers(observers);
		}
	}

	@Override
	public void unregister(Observer obj) {
		if (obj == null) {
			throw new NullPointerException("The given observer is null.");
		}
		List<Observer> observers = getObservers();
		if (!observers.contains(obj)) {
			observers.remove(obj);
			setObservers(observers);
		}
	}

	@Override
	public void notifyObservers() {
		List<Observer> observers = getObservers();
		for (Observer observer : observers) {
			observer.update();
		}
	}

	// OBSERVER METHODS

	@Override
	public void update() {
		notifyObservers();
	}

	@Override
	public void addSubject(Subject sub) {
		this.subjects.add(sub);
	}

	@Override
	public void removeSubject(Subject sub) {
		this.subjects.remove(sub);
	}
}