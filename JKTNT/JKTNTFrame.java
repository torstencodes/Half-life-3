import java.awt.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class JKTNTFrame extends JFrame {

	private JLabel label;
	private JKTNTPanel mainScreen;
	private JKTNTPanel gamePanel;
	private JPanel bottom;
	private JButton search;
	private JButton clear;
	private JButton loginCheck;
	private JButton loginSet;
	private JButton registerSet;
	private JButton registerCheck;
	private JButton logoutBtn;
	private JButton back;
	private JButton menuBackBtn;
	private JButton hiToLo;
	private JButton loToHi;
	private JButton atoZ;
	private JButton ztoA;
	private TextField userR;
	private TextField passR;
	private TextField user;
	private TextField pass;
	private TextField searchQuery;
	private btnListener clickListener;
	private Game[] g;
	private User u;

	public static void main(String[] args) throws InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, ClassNotFoundException {
		// gets the sleek look
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}
		new JKTNTFrame();

	}

	public JKTNTFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
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
	    u = new User("");
	    label = new JLabel("Welcome to JKTNT!");
	    bottom = new JPanel();
		// j.setBounds(500, 300, 800, 800);
		// Call helper methods to set up various sections
		setupBottomPanel();
		setupMiddlePanel();
		// setupBottomPanel();
		setupGames();

