import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

@SuppressWarnings("serial")
public class JKTNTFrame extends JFrame {

	private JLabel label;
	private JKTNTPanel mainScreen;
	private JKTNTPanel gamePanel;
	private JButton search;
	private JButton clear;
	private JButton login;
	private JButton back;
	private TextField text;
	private TextField user;
	private TextField pass;
	private btnListener clickListener;
	private Game[] g;

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
		g = new Game[10];
		
		g[0] = new Game("Dead Island","deadIsland.png",19.99);
		g[0].getButton().addActionListener(clickListener);
		g[1] = new Game("Xcom2","xcom2.png", 29.99);
		g[1].getButton().addActionListener(clickListener);
		g[2] = new Game("Tomb Rider","tombRaider.jpg",19.99);
		g[2].getButton().addActionListener(clickListener);
		
		gamePanel.add(g[0]);
		gamePanel.add(g[1]);
		gamePanel.add(g[2]);


		// this.getContentPane().add(g);
		// repaint();

	}
	
	
	//testing for changing panels
	private void displayDetailedPage(Game g) {
		// set up a panel inside the mainScreen panel to display games, separate the search bar, etc.
		
//		gamePanel = new JKTNTPanel(); 
//		//JScrollPane scrollFrame = new JScrollPane(gamePanel);
//		gamePanel.setPreferredSize(mainScreen.getMaximumSize());
//		//scrollFrame.setPreferredSize(mainScreen.getMaximumSize());
//		gamePanel.setLayout(new FlowLayout());
//		mainScreen.add(gamePanel);
		
		gamePanel.removeAll();
		System.out.println(g.getName());
		gamePanel.add(g);
		
		back = new JButton("Back");
		gamePanel.add(back);
		back.addActionListener(clickListener);

//		JLabel l1 = new JLabel("asda");
//		gamePanel.add(l1);
//		System.out.println(name);
		gamePanel.revalidate();
		gamePanel.repaint();

		
	}
	
	//goes back to the main game library page, with preloaded games
	private void back() {
		gamePanel.removeAll();
		
		//Temporary games showing on the main page
		gamePanel.add(g[0]);
		gamePanel.add(g[1]);
		gamePanel.add(g[2]);
		
		//redraw the panel
		gamePanel.revalidate();
		gamePanel.repaint();
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
				// do nothing yet, need diagram
				// search for the keyword typed in the textField
				mainScreen.searchGames(text.getText());

			} else if (btn == login) {
				// do nothing yet, need diagram
				mainScreen.setBackground(new Color(255, 255, 255));
			} else if(btn == clear){
				// do nothing yet, need diagram
				mainScreen.setBackground(new Color(0, 0, 0));
			} else if(btn == back){
				back();
			}
			else {
				// for all the other buttons that are beleng to games
				//gamePanel.setBackground(new Color((int)(Math.random()*255), 155, 0));
				
				//react based on the game clicked
				for(int i = 0; i < g.length; i++) {
					if(g[i] == null) {
						break;
					}
					if(btn.equals(g[i].getButton())) {
						displayDetailedPage(g[i]);
					}
				}
				
				//trying to get to a new page
				

			}
		}

	}

}
