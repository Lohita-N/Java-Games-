package game;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

	public class brickFrame extends JFrame
	{


		public static void main(String[] args)
		{
			EventQueue.invokeLater(new Runnable()
		{
		public void run()
		{
			try
		{
		brickFrame frame = new brickFrame();
		frame.setVisible(true);
		} 
		catch (Exception e)
		{
		e.printStackTrace();
		}
	}
});
}

/**
* Create the frame.
*/
		public brickFrame() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ImageIcon icon = new ImageIcon("src/images/m.png");
			this.setIconImage(icon.getImage());
			this.setTitle("Brick Breaker!");
			this.setLocation(300, 0);
			this.setResizable(false);
			setContentPane(new brickPanel());
			this.pack();
		}

	}
