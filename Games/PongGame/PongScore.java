package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PongScore 
{
	int WIDTH, HEIGTH;
	int score1, score2;
	
	PongScore(int WIDTH,int HEIGHT)
	{
		this.WIDTH = WIDTH;
		this.HEIGTH = HEIGHT;
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.setFont(new Font("Consolas",Font.PLAIN,60));
		g2.setStroke(new BasicStroke(5));
		g2.drawLine(WIDTH/2, 0, WIDTH/2, HEIGTH);
		g2.drawString(String.valueOf(score1/10)+String.valueOf(score1%10),WIDTH/2-85,50);
		g2.drawString(String.valueOf(score2/10)+String.valueOf(score2%10),WIDTH/2+20,50);
			
	}
}
