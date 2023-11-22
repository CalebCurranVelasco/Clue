package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class GameControlPanel extends JPanel {
	private JTextField theGuess;
	private JTextField guessResult;
	private JTextField text;
	private JTextField turn;
	private JTextField roll;
	private Player currPlayer;
	private static Board board;
	
	
	class NextPlayerListener implements ActionListener {
		private Board board;
		private JTextField currPlayer;
		private JTextField roll;

		public NextPlayerListener(Board board, JTextField currPlayer, JTextField roll) {
			this.board = board;
			this.currPlayer = currPlayer;
			this.roll = roll;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (board.getCurrPlayer().isHuman() == true) {
				int ans = JOptionPane.showConfirmDialog(null, "Are you done with your turn?", 
						"Yo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (ans == JOptionPane.YES_OPTION) {
					updateGame();
				} 
			} else {

				board.calcTargets(board.getCell(board.getCurrPlayer().getRow(), board.getCurrPlayer().getCol()), board.getRoll());
				BoardCell newTarget = board.getCurrPlayer().selectTarget(board.getTargets(), board.getRoomMap(), board.getCardDeck());
				if (newTarget != null) {
					board.getCurrPlayer().setCol(newTarget.getColumn());
					board.getCurrPlayer().setRow(newTarget.getRow());
				}

				updateGame();
			}
		}



		private void updateGame() {
			String newRoll = Integer.toString(board.roll());
			roll.setText(newRoll);
			board.updateTurn();
			currPlayer.setText(board.getCurrPlayer().getName());
			currPlayer.setBackground(board.getCurrPlayer().getColor());
			board.getBoardPanel().repaint();
		}

	}


	/*
	 * The Game Control Panel creates the full GUI panel that displays
	 * using a 2 x 1 matrix layout
	 */
	public GameControlPanel(Board board) {
		this.board = board;  // create the panel

		setLayout(new GridLayout(2,1));
		JPanel topHalfPanel = createTopHalfPanel();	// Displays top half of display
		JPanel botHalfPanel = createBotHalfPanel();	// Displays bottom half of display

		add(topHalfPanel);
		add(botHalfPanel);
	}

	/*
	 * This function creates the top half panel which includes 
	 * the name and roll panels. The buttons are created and
	 * included in this panel.
	 */
	private JPanel createTopHalfPanel() {
		JPanel topHalfPanel = new JPanel();
		topHalfPanel.setLayout(new GridLayout(1, 4));

		JPanel userPanel = createNamePanel();
		JPanel rollPanel = createRollPanel();

		JButton nextPlayerButton = new JButton("NEXT!");
		NextPlayerListener nextPlayerListener = new NextPlayerListener(board, turn, roll);
		nextPlayerButton.addActionListener(nextPlayerListener);
		JButton makeAccusationButton = new JButton("Make Accusation");

		topHalfPanel.add(userPanel);
		topHalfPanel.add(rollPanel);
		topHalfPanel.add(makeAccusationButton);
		topHalfPanel.add(nextPlayerButton);

		return topHalfPanel;
	}


	/*
	 * This function creates the name panel for displaying
	 * the current turn and which player's turn it is.
	 */
	private JPanel createNamePanel() {
		JPanel namePanel = new JPanel();
		// Use a grid layout, 1 row, 2 elements (label, text)
		namePanel.setLayout(new GridLayout(2,1));
		namePanel.add(new JLabel("Whose turn?"));
		turn = new JTextField(board.getCurrPlayer().getName());
		turn.setBackground(board.getCurrPlayer().getColor());
		turn.setEditable(false);
		namePanel.add(turn);
		board.setHumanTurn(true);
		return namePanel;
	}

	/*
	 * This function creates the roll panel for displaying 
	 * the roll number.
	 */
	private JPanel createRollPanel() {
		JPanel rollPanel = new JPanel();
		// Use a grid layout, 1 row, 2 elements (label, text)
		rollPanel.setLayout(new GridLayout(2, 2)); // maybe change to grid 1,2
		rollPanel.add(new JLabel("Roll: "), BorderLayout.WEST);
		roll = new JTextField(Integer.toString(board.roll()));
		roll.setEditable(false);
		rollPanel.add(roll);
		return rollPanel;
	}

	/*
	 * This function creates the bottom half panel which includes
	 * the guess panel.
	 */
	private JPanel createBotHalfPanel() {
		JPanel botHalfPanel = new JPanel();
		botHalfPanel.setLayout(new GridLayout(1,2));

		JPanel guessPanel = createGuessPanel("Guess");
		JPanel guessResultPanel = createGuessPanel("Guess Result");

		botHalfPanel.add(guessPanel);
		botHalfPanel.add(guessResultPanel);

		return botHalfPanel;
	}

	/*
	 * This function creates the guess panel which displays
	 * the guess result.
	 */
	private JPanel createGuessPanel(String title) {
		JPanel guessPanel = new JPanel();
		guessPanel.setLayout(new GridLayout(0, 1));
		TitledBorder panelTitle = BorderFactory.createTitledBorder(title);
		guessPanel.setBorder(panelTitle);

		if (title.equals("Guess")) {
			theGuess = new JTextField();
			theGuess.setEditable(false);	// Refactor Item
			guessPanel.add(theGuess);
		} else {
			guessResult = new JTextField();
			guessResult.setEditable(false);
			guessPanel.add(guessResult);
		}
		return guessPanel;
	}

	public void setGuess(String guess) {
		theGuess.setText(guess);
	}

	/*
	 * This function sets our instance variables properly
	 * so that we may use the player's information such as
	 * name and dice roll.
	 */
	
//	private void setTurn(Player player, Integer roll) {
//		currPlayer = player;
//		turn.setText(currPlayer.getName());
//		turn.setBackground(currPlayer.getColor());
//		this.roll.setText(String.valueOf(roll));
//	}

	public void setGuessResult(String guessResult) {
		this.guessResult.setText(guessResult);
	}

	public void setText(String g) {
		this.setText(g);
	}

	
}
