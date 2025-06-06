package Game;

import java.awt.Rectangle;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
//import java.awt.event.KeyEvent;


	public class PongBall extends Rectangle
	{
		Random rand = new Random();
		int xVelocity;
		int yVelocity;
		int speed = 3;

	PongBall(int x, int y, int WIDTH, int HEIGHT)
	{
		super(x, y, WIDTH, HEIGHT);

		int x1 = rand.nextInt(2);
		if(x1 == 0)
		{
			setXDirection (-speed);
		}
		else
		{
			setXDirection(speed);
		}

		int y1 = rand.nextInt(2);
		if(y1 == 0)
		{
			setYDirection (-speed);
		}
		else
		{
			setYDirection(speed);
		}
	}

	public void setYDirection(int i)
	{
		yVelocity = i;
	}

	public void setXDirection(int i)
	{
		xVelocity = i;
	}

	public void move()
	{
		x += xVelocity;
		y += yVelocity;
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillOval(x, y, width, height);
	}
	}
	