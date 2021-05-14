package view;

import model.domain.Menu;
import model.domain.Restaurant;
import model.domain.User;
import model.utilities.Observer;
import model.utilities.Subject;
import model.utilities.ToppingPricePair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FoodFrame extends JFrame implements Observer {

	private static final long serialVersionUID = -4853864434524144396L;
	private Subject subject;
	private String food;
	private FrameManager fm;
	private JPanel mainPanel;

	private JPanel leftSide;
	private JPanel content;

	private JButton restaurantsButton;
	private JButton shoppingCartButton;
	private JButton profilePageButton;
	private JButton logoutButton;

	private JButton addToCartButton;
	private List<JRadioButton> selectButtons;
	private List<JRadioButton> unSelectButtons;

	public FoodFrame(FrameManager fm, String food, User restaurant) {
		this.fm = fm;
		this.subject = restaurant;
		this.food = food;
		this.selectButtons = new ArrayList<>();
		this.unSelectButtons = new ArrayList<>();

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
		getFrameManager().setNewPanel(mainPanel, "food");
	}

	public void setLeftSide() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);

		JLabel titleLabel = new JLabel("Foodie", JLabel.CENTER);
		titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), titleLabel.getFont().getStyle(), 30));

		JLabel pageLabel = new JLabel("Food", JLabel.CENTER);
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
		Restaurant restaurant = (Restaurant) subject;
		List<ToppingPricePair> toppings = new ArrayList<>();
		List<Menu> menus = restaurant.getMenu();
		for (Menu menu : menus) {
			Map<String, List<ToppingPricePair>> items = menu.getItems();
			for (String item : items.keySet()) {
				if (item.equals(food)) {
					toppings = items.get(item);
				}
			}
		}
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel foodImage = new JLabel();
		ImageIcon icon = new ImageIcon("assets/" + food + ".jpg");

		foodImage.setIcon(icon);
		foodImage.setBackground(java.awt.Color.WHITE);
		foodImage.setPreferredSize(new Dimension(320, 320));
		
		panel.add(foodImage, gbc);
		
		for (ToppingPricePair topping : toppings) {
			JPanel toppingPanel = new JPanel(new GridBagLayout());
			toppingPanel.setLayout(new GridLayout(1, 2));

			JLabel toppingName = new JLabel("<html><FONT SIZE=5 COLOR=RED>" + toTitleCase(topping.getTopping()) + " " + topping.getCost() + "</FONT></html>");

			toppingPanel.add(toppingName, gbc);

			JRadioButton yesButton = new JRadioButton("Yes");
			JRadioButton noButton = new JRadioButton("No", true);

			// ... Create a button group and add the buttons.
			ButtonGroup bgroup = new ButtonGroup();
			bgroup.add(yesButton);
			bgroup.add(noButton);
			// ... Arrange buttons vertically in a panel
			JPanel radioPanel = new JPanel();
			yesButton.setName(topping + "Select");
			noButton.setName(topping + "UnSelect");
			radioPanel.setLayout(new GridLayout(1, 2));
			radioPanel.add(yesButton);
			radioPanel.add(noButton);
			// ... Add a titled border to the button panel.
			radioPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), ""));
			selectButtons.add(yesButton);
			unSelectButtons.add(noButton);

			toppingPanel.add(radioPanel, gbc);
			toppingPanel.setBorder(new RoundedLineBorder(Color.BLACK, 1, 10, true));

			panel.add(toppingPanel, gbc);
		}
		addToCartButton = new JButton();
		addToCartButton.setBackground(java.awt.Color.WHITE);
		addToCartButton.setText("Add To Cart");
		panel.add(addToCartButton);

		content.removeAll();
		content.add(new JScrollPane(panel));
		getFrameManager().setNewPanel(mainPanel, "food");
	}

	public void addAddToCartActionListener(ActionListener actionListener) {
		addToCartButton.addActionListener(actionListener);
	}

	public void addSelectToppingActionListener(ActionListener actionListener, String toppingName) {
		for (JRadioButton selectButton : selectButtons) {
			if (selectButton.getName().equals(toppingName + "Select")) {
				selectButton.addActionListener(actionListener);
			}
		}
	}

	public void addUnSelectToppingActionListener(ActionListener actionListener, String toppingName) {
		for (JRadioButton unSelectButton : unSelectButtons) {
			if (unSelectButton.getName().equals(toppingName + "UnSelect")) {
				unSelectButton.addActionListener(actionListener);
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
