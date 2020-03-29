import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class JKTNTFrame extends JFrame {

	private JLabel label;
	private JKTNTPanel panel;
	private JButton search;
	private JButton login;
	
	public static void main(String[] args) {
		new JKTNTFrame();
	}
	
	public JKTNTFrame() {
		super("JKTNT Window");
		this.setSize(new Dimension(500, 500));
		this.setLocationRelativeTo(null); // starts center screen
		this.setLayout(new BorderLayout());

		// Line of code that says what happens when you click close
		// Line of code to set the size and location
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// j.setBounds(500, 300, 800, 800);
		// Call helper methods to set up various sections
		setupTopPanel();
		setupMiddlePanel();
		setupBottomPanel();

		this.pack();
		this.setVisible(true);
	}
	
	// Sets up the top panel where some graphics-related buttons
	// will go.
	public void setupBottomPanel() {
		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout());
		// Now that the panel is set up, add it to the frame
		this.add(bottom, BorderLayout.SOUTH);
	}

	// Sets up the middle panel where the graphics will go
	private void setupMiddlePanel() {	
		panel = new JKTNTPanel();
		panel.setLayout(new FlowLayout());
		login = new JButton("Login");
		panel.add(login);
		search = new JButton("Search");
		panel.add(search);
		panel.setVisible(true);
		// This anonymous GraphicsPanel should be replaced with an instance variable
		this.add(panel, BorderLayout.CENTER);
	}

	// Sets up the bottom panel with buttons
	private void setupTopPanel() {
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
	//	MyButtonListener buttonListener = new MyButtonListener();

		label = new JLabel("Welcome to JKTNT!");
	
	//	top.add(login);
	//	loadData.addActionListener(buttonListener);
	//	saveData.addActionListener(buttonListener);
	//	clear.addActionListener(buttonListener);
		// These anonymous JButtons should be replaced with instance variables
		top.add(label);
		top.setVisible(true);
		// Once the panel is set up, add it to the frame
		this.add(top, BorderLayout.NORTH);
		
	}

}
