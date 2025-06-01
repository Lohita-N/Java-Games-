package Game;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.pongPanel;

public class pongFrame extends JFrame {

	private JPanel panel;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					pongFrame frame = new pongFrame();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	
	public pongFrame() 
	{
		this.setTitle("Pong Game");
		ImageIcon icon = new ImageIcon("src/images/icloud-download.png.png");
		setIconImage(icon.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(50,50);
		this.setResizable(false);
		this.setLayout(null);
		panel = new pongPanel();
		
		setContentPane(panel);
		pack();
	}

}