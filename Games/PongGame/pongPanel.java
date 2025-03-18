package Game;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class pongPanel extends JPanel implements Runnable 
{

	int WIDTH = 1000, HEIGHT = (int) (WIDTH * (5.0/9.0));
	private Dimension size = new Dimension(WIDTH, HEIGHT);
	static final int BALLDIAMETER = 20;
	static final int PWIDTH = 15;
	static final int PHEIGHT = 100;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	PongPaddle player1;
	PongPaddle player2;
	PongBall ball;
	PongScore score;
	
	public pongPanel() 
	{
		this.setPreferredSize(size);
		this.setLayout(null);
		newPaddles();
		newBall();
		score = new PongScore(WIDTH,HEIGHT);
		this.setFocusable(true);
		addKeyListener(new AL());
		gameThread = new Thread(this);
		gameThread.start();
	}

	private void newBall() 
	{	
		ball = new PongBall(WIDTH / 2 - BALLDIAMETER / 2, HEIGHT / 2 - BALLDIAMETER / 2, BALLDIAMETER, BALLDIAMETER);
	}

	private void newPaddles() 
	{ 
		player1 = new PongPaddle(3,(HEIGHT/2)-(PHEIGHT/2), PWIDTH,PHEIGHT, 1);
		player2 = new PongPaddle(WIDTH-PWIDTH-3,(HEIGHT/2)-(PHEIGHT/2), PWIDTH,PHEIGHT, 2);
	}
	
	public void paint(Graphics g)
	{
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	
	public void draw(Graphics g)
	{
		player1.draw(g);
		player2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	
	public void move()
	{
		player1.move();
		player2.move();
		ball.move();
	}
	
	public void checkCollision()
	{
		if(player1.y <= 0)
		{
			player1.y = 0;
		}
		if(player1.y > HEIGHT - PHEIGHT)
		{
			player1.y = HEIGHT - PHEIGHT;
		}
		if(player2.y <= 0)
		{
			player2.y = 0;
		}
		if(player2.y > HEIGHT - PHEIGHT)
		{
			player2.y = HEIGHT - PHEIGHT;
		}
		if(ball.y <= 0 || ball.y > HEIGHT - BALLDIAMETER)
		{
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.intersects(player1)||ball.intersects(player2))
		{
			ball.setXDirection(-ball.xVelocity);
		}
		
		if(ball.x<=0)
		{
			score.score2++;
			newBall();
		}
		
		if(ball.x>=WIDTH)
		{
			score.score1++;
			newBall();
		}
	}

	@Override
	public void run() 
	{
		long lastTime = System.nanoTime();
		int fps = 60;
		double ns = 1000000000/fps;
		double delta = 0;
		while(true)
		{
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if(delta >= 1)
			{
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}

	public class AL extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			player1.keyPressed(e);
			player2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e)
		{
			player1.keyReleased(e);
			player2.keyReleased(e);
		}
	}
}
