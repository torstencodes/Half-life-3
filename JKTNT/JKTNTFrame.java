import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

@SuppressWarnings("serial")
public class JKTNTFrame extends JFrame {

	private JLabel label;
	private JKTNTFrame frame;
	private JKTNTPanel mainScreen;
	private JKTNTPanel gamePanel;
	private JButton search;
	private JButton clear;
	private JButton login;
	private TextField text;
	private TextField user;
	private TextField pass;
	private btnListener clickListener;
	private Game g;
	private Game g2;

	public static void main(String[] args) {
		new JKTNTFrame();
	}

	public JKTNTFrame() {
		// creates the window
		super("JKTNT Window");

		// Line of code to set the size and location

		this.setSize(new Dimension(800, 800));
		this.setLocationRelativeTo(null); // starts center screen
		this.setLayout(new BorderLayout());
		clickListener = new btnListener();
		// this.setResizable(false);
		// Line of code that says what happens when you click close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// j.setBounds(500, 300, 800, 800);
		// Call helper methods to set up various sections
		setupTopPanel();
		setupMiddlePanel();
		setupBottomPanel();
		setupGames();

//		this.setLocationRelativeTo(null); // starts center screen
//		this.setLayout(new GridLayout(10, 10));
//		this.setVisible(true);

		this.pack();
		this.setVisible(true);
	}

	// Sets up the top panel where some graphics-related buttons
	// will go.
	private void setupTopPanel() {
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		// MyButtonListener buttonListener = new MyButtonListener();

		label = new JLabel("Welcome to JKTNT!");

		// top.add(login);
		// loadData.addActionListener(buttonListener);
		// saveData.addActionListener(buttonListener);
		// clear.addActionListener(buttonListener);

		// These anonymous JButtons should be replaced with instance variables
		top.add(label);
		top.setVisible(true);
		// Once the panel is set up, add it to the frame
		this.add(top, BorderLayout.NORTH);

	}

	// Sets up the middle panel where the graphics will go
	private void setupMiddlePanel() {
		mainScreen = new JKTNTPanel();
		mainScreen.setLayout(new FlowLayout());

		// creates a empty 20px wide textField
		text = new TextField("", 20);
		mainScreen.add(text);

		// creates a Search button with event listener attached
		search = new JButton("Search");
		mainScreen.add(search);
		search.addActionListener(clickListener);

		// creates a button to clear the search bar
		clear = new JButton("Clear");
		mainScreen.add(clear);
		clear.addActionListener(clickListener);

		// creates the username and password text fields
		user = new TextField("", 20);
		pass = new TextField("", 20);
		mainScreen.add(user);
		mainScreen.add(pass);

		// creates a Login button with event listener attached
		login = new JButton("Login");
		mainScreen.add(login);
		login.addActionListener(clickListener);

		mainScreen.setVisible(true);
		this.add(mainScreen, BorderLayout.CENTER);
	}

	private void setupGames() {
		// set up a panel inside the mainScreen panel to display games
		gamePanel = new JKTNTPanel();
		//JScrollPane scrollFrame = new JScrollPane(gamePanel);
		gamePanel.setPreferredSize(mainScreen.getMaximumSize());
		//scrollFrame.setPreferredSize(mainScreen.getMaximumSize());
		gamePanel.setLayout(new FlowLayout());
		mainScreen.add(gamePanel);
		
		
		g = new Game("Dead Island","deadIsland.png");
		g.getButton().addActionListener(clickListener);

		gamePanel.add(g);


		// this.getContentPane().add(g);
		// repaint();

	}
	
	private void setupGames(String name) {
		// set up a panel inside the mainScreen panel to display games, separate the search bar, etc.
		
		gamePanel = new JKTNTPanel(); 
		//JScrollPane scrollFrame = new JScrollPane(gamePanel);
		gamePanel.setPreferredSize(mainScreen.getMaximumSize());
		//scrollFrame.setPreferredSize(mainScreen.getMaximumSize());
		gamePanel.setLayout(new FlowLayout());
		mainScreen.add(gamePanel);
		
		
		this.getContentPane().remove(g);
		this.getContentPane().add(g);
		
		g = new Game("Xcom2","xcom2.png");
		g.getButton().addActionListener(clickListener);
		gamePanel.add(g);
		JLabel l1 = new JLabel("asda");
		gamePanel.add(l1);
		System.out.println(name);
		gamePanel.revalidate();
		repaint();

		
	}

	// Sets up the bottom panel with buttons
	private void setupBottomPanel() {
		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout());

		// load the images
		// add the game to the display

		// Now that the panel is set up, add it to the frame
		this.add(bottom, BorderLayout.SOUTH);
	}

//	private String search() {
//		System.out.println("lol");
//		return "";
//	}

	// button listener for Search, Login, etc.
	public class btnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			Object btn = e.getSource();

			if (btn == search) {
				// search for the keyword typed in the textField
				mainScreen.searchGames(text.getText());

			} else if (btn == login) {
				// do nothing yet
				mainScreen.setBackground(new Color(0, 0, 0));
			} else if (btn == g.getButton()) {
				
				// react based on games clicked
				//gamePanel.setBackground(new Color(0, 155, 0));
				
				//trying to get to a new page
				setupGames(g.getName());

			}
		}

	}

}
