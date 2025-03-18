package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MeteorPanel extends JPanel implements ActionListener {
	
	private static int WIDTH = 700, HEIGHT = 700, numAsteroids = 7;
	private static int numBooms = 9, boomsCount = 0, BGy = 0;
	private int delay = 30;
	private boolean alive = true, pause = false;
	private Thread gameThread;
	private Timer gameTimer;
	private BufferedImage BG;
	private Ship boss;
	private Meteors[] asteroids;
	private Explosion[] booms;
	private ArrayList<Laser> lasers;
	private Score score;
	private Sound laserSound, explode, alarm;

	public MeteorPanel()
	{
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setFocusable(true);
		
		addKeyListener(new AL());
		
		try {BG = ImageIO.read(new File("src/images/starfield.png"));}
		catch(Exception e) {e.printStackTrace();}
		
		boss = new Ship(WIDTH, HEIGHT);
		asteroids = new Meteors[numAsteroids];
		for(int i = 0; i < numAsteroids; i++){
			asteroids[i] = new Meteors(i, WIDTH, HEIGHT);
		}
		booms = new Explosion[numBooms];
		for(int i = 0; i < numBooms; i++){
			booms[i] = new Explosion(i);
		}
		
		lasers = new ArrayList<Laser>();
		score = new Score();
		laserSound = new Sound("src/images/Laser_Shoot.wav");
		explode = new Sound("src/images/rumble1.wav");
		alarm = new Sound("src/images/alarm.wav");
		
		gameTimer = new Timer(delay,this);
		gameTimer.start();
		
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		if(BGy >= HEIGHT){BGy = 0;}
		else {BGy += 2;}
		g2d.drawImage(BG, 0, BGy, null);
		g2d.drawImage(BG, 0, BGy-HEIGHT, null);
		if(alive) {
			move();
			draw(g2d);
		}
		else {
			try {Thread.sleep(200);}
			catch(InterruptedException e){e.printStackTrace();}
		booms[boomsCount%9].draw(g2d,boss.x);
		boomsCount++;
		if(boomsCount > 8){restart();}
		}
	}
	
	public void draw(Graphics2D g2d){
		boss.draw(g2d);
		score.draw(g2d);
		for(int i = 0; i < numAsteroids; i++) {asteroids[i].draw(g2d);}
		for(int i = 0; i < lasers.size(); i++) {lasers.get(i).draw(g2d);}
	}
	
	public void move(){
		boss.move();
		for(int i = 0; i < numAsteroids; i++) {asteroids[i].move();}
		for(int i = 0; i < lasers.size(); i++) {lasers.get(i).move();}
	}
	
	private void checkCollisions() {
		//Asteroids
		for(int i = 0; i < numAsteroids; i++) {
			if(asteroids[i].x <= -(asteroids[i].asteroid.getWidth())
					|| asteroids[i].x > WIDTH 
					|| asteroids[i].y > HEIGHT) {
				asteroids[i] = new Meteors(i, WIDTH,HEIGHT);
			}
		}
		
		//Asteroids and Lasers
		for(int i = 0; i < numAsteroids; i++) {
			Rectangle r1 = new Rectangle(asteroids[i].x,asteroids[i].y, asteroids[i].asteroid.getWidth(), asteroids[i].asteroid.getHeight());
			for(int j = 0; j < lasers.size(); j++) {
				Rectangle r2 = new Rectangle(lasers.get(j).x, lasers.get(j).y, lasers.get(j).laser.getWidth(), lasers.get(j).laser.getHeight());
				if(r1.intersects(r2)) {
					explode.play(-10);
					lasers.remove(j);
					if(i == 0) {score.score += 5;}
					else if(i <= 3) {score.score += 3;}
					else {score.score += 1;}
					asteroids[i] = new Meteors(i, WIDTH, HEIGHT);
				}
				
			}
		}
		
		//Asteroids and Ship
		for(int i = 0; i < numAsteroids; i++) {
			Rectangle r1 = new Rectangle(asteroids[i].x,asteroids[i].y, asteroids[i].asteroid.getWidth(), asteroids[i].asteroid.getHeight());
			Rectangle r2 = new Rectangle(boss.x, boss.y, boss.width, boss.height);
			if(r1.intersects(r2)) {
				score.lives--;
				alarm.play(5);
				asteroids[i] = new Meteors(i, WIDTH, HEIGHT);
				if(score.lives < 1) {
					gameOver();
				}
			}
		}
	}
	
	public void gameOver() {
		alive = false;
		explode.play(-10);
	}
	
	public void restart(){
		pause = true;
		boomsCount = 0;
		gameTimer.stop();
		int restart = JOptionPane.showConfirmDialog(null, "Play Again?", "Game over", JOptionPane.YES_NO_OPTION);
		if(restart == 0) {alive = true; pause = false; score = new Score(); boss = new Ship(WIDTH,HEIGHT); 
		for(int i = 0; i < numAsteroids; i++) {
			asteroids[i] = new Meteors(i, WIDTH,HEIGHT);
		} for(int i = lasers.size() - 1; i > -1; i-- ) {
			lasers.remove(i);
		}
		gameTimer.start(); 
		} 
		else {System.exit(0);}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		checkCollisions();
		repaint();
		
	}

	
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT ||e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_A ||e.getKeyCode() == KeyEvent.VK_D) {
				boss.keyPressed(e);
			}
			if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
				laserSound.play(-10);
				int xCoord = boss.x + (boss.ship.getWidth()/2);
				int yCoord = boss.y;
				lasers.add(new Laser(xCoord, yCoord));
			}
		}
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT ||e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_A ||e.getKeyCode() == KeyEvent.VK_D) {
				boss.keyReleased(e);
			}
		}
	}

}
