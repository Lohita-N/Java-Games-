package game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import game.shipPanel;

public class shipFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					shipFrame frame = new shipFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public shipFrame() {
		{
			this.setTitle("Space Ship");
			ImageIcon icon = new ImageIcon("src/images/icloud-download.png.png");
			setIconImage(icon.getImage());
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocation(50,50);
			this.setResizable(false);
			this.setLayout(null);
			panel = new shipPanel();
			setContentPane(panel);
			pack();
		}
	}
}
	
