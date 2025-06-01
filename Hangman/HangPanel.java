package Games;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class HangPanel extends JPanel implements ActionListener 
{
	private int WIDTH = 700;
	private int HEIGHT = 540;
	private Dimension SIZE = new Dimension(WIDTH,HEIGHT);
	private JLabel titleLBL, hangLBL, wordLBL, asterisksLBL, pickLBL, lgLBL, guessedLBL, updatesLBL;
	private JTextField textField;
	private String asterisks="";
	private String solved;
	private String guessedLetters ="";
	private String alphabet="abcdefghijklmnopqrstuvwxyz";
	private int numWrongGuesses=0;
	private ImageIcon win = new ImageIcon("src/files/trophy.png");
	private ImageIcon rip = new ImageIcon("src/files/RIP.jpg");
	
	
	
	public HangPanel() 
	{
		setBackground(Color.PINK);
		setPreferredSize(SIZE);
		setLayout(null);
		
		solved=GetWord.guessWord();
		for(int i=0; i<solved.length();i++)
		{
			asterisks+="*";
		}
		System.out.println(solved);
		
		
		titleLBL = new JLabel("HangMan");
		titleLBL.setFont(new Font("Lucida Bright", Font.BOLD, 41));
		titleLBL.setBounds(0, 20, 350, 54);
		titleLBL.setHorizontalAlignment(JLabel.CENTER);
		add(titleLBL);
		
		hangLBL=new JLabel("");
		hangLBL.setIcon(new ImageIcon("src/files/0.jpg"));
		hangLBL.setBounds(350,0,350,541);
		add(hangLBL);
		
		wordLBL=new JLabel("Word to Guess");
		wordLBL.setBounds(0,85,350,31);
		wordLBL.setFont(new Font("Times New Roman",Font.BOLD,25));
		wordLBL.setHorizontalAlignment(JLabel.CENTER);
		add(wordLBL);
		
		asterisksLBL=new JLabel();
		asterisksLBL.setBounds(0,118,350,31);
		asterisksLBL.setFont(new Font("Times New Roman",Font.BOLD,25));
		asterisksLBL.setHorizontalAlignment(JLabel.CENTER);
		asterisksLBL.setText(asterisks);
		add(asterisksLBL);
		
		pickLBL=new JLabel("Please Pick a Letter");
		pickLBL.setFont(new Font("Times NEw Roman",Font.BOLD,25));
		pickLBL.setHorizontalAlignment(JLabel.CENTER);
		pickLBL.setBounds(0,186,350,31);
		add(pickLBL);
		
		textField= new JTextField();
		textField.setBounds(148,221,15,20);
		textField.addActionListener(this);
		add(textField);
		textField.setColumns(10);
		
		lgLBL = new JLabel("Letters Guessed");
		lgLBL.setFont(new Font("Time New Romen", Font.BOLD,25));
		lgLBL.setHorizontalAlignment(JLabel.CENTER);
		lgLBL.setBounds(0,270,350,31);
		add(lgLBL);
		
		guessedLBL= new JLabel();
		guessedLBL.setFont(new Font("Time New Romen",Font.BOLD,25));
		guessedLBL.setHorizontalAlignment(JLabel.CENTER);
		guessedLBL.setBounds(0,315,340,31);
		add(guessedLBL);
		
		updatesLBL = new JLabel();
		updatesLBL.setBounds(0,351,350,190);
		add(updatesLBL);
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String guess = textField.getText().toLowerCase();
		if (guess.length()>0)
		{
			checkGuess(guess.substring(0,1));
		}
		
		hangLBL.setIcon(new ImageIcon("src/files/"+numWrongGuesses+".jpg"));
		asterisksLBL.setText(asterisks);
		guessedLBL.setText(guessedLetters);
		textField.setText("");
		
		if(checkGameOver())
		{
			int restart = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
			if(restart ==0)
			{
				gameRestart();
			}
			else
			{
				System.exit(0);
			}
		}
	}
	
	private void checkgameover()
	{
		if(checkGameOver())
		{
			int restart = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Game over", JOptionPane.YES_NO_OPTION);
			if(restart ==0)
			{
				gameRestart();
			}
			else
			{
				System.exit(0);
			}
		}
	}
	

	private void gameRestart() 
	{
		numWrongGuesses=0;
		solved = GetWord.guessWord();
		System.out.println(solved);
		asterisks = "";
		for(int x = 0;x<solved.length();x++);
		{
			asterisks+="*";
		}
		guessedLetters="";
		guessedLBL.setText(guessedLetters);
		updatesLBL.setIcon(null);
	}

	private boolean checkGameOver() 
	{
		if(solved.equals(asterisks))
		{
			updatesLBL.setIcon(win);
			return true;
		}
		else if(numWrongGuesses==7)
		{
			updatesLBL.setIcon(rip);
		}
		return false;
	}

	private void checkGuess(String temp) 
	{
		if(alphabet.indexOf(temp)>=0)
		{
			if(guessedLetters.indexOf(temp)==-1)
			{
				guessedLetters+=temp;
			}
			if(solved.indexOf(temp)>=0)
			{
				for(int i=0; i<solved.length();i++)
				{
					if(solved.charAt(i) == temp.charAt(0))
					{
						asterisks=asterisks.substring(0,i)+temp+asterisks.substring(i+1);
					}
				}
			}
			else
			{
				numWrongGuesses++;
			}
		}
	}
}
