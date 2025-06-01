package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;

public class Paddle
{
private int xPos, yPos;
private int xSpeed = 10;
private BufferedImage paddle;
private Random rand = new Random();
private int velocity = 0;



Paddle()
{
loadImage();
}


private void loadImage()
{
try
{
paddle = ImageIO.read(new File("src/images/paddle.png"));
xPos = rand.nextInt(brickPanel.WIDTH - paddle.getWidth());
yPos = brickPanel.HEIGHT - paddle.getHeight();
}
catch (IOException e)
{
e.printStackTrace();
}
}

public void keyPressed(KeyEvent e)
{
if(e.getKeyCode() == KeyEvent.VK_LEFT)
{
setXDirection(-xSpeed);
move();
}
if(e.getKeyCode() == KeyEvent.VK_RIGHT)
{
setXDirection(xSpeed);
move();
}
}

public void keyReleased(KeyEvent e)
{
if(e.getKeyCode() == KeyEvent.VK_LEFT)
{
velocity = 0;
move();
}
if(e.getKeyCode() == KeyEvent.VK_RIGHT)
{
velocity = 0;
move();
}
}

public void move()
{
xPos += velocity;
if(xPos < 0)
{
xPos = 0;
}

if(xPos > brickPanel.WIDTH - paddle.getWidth())
{
xPos = brickPanel.WIDTH - paddle.getWidth();
}
}



private void setXDirection(int i)
{
velocity = i;
}

public void draw(Graphics g)
{
Graphics2D g2d = (Graphics2D) g;
g2d.drawImage(paddle, xPos, yPos, null);
}

public Rectangle getBounds()
{
return new Rectangle(xPos, yPos, paddle.getWidth(), paddle.getHeight());
}
}