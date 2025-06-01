
package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.Rectangle;
import java.util.Random;

import javax.imageio.ImageIO;

public class Bricks
{
private int xPos, yPos;
private BufferedImage bricks;
public boolean visible = true;
private Random rand = new Random();

Bricks(int row, int col)
{
createBrick(row, col);
}

private void createBrick(int y, int x)
{

try
{
int i = rand.nextInt(10);
bricks = ImageIO.read(new File("src/images/b" + i + ".png"));
}
catch (IOException e)
{
e.printStackTrace();
}

xPos = x * bricks.getWidth();
yPos = y * bricks.getHeight();
}

public void draw(Graphics g)
{
if(visible)
{
Graphics2D g2d = (Graphics2D) g;
g2d.drawImage(bricks, xPos, yPos, null);
}
}

public Rectangle getBounds()
{
return new Rectangle(xPos, yPos, bricks.getWidth(), bricks.getHeight());
}
}