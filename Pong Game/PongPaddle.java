package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class PongPaddle extends Rectangle
{
	int id;
	int yVelocity;
	int speed = 10;
	
	PongPaddle(int x, int y, int PWIDTH, int PHEIGHT, int id)
	{
		super(x,y,PWIDTH, PHEIGHT);
		this.id = id;
	}
	
	public void keyPressed(KeyEvent e)
	{
		switch(id)
		{
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W)
			{
				setYDirection(-speed);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S)
			{
				setYDirection(speed);
				move();
			}
			break;
			
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP)
			{
				setYDirection(-speed);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				setYDirection(speed);
				move();
			}
			break;
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		switch(id)
		{
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W)
			{
				setYDirection(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S)
			{
				setYDirection(0);
				move();
			}
			break;
			
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP)
			{
				setYDirection(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				setYDirection(0);
				move();
			}
			break;
		}
	}

	public void move() 
	{
		y = y + yVelocity;		
	}

	private void setYDirection(int i) 
	{
		yVelocity = i;
	}
	
	public void draw(Graphics g) 
	{
		if(id == 1)
		{
			g.setColor(Color.BLUE);
		}
		else
		{
			g.setColor(Color.RED);
		}
		
		g.fillRect(x, y, width, height);
	}
}
