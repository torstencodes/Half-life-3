import java.awt.*;
import java.awt.image.BufferedImage;
import javax.print.DocFlavor.URL;
import javax.swing.*;

/*
 * This is how we draw graphics on an object.
 * We want to draw things on a JButton, so:
 * 1. Extend JButton
 * 2. Override paintComponent 
 * 3. Draw our graphics in the paintComponent method
 */

public class Game extends JKTNTPanel {
	private Image image;

	public Game(String text) {
		super();
		image = new ImageIcon("deadIsland.png").getImage();
	}
	
	@Override // This method already exists in JButton
	public void paintComponent(Graphics g) {
	    super.paintComponent(g); //Tells the JButton to do its own drawing

		// Starting here, draw our graphics
		// All graphics get drawn by starting with g.
		g.drawImage(image, 0, 0, 250,300, this);
		

	}
}
