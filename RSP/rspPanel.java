package game;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.util.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class rspPanel extends JPanel implements ActionListener
{
	private int WIDTH = 900;
	private int HEIGHT = 650;
	private Dimension SIZE = new Dimension(WIDTH,HEIGHT);
	private JLabel titleLBL,player,computer;
	private ImageIcon paper = new ImageIcon ("src/images/paper.png");
	private ImageIcon rock = new ImageIcon ("src/images/rock.png");
	private ImageIcon scissor = new ImageIcon ("src/images/scissors.png");
	private JButton rockButton, paperButton, scissorsButton,rockButton1,paperButton1,scissorsButton1;    
	private JPanel P1,P2,P3,P4;

	
	
	public rspPanel()
	{
		setForeground(Color.BLACK);
		setBackground(Color.BLACK);
		setPreferredSize(SIZE);
		setLayout(new BorderLayout());
		this.createGUI();
		showStart();	
		int Welcome = JOptionPane.showConfirmDialog(null, "Welcome to the Rock, Paper, Scissor. Click one of the three choices! Let's see who is going to win!","Welcome",
				JOptionPane.YES_NO_OPTION);
			if (Welcome != 0)
			{
				System.exit(0);
			}
	}
	
	private void createGUI() {
		this.removeAll();
		P1 = new JPanel();
		P1.setBackground(Color.BLACK);
		P1.setForeground(Color.BLACK);
		
		titleLBL = new JLabel("Rock Paper Scissors");
		titleLBL.setFont(new Font("Tahoma", Font.BOLD, 40));
		titleLBL.setForeground(Color.WHITE);
		titleLBL.setBounds(267, 40, 361, 41);
		
		P1.add(titleLBL);
		P2 = new JPanel();
		P2.setLayout(new GridLayout(1,2));
		
		P3 = new JPanel();
		P3.setBackground(Color.BLACK);
		P3.setForeground(Color.WHITE);
		P3.setLayout(new GridLayout(4,1));
		
		player = new JLabel("Player");
		//player.setBackground(Color.BLACK);
		player.setFont(new Font("Tahoma", Font.PLAIN, 50));
		player.setHorizontalAlignment(SwingConstants.CENTER);
		player.setForeground(Color.WHITE);
		
		P3.add(player);
		rockButton = new JButton(rock);
		rockButton.setBackground(Color.LIGHT_GRAY);
		P3.add(rockButton);
		rockButton.addActionListener(this);
		
	    paperButton = new JButton(paper);
	    paperButton.setBackground(Color.LIGHT_GRAY);
	    P3.add(paperButton);
	    
	    paperButton.addActionListener(this);
	    scissorsButton = new JButton(scissor);
	    scissorsButton.setBackground(Color.LIGHT_GRAY);
	    P3.add(scissorsButton);
	    scissorsButton.addActionListener(this);
	    
	    P4 = new JPanel();
	    P4.setForeground(Color.WHITE);
	    P4.setBackground(Color.BLACK);
	    P4.setLayout(new GridLayout(4,1));
	    
		computer = new JLabel("Computer");
		computer.setForeground(Color.WHITE);
		computer.setFont(new Font("Tahoma", Font.PLAIN, 50));
		computer.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		P4.add(computer);
		rockButton1 = new JButton(rock);
		rockButton1.setBackground(Color.LIGHT_GRAY);
		P4.add(rockButton1);
		
	    paperButton1 = new JButton(paper);
	    paperButton1.setBackground(Color.LIGHT_GRAY);
	    P4.add(paperButton1);
	    
	    scissorsButton1 = new JButton(scissor);
	    scissorsButton1.setBackground(Color.LIGHT_GRAY);
	   
	    P4.add(scissorsButton1);
        P2.add(P3);
        P2.add(P4);
        
        this.add(P1,BorderLayout.NORTH);
        this.add(P2,BorderLayout.CENTER);
        validate();
     repaint();
        
		
	}
	
	
	
	@ Override
	public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        int playerChoice; 
        int compChoice;   
        Random randomSeed = new Random(); 
        if (source == rockButton) { 
            playerChoice = 0;
            rockButton.setBackground(Color.black);
               }
        else if (source == paperButton){ 
            playerChoice = 1;
            paperButton.setBackground(Color.black);
        } else {
            playerChoice = 2; 
            scissorsButton.setBackground(Color.black);
        }
        compChoice = randomSeed.nextInt(3); 
        if (compChoice == 0) { 
            rockButton1.setBackground(Color.red);
  } else if (compChoice == 1) {
     
      paperButton1.setBackground(Color.red);
  } else {
     
      scissorsButton1.setBackground(Color.red);
  }
        findWinner(playerChoice, compChoice);
        

        

    }
	
	private void findWinner(int playerChoice, int compChoice) {
        
        if (playerChoice == compChoice) { 
        	int answer = JOptionPane.showConfirmDialog(null, "Its a tie!! Play Again?", "Game Over",
					JOptionPane.YES_NO_OPTION);
				if (answer==0)
				{
					startOver();
				}
				else
					{
					System.exit(0);
					}
        } else if ((playerChoice == 0 && compChoice == 1) || 
        (playerChoice == 1 && compChoice == 2) ||
        (playerChoice == 2 && compChoice == 0))
        
        {
        	int answer1 = JOptionPane.showConfirmDialog(null, "Computer Won!! Play Again?", "Game Over",
					JOptionPane.YES_NO_OPTION);
				if (answer1==0)
				{
					startOver();
				}
				else
					{
					System.exit(0);
					} 
        } else {
        	int answer2 = JOptionPane.showConfirmDialog(null, "Player Won!! Play Again?", "Game Over",
					JOptionPane.YES_NO_OPTION);
				if (answer2==0)
				{
					startOver();
				}
				else
					{
					System.exit(0);
					}
        } 
    }
    
	private void startOver()
	{
	 this.createGUI();
		}
    private void showStart()
	{
		int answer1 = JOptionPane.showConfirmDialog(null,"Start the Game!!", "Welcome to Rock Paper Scissors",
				JOptionPane.YES_NO_OPTION);
		if (answer1 != 0)
		{
			System.exit(0);
		}
				
	}
	
}

