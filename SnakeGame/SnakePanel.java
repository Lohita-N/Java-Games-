package Game;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Arrays;
 

public class SnakePanel extends JPanel implements ActionListener

{

    private int SCREEN_WIDTH = 600, SCREEN_HEIGHT = 600;
    private int UNIT_SIZE = 25;
    private int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    private int  delay = 175;
    private int[] x = new int[GAME_UNITS];
    private int[] y = new int[GAME_UNITS];
    private int bodyParts = 3;
    private int fruitEaten = 0;
    private int fruitX, fruitY;
    private char direction = 'R';
    private boolean running = false;
    Timer timer;
    private Random rand = new Random();
    private int highScore=0;
    private String name = "";
    private int obsx,obsy,obs_width,obs_height;
    private Rectangle r,r1;

    public SnakePanel()

    {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    private void startGame()
    {
    	try {
			createHighScore();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	name = readName();
       	highScore = readHighScore();
       	JOptionPane.showMessageDialog(this, "High Score Scored by " + name + ":" + highScore);
       	newObstacle();
       	newFruit();
        running = true;
        timer = new Timer(delay, this);
        timer.start();
        
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g)
    {
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 35));
        g.drawString("Score: " + fruitEaten, SCREEN_WIDTH / 2 - 5, g.getFont().getSize());
        if (running)
        {
            g.setColor(Color.RED);
            g.fillOval(fruitX, fruitY, UNIT_SIZE, UNIT_SIZE);
            g.setColor(Color.GREEN);
            g.fillRect(obsx,obsy,obs_width,obs_height);
                     
            
            for(int i = 0; i < bodyParts; i++)
            {
                if(i ==0)
                {
                    g.setColor(Color.WHITE);
                }
                else
                {
                    g.setColor(Color.GREEN);
//                    g.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
                }
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                r1 = new Rectangle(x[0], y[0], UNIT_SIZE, UNIT_SIZE);
            }
            
        }
        else
        {
            gameOver(g);
        }
    }
 
    private void move()
    {
        for (int i = bodyParts; i > 0; i--)
        {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
         switch(direction)
        {
        case 'U':
            y[0] -= UNIT_SIZE;
            break;
          
        case 'D':
            y[0] += UNIT_SIZE;
            break;
          
        case 'L':
            x[0] -= UNIT_SIZE;
            break;
           
        case 'R':
            x[0] += UNIT_SIZE;
            break;
        }
    }
   
    private void newFruit()
    {
        fruitX = rand.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        fruitY = rand.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }
   private void newObstacle()
   {
	   obsx = fruitX+50;
       obsy = fruitY+50;
       obs_width = UNIT_SIZE;
       obs_height = UNIT_SIZE*4;   
       r = new Rectangle(obsx,obsy,obs_width,obs_height);
   }
    private void checkCollision()
    {
       
    	for(int i = bodyParts; i > 0; i--)
        {
            if((x[0] == x[i]) && (y[0] == y[i]))
            {
                running = false;
             }
        }
       
        if(x[0]>SCREEN_WIDTH || x[0]<0)
        {
            running = false;
          
        }
        if(y[0]>SCREEN_HEIGHT || y[0]<0)
        {
            running = false;
           
        }
      
        if(x[0] == fruitX && y[0] == fruitY)
        {
            bodyParts++;
            fruitEaten++;
            newFruit();
        }
       if(r.intersects(r1))
       {
    	   running = false;
       }
        if(!running)
        {
            timer.stop();
            writeHighScore();
           
        }
    }
   
    private void gameOver(Graphics g)
    {
      
        g.setColor(Color.RED);
       g.setFont(new Font("Ink Free", Font.BOLD, 75));
       FontMetrics font_me2 = getFontMetrics(g.getFont());
       g.drawString("Game Over", (SCREEN_WIDTH - font_me2.stringWidth("Game Over")) / 2,
               SCREEN_HEIGHT/2);
               g.setColor(Color.GREEN);
       g.setFont(new Font("Ink Free", Font.BOLD, 40));
       FontMetrics font_me3 = getFontMetrics(g.getFont());
       g.drawString("Press R to Replay and E to Exit", (SCREEN_WIDTH - font_me3.stringWidth("Press R to Replay and E to Exit")) / 2, SCREEN_HEIGHT / 2-150);
             
    }
 
 
    public class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch(e.getKeyCode())
            {
            case KeyEvent.VK_UP:
                if(direction != 'D')
                {
                    direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if(direction != 'U')
                {
                    direction = 'D';
                }
                break;
            case KeyEvent.VK_LEFT:
                if(direction != 'R')
                {
                    direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(direction != 'L')
                {
                    direction = 'R';
               }
                break;
            case KeyEvent.VK_R:
                if(!running){
                    fruitEaten=0;
                    bodyParts=3;
                    direction='R';
                 Arrays.fill(x,0);
                   Arrays.fill(y,0);
                    startGame();
                }
                break;
            case KeyEvent.VK_E:
                if(!running){
                    System.exit(0);
                }
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (running)
        {
        	move();
            checkCollision();
          }
        repaint();
    }
public void createHighScore() throws IOException
{
	 File save = new File("Highscores.txt");
	if (!save.exists()){
        save.createNewFile();
        PrintWriter writer = new PrintWriter("Highscores.txt", "UTF-8");
        writer.println("Nobody");
        writer.println(String.valueOf(0));
        writer.close();
    }
	   
	    }

public void writeHighScore()
{
	if(fruitEaten > highScore)
    {
		 String name1 = JOptionPane.showInputDialog("You Set a highscore. What is your name?");
		    highScore = fruitEaten;
		    name = name1;
	try{
        PrintWriter writer = new PrintWriter("Highscores.txt", "UTF-8");
        writer.println(name);
        writer.println(String.valueOf(highScore));
        writer.close();
    }catch(Exception e){
        e.printStackTrace();
    }
	finally {
		
	}}
	   }
	
public int readHighScore()
{
	BufferedReader in = null;
	int t = 0;
	try {
		in = new BufferedReader(new FileReader("Highscores.txt"));
	} catch (FileNotFoundException e) {
				e.printStackTrace();
	}
    String line = null;
       try {
    	line = in.readLine();
		line = in.readLine();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
    t = Integer.parseInt(line);
     try {
		in.close();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
     return t;
}
public String readName()
{
	BufferedReader in = null;
	String name1;
	try {
		in = new BufferedReader(new FileReader("Highscores.txt"));
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
    String line = null;
    try {
		line = in.readLine();
	} catch (IOException e) {
				e.printStackTrace();
	}
    name1 = line;
    
     try {
		in.close();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
     return name1 ;
}

}


