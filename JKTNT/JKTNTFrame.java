import java.awt.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
    private JButton submitComm;
    private JButton submitReq;
    private JButton adminPage;
    private TextField commArea;
    private TextField userR;
    private TextField passR;
    private TextField user;
    private TextField pass;
    private TextField searchQuery;
    private btnListener clickListener;
    private JComboBox<String> box;
    private JComboBox<Integer> rateBox;
    private ArrayList<Game> g;
    private int currGame;
    private User u;
    private Admin a;
    private Moderator m;
    

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
        // Line of code that says what happens when you click close
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        u = new User("");
        a = new Admin("");
        m = new Moderator("");
        label = new JLabel("Welcome to JKTNT!");
        bottom = new JPanel();
        // Call helper methods to set up various sections
        setupBottomPanel();
        setupMiddlePanel();
        setupGames();
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
        if (u.getUserName().isEmpty() && a.getUserName().isEmpty() && m.getUserName().isEmpty()) {
            label.setText("Welcome to JKTNT!");
        } else {
            if (!a.getUserName().isEmpty()) {
                label.setText(a.getUserName() + " is logged in and is an Admin!");
            } else if (!m.getUserName().isEmpty()) {
                label.setText(m.getUserName() + " is logged in and is a Moderator!");
            } else {
                label.setText(u.getUserName() + " is logged in and is a User!");
            }
        }
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
      
        
        JLabel sort = new JLabel("Sort By:");
        mainScreen.add(sort);
        //add a selection box for sorting, to add more item just add to the string [] 
        //and change the filter method
        String[] selection = {"A->Z", "Z->A", "High->Low", "Low->High"};
        box = new JComboBox<String>(selection);
        box.addActionListener(clickListener);
        mainScreen.add(box);
        
        Integer[] select = {0, 1, 2, 3, 4, 5};
        rateBox = new JComboBox<>(select);
        rateBox.addActionListener(clickListener);
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
        JScrollPane scroll = new JScrollPane(gamePanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);        
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
        this.add(scroll, BorderLayout.CENTER); 
        g = readGames();
        loadGames(g);
        filterPrice("");
        
        //Change the scroll bar dynamically corresponding to the window size and game entries.
        scroll.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                int len;
                if (mainScreen.getWidth() < 530) {
                    len = g.size() * 330 ;
                    gamePanel.setPreferredSize(new Dimension(400, len));
                } else if (mainScreen.getWidth() > 530 && mainScreen.getWidth() < 790) {
                    
                    len = g.size() * 165 ;
                    gamePanel.setPreferredSize(new Dimension(400, len));
                } else if (mainScreen.getWidth() > 790 && mainScreen.getWidth() < 1040) {
                    //System.out.println("default");
                    len = g.size() * 120 ;
                    gamePanel.setPreferredSize(new Dimension(400, len));
                } else {
                    len = g.size() * 95 ;
                    gamePanel.setPreferredSize(new Dimension(400, len));
                }
            }
        });
            
            
         
    }
    
    private ArrayList<Game> readGames() {
        ArrayList<Game> games = new ArrayList<Game>(0);
        try {
            BufferedReader read = new BufferedReader(new FileReader("gameList.csv"));
            String dummy;
            for (int i = 0; (dummy = read.readLine()) != null; i++) {
                String[] gameInfo = dummy.split(",");
                games.add(new Game(Integer.parseInt(gameInfo[0]), gameInfo[1].equals("true"), gameInfo[2], gameInfo[3], 
                        Double.parseDouble(gameInfo[4]), gameInfo[5], gameInfo[6]));
                games.get(i).getButton().addActionListener(clickListener);
            }

            read.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        return games;
        
    }

    // testing for changing panels
    private void displayDetailedPage(Game g) {
        gamePanel.removeAll();
        gamePanel.add(g);
        
        JPanel bot = new JPanel();

        // make the text area scrollable
        JScrollPane scroll = new JScrollPane(g.getGamePanel());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(1000, 500));
        
        gamePanel.add(scroll, BorderLayout.NORTH);
        
        if (!u.getUserName().isEmpty() || !m.getUserName().isEmpty() || !a.getUserName().isEmpty()) {
           commArea = new TextField("", 100);
           submitComm = new JButton("submit");
           submitComm.addActionListener(clickListener);
           bot.add(new JLabel("Comment:"));
           bot.add(commArea);
           bot.add(new JLabel("Rating:"));
           bot.add(rateBox);
           bot.add(submitComm);
        }

        // create a back button to go back to home page
        back = new JButton("Back");
        bot.add(back);
        back.addActionListener(clickListener);
        gamePanel.add(bot, BorderLayout.SOUTH);
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
        
        g = readGames();
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

        mainScreen.add(new JLabel("Username"));
        mainScreen.add(user);
        mainScreen.add(new JLabel("Password"));
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
        
        g = readGames();
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
        } else if (!u.getUserName().isEmpty() || !m.getUserName().isEmpty() || !a.getUserName().isEmpty()) {
            JOptionPane.showMessageDialog(mainScreen, "User already logged in!");
        } else{
            if (u.loginUser(userN, passW)) {
                try {
                    if (u.isMod(userN)) {
                        u.logout();
                        m.loginUser(userN, passW);
                        JOptionPane.showMessageDialog(mainScreen, "Successfully Logged In as Mod!");
                    } else if (u.isAdmin(userN)) {
                        u.logout();
                        a.loginUser(userN, passW);
                        JOptionPane.showMessageDialog(mainScreen, "Successfully Logged In as Admin!");
                    } else {
                        JOptionPane.showMessageDialog(mainScreen, "Successfully Logged In as User!");
                    }
                } catch (HeadlessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }               
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
        if (u.logout() || a.logout() || m.logout()) {
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
     * Will filter the game accordingly with user selection
     */
    private void filterPrice(String btn) {
        gamePanel.removeAll();
        filter sort = new filter(g);
        ArrayList<Game> gameList;
        if (btn.equals("High->Low")) {
            gameList = sort.priceHitoLo();
        } else if (btn.equals("Low->High")){
            gameList = sort.priceLotoHi();
        } else if (btn.equals("Z->A")) {
            gameList = sort.ztoA();
        } else {
            gameList = sort.atoZ();
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
            if (gameList.get(i).getShowGame()) {
                gamePanel.add(gameList.get(i));
            }
        }
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    private void menuBack() {
        mainScreen.removeAll();
        
        JLabel sort = new JLabel("Sort By:");
        mainScreen.add(sort);
        
        mainScreen.add(box);
        // creates a Search button with event listener attached
        mainScreen.add(search);
        // creates a empty 20px wide textField
        mainScreen.add(searchQuery);
        // creates a button to clear the search bar
        mainScreen.add(clear);
        // creates a Login button with event listener attached
        if (u.getUserName().isEmpty() && m.getUserName().isEmpty() && a.getUserName().isEmpty()) {
            mainScreen.add(loginSet);
                
            mainScreen.add(registerSet);
        } else {
            logoutBtn = new JButton("Logout");
            mainScreen.add(logoutBtn);
            logoutBtn.addActionListener(clickListener);
        }
        if (a.getUserName().isEmpty() && (!m.getUserName().isEmpty() || !u.getUserName().isEmpty())) {
            submitReq = new JButton("Request");
            mainScreen.add(submitReq);
            submitReq.addActionListener(clickListener);
        } else if (!a.getUserName().isEmpty()){
            adminPage = new JButton("Admin");
            mainScreen.add(adminPage);
            adminPage.addActionListener(clickListener);
        }
        
        mainScreen.revalidate();
        mainScreen.repaint();
        
        g = readGames();
        loadGames(g);
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
            } else if (btn == submitComm) {
                u.postComment(commArea.getText(), g.get(currGame), (int)rateBox.getSelectedItem());
                g = readGames();
                loadGames(g);
                displayDetailedPage(g.get(currGame));
            } else if (btn == box) {
                filterPrice((String)box.getSelectedItem());
            }else if (btn == logoutBtn) {
                logoutMsg();
            } else {
                // react based on the game clicked
                for (int i = 0; i < g.size(); i++) {
                    if (g.get(i) == null) {
                        break;
                    }
                    if (btn.equals(g.get(i).getButton())) {
                        currGame = i;
                        displayDetailedPage(g.get(i));
                    }
                }
                // trying to get to a new page
            }
        }

    }

}