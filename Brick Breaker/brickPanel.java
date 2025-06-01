package game;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class brickPanel extends JPanel implements Runnable
{
static final int WIDTH = 700, HEIGHT = 650;
private final int numRows = 7, numCols = 7;
private Image image;
private Thread thread;
private Graphics graphics;
private Bricks[][] bricks;
private Paddle paddle;
private Ball ball,ball1;
int newball = 0;
int newSpeed = 0;
//int Speedx = 5;
//int Speedy = 5;

public brickPanel()
{
addKeyListener(new MyKey());
this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
gameSetup();
this.setFocusable(true);
Music music = new Music();
music.start();
this.thread = new Thread(this);
thread.start();
}

private void gameSetup()
{
paddle = new Paddle();
ball = new Ball();
ball1 = new Ball();
bricks = new Bricks[numRows][numCols];
for(int rows = 0; rows < numRows; rows++)
{
for(int cols = 0; cols < numCols; cols++)
{
bricks[rows][cols] = new Bricks(rows, cols);
}
}
}

public void move()
{
paddle.move();
ball.move();
if (newball==1)
{
	ball1.move();
}
}


private void checkCollision()
{
Rectangle ballRect = ball.getBounds();
Rectangle pRect = paddle.getBounds();
Rectangle ball1Rect = ball1.getBounds();

if (ballRect.y + ballRect.height >= pRect.y && ballRect.getCenterX()
> pRect.x && ballRect.getCenterX() < pRect.x + pRect.width)
{
ball.ySpeed *= -1.5;
}

for(int row = 0; row < numRows; row++)
{
for(int col = 0; col < numCols; col++)
{
if(bricks[row][col].visible)
{
Rectangle brickRect = bricks[row][col].getBounds();
if(ballRect.y <= brickRect.y + brickRect.height
&& ballRect.x > brickRect.x + ballRect.width-5
&& ballRect.x - 5 < brickRect.x + brickRect.width)
{
ball.ySpeed *= -1;
bricks[row][col].visible = false;
}
}
}
}
if (bricks[6][1].visible == false)
{
	newball =1;
	if (ball1Rect.y + ball1Rect.height >= pRect.y && ball1Rect.getCenterX()
			> pRect.x && ball1Rect.getCenterX() < pRect.x + pRect.width)
			{
			ball1.ySpeed *= -1;
			}

			for(int row = 0; row < numRows; row++)
			{
			for(int col = 0; col < numCols; col++)
			{
			if(bricks[row][col].visible)
			{
			Rectangle brickRect = bricks[row][col].getBounds();
			if(ball1Rect.y <= brickRect.y + brickRect.height
			&& ball1Rect.x > brickRect.x + ball1Rect.width-5
			&& ball1Rect.x - 5 < brickRect.x + brickRect.width)
			{
			ball1.ySpeed *= -1;
			bricks[row][col].visible = false;
			}
			}
			}
			}
}


}
public void paint(Graphics g)
{
image = createImage(getWidth(), getHeight());
graphics = image.getGraphics();
draw(graphics);
g.drawImage(image, 0, 0, this);
}

private void draw(Graphics g)
{
for(int row = 0; row < numRows; row++)
{
for(int col = 0; col < numCols; col++)
{
bricks[row][col].draw(g);
}
}
ball.draw(g);
paddle.draw(g);
if (newball == 1)
{
	ball1.draw(g);
}
}

@Override
public void run()
{
long lastTime = System.nanoTime();
int fps = 60;
double ns = 1000000000 / fps;
double delta = 0;
while(true)
{
long now = System.nanoTime();
delta += (now-lastTime) / ns;
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



public class MyKey extends KeyAdapter
{
public void keyPressed(KeyEvent e)
{
paddle.keyPressed(e);
}
public void keyReleased(KeyEvent e)
{
paddle.keyReleased(e);
}
}

}