//		this.setLocationRelativeTo(null); // starts center screen
//		this.setLayout(new GridLayout(10, 10));
//		this.setVisible(true);
//		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		// Initiate the user object. 
	}

	// Sets up the top panel where some graphics-related buttons
	// will go.
	private void setupBottomPanel() {
	    bottom.removeAll();
        bottom.setLayout(new FlowLayout());
        // MyButtonListener buttonListener = new MyButtonListener();
        if (u.getUserName().isEmpty()) {
            label.setText("Welcome to JKTNT!");
        } else {
            if (u.isAdmin(u.getUserName())) {
                label.setText(u.getUserName() + " is logged in and is an Admin!");
            } else if (u.isMod(u.getUserName())) {
                label.setText(u.getUserName() + " is logged in and is a Moderator!");
            } else {
                label.setText(u.getUserName() + " is logged in and is a User!");
            }
        }

        // top.add(login);
        // loadData.addActionListener(buttonListener);
        // saveData.addActionListener(buttonListener);
        // clear.addActionListener(buttonListener);

        // These anonymous JButtons should be replaced with instance variables
        bottom.add(label);
        bottom.setVisible(true);
        
        bottom.revalidate();
        bottom.repaint();
        // Once the panel is set up, add it to the frame
        this.add(bottom, BorderLayout.SOUTH);

	}
	
	private void setupMiddlePanel() {
        mainScreen = new JKTNTPanel();
        mainScreen.setLayout(new FlowLayout());
        mainScreen.setPreferredSize(new Dimension(1000, 100));
        
        atoZ = new JButton("A->Z");
        mainScreen.add(atoZ);
        atoZ.addActionListener(clickListener);
        
        ztoA = new JButton("Z->A");
        mainScreen.add(ztoA);
        ztoA.addActionListener(clickListener);

        hiToLo = new JButton("High->Low");
        mainScreen.add(hiToLo);
        hiToLo.addActionListener(clickListener);
        
        loToHi = new JButton("Low->High");
        mainScreen.add(loToHi);
        loToHi.addActionListener(clickListener);
        
        // creates a Search button with event listener attached
        search = new JButton("Search");
        searchQuery = new TextField("", 30);
        mainScreen.add(search);
        mainScreen.add(searchQuery);
        search.addActionListener(clickListener);

        // creates a button to clear the search bar
        clear = new JButton("Clear");
        mainScreen.add(clear);
        clear.addActionListener(clickListener);
        
        // creates a Login button with event listener attached
        loginSet = new JButton("Login");
        mainScreen.add(loginSet);
        loginSet.addActionListener(clickListener);
        
        // Create a register button with event listener
        registerSet = new JButton("register");
        mainScreen.add(registerSet);
        registerSet.addActionListener(clickListener);

        mainScreen.setVisible(true);
        
        this.add(mainScreen, BorderLayout.NORTH);
	}

	// Sets up the middle panel where the graphics will go


	private void setupGames() {
        // set up a panel inside the mainScreen panel to display games
        gamePanel = new JKTNTPanel();
        // JScrollPane scrollFrame = new JScrollPane(gamePanel);
        gamePanel.setPreferredSize(new Dimension(400, 1500));
        // scrollFrame.setPreferredSize(mainScreen.getMaximumSize());

        JScrollPane scroll = new JScrollPane(gamePanel);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setLayout(new ScrollPaneLayout());
        scroll.setPreferredSize(new Dimension(1000, 800));

        
        
        gamePanel.setLayout(new FlowLayout());
        // mainScreen.setPreferredSize(mainScreen.getPreferredSize());
        gamePanel.setVisible(true);
        this.add(scroll, BorderLayout.CENTER);
		g = new Game[4];

//		for(int i = 4; i < g.length; i++) {
//			g[i] = new Game("Dead Island","deadIsland.png",19.99);
//			g[i].getButton().addActionListener(clickListener);
//			gamePanel.add(g[i]);
//		}
		g[0] = new Game("Dead Island", "deadIsland.png", 14.99);
		g[0].getButton().addActionListener(clickListener);
		g[1] = new Game("Xcom2", "xcom2.png", 29.99);
		g[1].getButton().addActionListener(clickListener);
		g[2] = new Game("Tomb Rider", "tombRaider.jpg", 19.99);
		g[2].getButton().addActionListener(clickListener);
		g[3] = new Game("PlayerUnknown's: BattleGrounds", "pubg.jpg", 24.99);
		g[3].getButton().addActionListener(clickListener);
		loadGames(g);
	}

	// testing for changing panels
	private void displayDetailedPage(Game g) {
		gamePanel.removeAll();
		gamePanel.add(g);

		// display a description of the game
		JTextArea description = new JTextArea();
		description.setSize(500, 500);
		// temporary text for 1 game
		description.setText(
				"XCOM 2 is a 2016 turn-based tactics video game that was developed by Firaxis Games and published by 2K Games. It is the sequel to 2012's reboot of the series XCOM: Enemy Unknown; it takes place 20 years after the events of Enemy Unknown. XCOM, a military organization trying to fight off an alien invasion, has lost the war and is now a resistance force against the occupation of Earth and the established totalitarian regime and military dictatorship. Gameplay is split between turn-based combat in which players command a squad of soldiers to fight enemies and strategy elements. Players manage and control the operations of the Avenger, a derelict alien ship that is a mobile base for XCOM, commanding the engineering and research department of the base between missions. ");
		description.setFont(new Font("TimesRoman", Font.PLAIN, 18));

		description.setEditable(false);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);

		// make the text area scrollable
		JScrollPane scroll = new JScrollPane(description);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(500, 500));

		gamePanel.add(scroll);

		// create a back button to go back to home page
		back = new JButton("Back");
		gamePanel.add(back);
		back.addActionListener(clickListener);

		gamePanel.revalidate();
		gamePanel.repaint();

	}
	/*
	 * I think there is a way we could combine the user name and password instance 
	 * variables with the ones that are used in the login method. Not sure how tho.
	 */
	private void registerUser() {
	    mainScreen.removeAll();
	    
	    JLabel uname = new JLabel("Enter Username");
	    JLabel pwd = new JLabel("Enter Password");
	    userR = new TextField("", 20);
	    passR = new TextField("", 20);
	    mainScreen.add(uname);
	    mainScreen.add(userR);
	    mainScreen.add(pwd);
	    mainScreen.add(passR);
	    
	    registerCheck = new JButton("Register");
	    mainScreen.add(registerCheck);
	    registerCheck.addActionListener(clickListener);
	    
	    menuBackBtn = new JButton("Back");
	    menuBackBtn.addActionListener(clickListener);
	    mainScreen.add(menuBackBtn);
	    mainScreen.setVisible(true);
	    
	    mainScreen.revalidate();
	    mainScreen.repaint();
	    
	    loadGames(g);
	}

	/*
	 * This method simply displays the login screen and allows the user 
	 * to login to their account.
	 * 
	 * Needs to handle the case where user enters in a too long pass or user name
	 */
	private void login() {
		mainScreen.removeAll();
        user = new TextField("", 20);
        pass = new TextField("", 20);
		// creates the username and password text fields
		JLabel uname = new JLabel("Username");
		JLabel psw = new JLabel("Password");
		mainScreen.add(uname);
		mainScreen.add(user);
		mainScreen.add(psw);
		mainScreen.add(pass);

		// creates a Login button with event listener attached
		loginCheck = new JButton("Login");
		mainScreen.add(loginCheck);
		loginCheck.addActionListener(clickListener);

		menuBackBtn = new JButton("Back");
		menuBackBtn.addActionListener(clickListener);
		mainScreen.add(menuBackBtn);
		mainScreen.setVisible(true);
		
		mainScreen.revalidate();
        mainScreen.repaint();
        
		loadGames(g);
	}
	/*
	 * This checks if the user successfully logged in, we should use regex to check validity of the string.
	 * 
	 * Need option to log a user out
	 */
	private void checkLogin() {
	    String userN = user.getText();
	    String passW = pass.getText();
	    if (userN.isEmpty() || passW.isEmpty()) {   
	        JOptionPane.showMessageDialog(mainScreen, "Please enter in valid user/pass!");
	    } else if (!u.getUserName().isEmpty()) {
	        JOptionPane.showMessageDialog(mainScreen, "User already logged in!");
	    } else{
	        if (u.loginUser(userN, passW)) {
                JOptionPane.showMessageDialog(mainScreen, "Successfully Logged In!");
                menuBack();
                setupBottomPanel();
	        } else {
                JOptionPane.showMessageDialog(mainScreen, "User name does not exist!");	            
	        }
	    }
	}
	/*
     * This checks if the registration is good and then lets the user know if registration was successful.
     */
	private void checkReg() {
	    String userN = userR.getText();
	    String passW = passR.getText();
	    // Can add more checking here if we want to define a password by a regex expression.
	    if (userN.isEmpty() || passW.isEmpty()) {
	        JOptionPane.showMessageDialog(mainScreen, "Please enter in valid user/pass!");
	    } else {
	        if (u.registerUser(userN,passW)) {
	            JOptionPane.showMessageDialog(mainScreen, "Successfully Registered!");
	            menuBack();
	            setupBottomPanel();
	        } else {
	            JOptionPane.showMessageDialog(mainScreen, "User name already taken!");
	        }
	    }
	}
	/*
	 * Logs out the user and displays respective message. 
	 */
	private void logoutMsg() {
	    boolean goodLogOut = u.logout();
	    if (goodLogOut) {
	        setupBottomPanel();
	        menuBack();
	        JOptionPane.showMessageDialog(mainScreen, "Successful logout");
	    } else {
            JOptionPane.showMessageDialog(mainScreen, "Already logged out or something");
	    }
	}
	/*
	 * Searches games and filters the games to display by the query
	 */
	private void searchGames() {
	    String query = searchQuery.getText();
	    if (query.isEmpty()) {
	        JOptionPane.showMessageDialog(mainScreen, "Please enter in valid query!");
	    } else {
	        gamePanel.removeAll();
	        filter sort = new filter(g);
	        ArrayList<Game> gameList = sort.search(query);
	        for (int i = 0; i < gameList.size(); i++) {
	            gamePanel.add(gameList.get(i));
	        }
	        gamePanel.revalidate();
	        gamePanel.repaint();
	    }
	}
	/*
	 * Will filter the game list by high to low price or low to high price.
	 * Or it will 
	 */
	private void filterPrice(Object btn) {
	    gamePanel.removeAll();
	    filter sort = new filter(g);
	    ArrayList<Game> gameList;
	    if (btn == hiToLo) {
	        gameList = sort.priceHitoLo();
	    } else if (btn == loToHi){
	        gameList = sort.priceLotoHi();
	    } else if (btn == atoZ) {
	        gameList = sort.atoZ();
	    } else {
	        gameList = sort.ztoA();
	    }
	    loadGames(gameList);
	}
	/*
	 * Provided an array or an arrayList, this method will load all the games
	 * from the given list to the screen.
	 */
	private void loadGames(ArrayList<Game> gameList) {
	    gamePanel.removeAll();
	    for (int i = 0; i < gameList.size(); i++) {
	        gamePanel.add(gameList.get(i));
	    }
	    gamePanel.revalidate();
	    gamePanel.repaint();
	}
	
	private void loadGames(Game[] gameList) {
	    gamePanel.removeAll();
	    for (int i = 0; i < gameList.length; i++) {
	        gamePanel.add(gameList[i]);
	    }
	    gamePanel.revalidate();
	    gamePanel.repaint();
	}

	private void menuBack() {
		mainScreen.removeAll();
	    
		mainScreen.add(atoZ);
		
		mainScreen.add(ztoA);
		
        mainScreen.add(hiToLo);
        
        mainScreen.add(loToHi);
	    // creates a Search button with event listener attached
        mainScreen.add(search);
		// creates a empty 20px wide textField
		mainScreen.add(searchQuery);
		// creates a button to clear the search bar
		mainScreen.add(clear);
		// creates a Login button with event listener attached
		if (u.getUserName().isEmpty()) {
		    mainScreen.add(loginSet);
		        
		    mainScreen.add(registerSet);
		} else {
		    logoutBtn = new JButton("Logout");
		    mainScreen.add(logoutBtn);
		    logoutBtn.addActionListener(clickListener);
		}
		
		mainScreen.revalidate();
        mainScreen.repaint();

		setupGames();
	}

	// Sets up the bottom panel with buttons

	// button listener for Search, Login, etc.
	public class btnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object btn = e.getSource();
			if (btn == search) {
				searchGames();
			} else if (btn == registerSet) {
			    registerUser();
			} else if (btn == registerCheck) {
			    checkReg();
			} else if (btn == loginSet) {
				login();
		    } else if (btn == clear) {
				// do nothing yet, need diagram
				loadGames(g);
				searchQuery.setText("");
			} else if (btn == menuBackBtn) {
				menuBack();
			} else if (btn == back) {
				loadGames(g);
			} else if (btn == loginCheck) {
			    checkLogin();
			} else if (btn == hiToLo || btn == loToHi || btn == atoZ || btn == ztoA) {
			    filterPrice(btn);
			} else if (btn == logoutBtn) {
			    logoutMsg();
			} else {
				// react based on the game clicked
				for (int i = 0; i < g.length; i++) {
					if (g[i] == null) {
						break;
					}
					if (btn.equals(g[i].getButton())) {
						displayDetailedPage(g[i]);
					}
				}
				// trying to get to a new page
			}
		}

	}

}
