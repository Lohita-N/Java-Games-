package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Graphics;

import javax.swing.JPanel;

public class shipPanel extends JPanel{
	private int SCREEN_WIDTH = 600, SCREEN_HEIGHT = 600;
	//public SpaceShip ship[] = new SpaceShip[4]; 
	public SpaceShip ship = new SpaceShip();
	
	/**
	 * Create the panel.
	 */
	public shipPanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setFocusable(true);

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g)
	{
		
		ship.shipIcon.paintIcon(this, g, ship.x, ship.y);
		/*for(int i=0;i<4;i++)
		{
			
			ship[i].shipIcon.paintIcon(this, g, ship[i].x,ship[i].y);
			System.out.println(ship[i].x);
		}*/
		
	}
}
