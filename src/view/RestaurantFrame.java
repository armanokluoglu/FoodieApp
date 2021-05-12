package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.domain.Menu;
import model.domain.Restaurant;
import model.domain.User;
import model.utilities.Observer;
import model.utilities.Subject;

public class RestaurantFrame extends JFrame implements Observer {

	private static final long serialVersionUID = -4853864434524144396L;
	private Subject subject;
	private FrameManager fm;
	private JPanel mainPanel;

	private JPanel leftSide;
	private JPanel content;

	private JButton restaurantsButton;
	private JButton shoppingCartButton;
	private JButton profilePageButton;
	private JButton logoutButton;

	private List<JButton> foodButtons;

	public RestaurantFrame(FrameManager fm, User restaurant) {
		this.fm = fm;
		this.subject = restaurant;
		this.foodButtons = new ArrayList<>();

		restaurant.register(this);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 2));
		
		JPanel leftSide = new JPanel();
		leftSide.setLayout(new GridBagLayout());
		leftSide.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.leftSide = leftSide;
		
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		this.content = content;

		mainPanel.add(this.leftSide);
		mainPanel.add(this.content);
		this.mainPanel = mainPanel;
		
		setLeftSide();
		setContent();
		getFrameManager().setNewPanel(mainPanel, "restaurant");
	}

	public void setLeftSide() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);

		JLabel titleLabel = new JLabel("Foodie", JLabel.CENTER);
		titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), titleLabel.getFont().getStyle(), 30));

		JLabel pageLabel = new JLabel("Restaurant", JLabel.CENTER);
		pageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		pageLabel.setFont(new Font(pageLabel.getFont().getName(), pageLabel.getFont().getStyle(), 20));

		JButton restaurantsButton = new JButton("Restaurants");
		restaurantsButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		restaurantsButton.setPreferredSize(new Dimension(200, 50));
		this.restaurantsButton = restaurantsButton;

		JButton shoppingCartButton = new JButton("Shopping Cart");
		shoppingCartButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		shoppingCartButton.setPreferredSize(new Dimension(200, 50));
		this.shoppingCartButton = shoppingCartButton;
		
		JButton profilePageButton = new JButton("My Profile");
		profilePageButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		profilePageButton.setPreferredSize(new Dimension(200, 50));
		this.profilePageButton = profilePageButton;

		JButton logoutButton = new JButton("Logout");
		logoutButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		logoutButton.setPreferredSize(new Dimension(200, 50));
		this.logoutButton = logoutButton;

		leftSide.add(titleLabel, gbc);
		leftSide.add(pageLabel, gbc);
		leftSide.add(restaurantsButton, gbc);
		leftSide.add(profilePageButton, gbc);
		leftSide.add(shoppingCartButton, gbc);
		leftSide.add(logoutButton, gbc);
	}

	public void setContent() {
		Restaurant restaurant = ((Restaurant) this.subject);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(10, 10, 10, 10);
		
		JLabel restaurantName = new JLabel("<html><FONT SIZE=5><FONT COLOR=GREEN>Restaurant Name: </FONT>" + restaurant.getName() + "</FONT></html>");
		JLabel restaurantAddress = new JLabel("<html><FONT SIZE=5><FONT COLOR=GREEN>Restaurant Address: </FONT>" + restaurant.getAddress() + "</FONT></html>");
		JLabel menuLabel = new JLabel("<html><FONT SIZE=5 COLOR=GREEN>MENU</FONT></html>");

		panel.add(restaurantName, gbc);
		panel.add(restaurantAddress, gbc);
		panel.add(menuLabel, gbc);
		
		List<Menu> menu = restaurant.getMenu();
		for (Menu submenu : menu) {
			JPanel submenuPanel = new JPanel(new GridBagLayout());
			JLabel submenuName = new JLabel("<html><FONT SIZE=5 COLOR=RED>" + submenu.getName() + "</FONT></html>");
			
			submenuPanel.add(submenuName, gbc);

			Map<String, List<String>> items = submenu.getItems();
			for (String item : items.keySet()) {
				JButton foodButton = new JButton();
				ImageIcon icon = new ImageIcon("assets/" + item + ".jpg");

				foodButton.setName(item);
				foodButton.setIcon(icon);
				foodButton.setBackground(java.awt.Color.WHITE);
				foodButton.setPreferredSize(new Dimension(300, 300));
				foodButtons.add(foodButton);
				submenuPanel.add(foodButton, gbc);
				
				JLabel itemName = new JLabel("<html><FONT SIZE=4 COLOR=RED>" + toTitleCase(item) + "</FONT></html>");
				
				List<String> itemToppings = items.get(item);
				String toppings = "";
				for (String topping : itemToppings) {
					toppings += toTitleCase(topping) + ", ";
				}
				toppings = toppings.substring(0, toppings.length() - 2);
				JLabel availableToppings = new JLabel("<html><FONT SIZE=3 COLOR=RED>Available Toppings: </FONT>" + toppings + "</html>");
				availableToppings.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
				
				submenuPanel.add(itemName, gbc);
				submenuPanel.add(availableToppings, gbc);
				submenuPanel.setBorder(new RoundedLineBorder(Color.BLACK, 1, 10, true));
			}
			panel.add(submenuPanel, gbc);
		}

		content.removeAll();
		content.add(new JScrollPane(panel));
		getFrameManager().setNewPanel(mainPanel, "user");
	}

	public void addOpenFoodActionListener(ActionListener actionListener, String foodName) {
		for (JButton jButton : foodButtons) {
			if (jButton.getName().equals(foodName)) {
				jButton.addActionListener(actionListener);
			}
		}
	}
	
	public void addOpenRestaurantsActionListener(ActionListener actionListener) {
		restaurantsButton.addActionListener(actionListener);
	}
	
	public void addOpenShoppingCartActionListener(ActionListener actionListener) {
		shoppingCartButton.addActionListener(actionListener);
	}
	
	public void addOpenUserProfileActionListener(ActionListener actionListener) {
		profilePageButton.addActionListener(actionListener);
	}

	public void addLogoutActionListener(ActionListener actionListener) {
		logoutButton.addActionListener(actionListener);
	}

	public FrameManager getFrameManager() {
		return this.fm;
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(getFrameManager().getFrame(), message);
	}

	public String showInputDialog(String message) {
		return JOptionPane.showInputDialog(getFrameManager().getFrame(), message);
	}
	
	private String toTitleCase(String givenString) {
	    String[] arr = givenString.split(" ");
	    StringBuffer sb = new StringBuffer();

	    for (int i = 0; i < arr.length; i++) {
	        sb.append(Character.toUpperCase(arr[i].charAt(0)))
	            .append(arr[i].substring(1)).append(" ");
	    }          
	    return sb.toString().trim();
	}  

	@Override
	public void update() {
		setContent();
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
