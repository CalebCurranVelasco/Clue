package clueGame;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GameControlPanel extends JPanel {
	private String theGuess;
	private String text;
	private JTextField name;
	
	private static final long serialVersionUID = 1L;

	public GameControlPanel() {
		
		// Create a layout with 2 rows
		setLayout(new GridLayout(2,0));
		JPanel userPanel = createUserPanel();	// Creates the panel containing player turn, roll, and buttons
		add(userPanel);
		userPanel = createGuessPanel();
		add(userPanel);
		
		
	}

	
	/*
	 * The function below creates a new JPanel for displaying 
	 * the player's turn, the dice roll, and the buttons.
	 */
//	private JPanel createUserPanel() {	
//		setLayout(new GridLayout(1, 4));
//		JPanel newPanel = new JPanel();
//		newPanel = createNamePanel();
//		add(newPanel);
//		newPanel = createButtonPanel();
//		add(newPanel);
//		
//		
//		return newPanel;
//	}
	
//	private JPanel createGuessPanel() {
//		setLayout(new GridLayout(0, 2));
//		JPanel guessPanel = new JPanel();
//		
//		
//		return guessPanel;
//		
//	}
//
//
//
//	private JPanel createButtonPanel() {
//		// no layout specified, so this is flow
//		JButton agree = new JButton("Make Accusation");
//		JButton disagree = new JButton("NEXT!");
//		JPanel panel = new JPanel();
//		panel.add(agree);
//		panel.add(disagree);
//		return panel;		
//	}
//
//	private JPanel createNamePanel() {
//		JPanel panel = new JPanel();
//		// Use a grid layout, 1 row, 2 elements (label, text)
//		panel.setLayout(new GridLayout(2,1));
//		JLabel nameLabel = new JLabel("Whose turn?");
//		name = new JTextField(20);
//		panel.add(nameLabel);
//		panel.add(name);
//		panel.setBorder(new TitledBorder (new EtchedBorder(), "Who are you?"));
//		return panel;
//	}

	private void setTurn(ComputerPlayer computerPlayer, int i) {
		
		
	}
	
//	public void setGuess(String guess) {
//	    theGuess.setText(guess);
//	}
	
	public void setText(String g) {
		this.text = g;
	}


	public static void main(String[] args) {
		GameControlPanel panel = new GameControlPanel();  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(750, 180);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
		
//		// test filling in the data
//		panel.setTurn(new ComputerPlayer( "Col. Mustard", 0, 0, "orange"), 5);
//		panel.setGuess( "I have no guess!");
//		panel.setGuessResult( "So you have nothing?");
//		// TODO Auto-generated method stub

	}


	
}
