package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

	public class Ball extends Rectangle
	{
		int xPos, yPos;
		int xSpeed = 4, ySpeed = 4;
		private BufferedImage ball;
		Random rand = new Random();
		//int Speedvalue = 0;

	Ball()
	{
		xPos = 300;
		yPos = 250;
		loadImage();
	}

	
	private void loadImage()
	{
		try
		{
			ball = ImageIO.read(new File("src/images/redball.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void move()
	{
		
		xPos += xSpeed;
		if(xPos < 0)
	{
		xPos =0;
		xSpeed *= -1;
	}
		if(xPos > brickPanel.WIDTH - ball.getWidth())
		{
			xPos = brickPanel.WIDTH - ball.getWidth();
			xSpeed *= -1;
		}

		yPos += ySpeed;
		if(yPos < 0)
		{
			yPos =0;
			ySpeed *= -1;
		}
		if(yPos > brickPanel.HEIGHT - ball.getHeight())
		{
			yPos = brickPanel.HEIGHT - ball.getHeight();
			ySpeed *= -1;
		}
	}
		
	
	

	/*public void move1(int Speedx, int Speedy)
	{
		System.out.println(Speedx);
		xPos += Speedx;
		if(xPos < 0)
	{
		xPos =0;
		Speedx *= -1;
	}
		if(xPos >= brickPanel.WIDTH - ball.getWidth())
		{
			xPos = brickPanel.WIDTH - ball.getWidth();
			Speedx *= -1;
		}

		yPos += ySpeed;
		if(yPos < 0)
		{
			yPos =0;
			Speedy *= -1;
		}
		if(yPos >= brickPanel.HEIGHT - ball.getHeight())
		{
			yPos = brickPanel.HEIGHT - ball.getHeight();
			Speedy *= -1;
		}
	}*/
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(ball, xPos, yPos, null);
	}

	public Rectangle getBounds()
	{
		return new Rectangle(xPos, yPos, ball.getWidth(), ball.getHeight());
	}
}

