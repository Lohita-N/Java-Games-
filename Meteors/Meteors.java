package game;

import java.util.Random;
import java.awt.geom.AffineTransform;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class Meteors
{
public int x, y, xVel, yVel;
public int width, height, rotate;
private Random rand;
public BufferedImage asteroid;

Meteors(int numImage, int width, int height)
{
loadImge(numImage);
rand = new Random();
x = rand.nextInt(width);
if(rand.nextBoolean())
{
xVel = rand.nextInt(3) + 1;
}
else
{
xVel = -(rand.nextInt(3) + 1);
}

y = -(rand.nextInt(asteroid.getHeight()));
yVel = (rand.nextInt(3) + 1);
rotate = 2;
}

private void loadImge(int numImage)
{
try
{
asteroid = ImageIO.read(new File("src/images/asteroid" + numImage + ".png"));
}
catch(Exception e)
{
e.printStackTrace();
}
}

public void move()
{
x += xVel;
y += yVel;
rotate += 2;
}

public void draw(Graphics2D g2d)
{
AffineTransform at = AffineTransform.getTranslateInstance(x, y);
at.rotate(Math.toRadians(rotate), asteroid.getWidth()/2, asteroid.getHeight()/2);
g2d.drawImage(asteroid, at, null);
}
}
