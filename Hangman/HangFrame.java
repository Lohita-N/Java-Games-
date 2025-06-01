package Games;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HangFrame extends JFrame {

	private HangPanel contentPane;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					HangFrame frame = new HangFrame();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HangFrame() 
	{
		this.setTitle("HangMan");
		ImageIcon icon = new ImageIcon("src/images/icloud-download.png.png");
		setIconImage(icon.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(100,100);
		this.setResizable(false);
		this.setLayout(null);
		contentPane =new HangPanel();
		
		setContentPane(contentPane);
		pack();
	}

}