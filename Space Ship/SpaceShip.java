package game;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

public class SpaceShip 
{
	
public int x,y, xDirection, yDirection;
Random rand = new Random();
public ImageIcon shipIcon;
public int shipNum;


SpaceShip()
{
	x = rand.nextInt(2);
	y = rand.nextInt(2);
	xDirection = rand.nextInt(2);
	yDirection = rand.nextInt(3);
	shipIcon = new ImageIcon("src/image/ship0.png");
}

//ship(int x, int y, int w, int z)
{
	
}
//


}

