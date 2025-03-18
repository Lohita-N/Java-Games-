package game;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MeteorFrame extends JFrame {


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeteorFrame frame = new MeteorFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MeteorFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(200, 0);
		ImageIcon icon = new ImageIcon("src/images/m.png");
		this.setIconImage(icon.getImage());
		this.setTitle("Meteors");
		this.setResizable(false);
		this.setLayout(null);
		this.setContentPane(new MeteorPanel());
		this.pack();
	}

}
