package Game;


import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SnakeFrame extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SnakeFrame frame = new SnakeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SnakeFrame() 
	{
		this.setTitle("Snake");
		ImageIcon icon = new ImageIcon("src/Image/icloud-download.png.png");
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(100, 40);
		setContentPane (new SnakePanel());
		this.pack();
	}
}

	