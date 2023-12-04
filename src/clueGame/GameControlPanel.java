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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class GameControlPanel extends JPanel {
	private static JTextField theGuess;
	private static JTextField guessResult;
	private JTextField text;
	private JTextField turn;
	private JTextField roll;
	private Player currPlayer;
	private static Board board;
	private boolean canceled;
	private CardsPanel cardsPanel;
	
	
	class MakeAccusationListener implements ActionListener {
		private static Board board;
		private JTextField currPlayer;
		private JTextField roll;

		public MakeAccusationListener(Board board, JTextField currPlayer) {
			this.board = board;
			this.currPlayer = currPlayer;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// TODO Auto-generated method stub
			if (board.getCurrPlayer().isHuman()) {
				canceled = false;
				AccusationDialog accusationDialog = new AccusationDialog();
				accusationDialog.setVisible(true);
				
				if (!canceled) {
					Solution possibleSolution = accusationDialog.getSolution();
					if (board.checkAccusation(possibleSolution.getPerson(), possibleSolution.getWeapon(), possibleSolution.getRoom())) {
						// win
						
						JOptionPane.showMessageDialog(null, "Person: " + possibleSolution.getPerson() + 
								"\nWeapon: " + possibleSolution.getWeapon() + 
								"\nRoom: " + possibleSolution.getRoom() +
								"\nYou got it! You are a genius!");
						System.exit(0);
					} else {
						// lose
						JOptionPane.showMessageDialog(null, "Person: " + possibleSolution.getPerson() + 
								"\nWeapon: " + possibleSolution.getWeapon() + 
								"\nRoom: " + possibleSolution.getRoom() +
								"\nNope, you're a loser");
					    // Exit the program when the user closes the message dialog
					    System.exit(0);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Nice try loser", "Error", JOptionPane.ERROR_MESSAGE);
			}
				
				
			
		}

//		public static void checkSuggestion(Solution testSolution) {
//			System.out.println("first");
//			Pair disprovingPair = board.handleSuggestion(testSolution.getPerson(), testSolution.getWeapon(), testSolution.getRoom(), board.getCurrPlayer());
//        	
//        	if (disprovingPair == null) {
//        		// guess result should say inconclusive
//        		setGuessResult("No New Clue", Color.white);
//        	} else {
//        		// guess result should say is was disproved
//        		Card disprovingCard = disprovingPair.getCard();
//        		Player disprovingPlayer = disprovingPair.getPlayer();
//            	Color disprovingColor = disprovingPlayer.getColor();
//            	if (board.isHumanTurn()) {
//            		setGuessResult(disprovingCard.toString(), disprovingColor);
//            	} else {
//            		setGuessResult("Suggestion Disproved", disprovingColor);
//            	}
//            	board.getCurrPlayer().addCardsSeen(disprovingCard, disprovingPlayer);
//        		
//        	}
//        	// regardless there should be the guess shown
//        	String newGuess = testSolution.getPerson() + ", " + testSolution.getRoom() +
//        			", " + testSolution.getWeapon();
//        	setGuess(newGuess, board.getCurrPlayer().getColor());
//		}

	}
	
	private class AccusationDialog extends JDialog {
		private JComboBox<String> roomDropDown;
		private JComboBox<String> personDropDown;
		private JComboBox<String> weaponDropDown;
		
		private Solution solution = new Solution();
		
		public AccusationDialog() {
			super((JDialog)null, "Make an Accusation", true);
			
	        ArrayList<Card> roomCards = board.getRoomCards();
	        ArrayList<String> roomNames = new ArrayList<String>();
	        for (Card room : roomCards) {
	        	roomNames.add(room.getCardName());
	        }
	        String[] rooms = roomNames.toArray(new String[0]);
	        roomDropDown = new JComboBox<>(rooms);
	        
	        ArrayList<Card> playerCards = board.getPersonCards();
	        ArrayList<String> playerNames = new ArrayList<String>();
	        for (Card player : playerCards) {
	        	playerNames.add(player.getCardName());
	        }
	        String[] persons = playerNames.toArray(new String[0]);
	        personDropDown = new JComboBox<>(persons);
	        
	        ArrayList<Card> weaponCards = board.getWeaponCards();
	        ArrayList<String> weaponNames = new ArrayList<String>();
	        for (Card weapon : weaponCards) {
	        	weaponNames.add(weapon.getCardName());
	        }
	        String[] weapons = weaponNames.toArray(new String[0]);
	        weaponDropDown = new JComboBox<>(weapons);
	        
	        JButton submitButton = new JButton("Submit");
	        submitButton.addActionListener(new ActionListener() {
	            
	        	@Override
	            public void actionPerformed(ActionEvent e) {
	            	// need to get all three cards and put them in solution
	            	for (Card room : roomCards) {
	            		if (room.getCardName().equals(roomDropDown.getSelectedItem())) {
	            			solution.setRoom(room);
	            			break;
	            		}
	            	}
	            	for (Card player : playerCards) {
	            		if (player.getCardName().equals(personDropDown.getSelectedItem())) {
	            			solution.setPerson(player);
	            			break;
	            		}
	            	}
	            	for (Card weapon : weaponCards) {
	            		if (weapon.getCardName().equals(weaponDropDown.getSelectedItem())) {
	            			solution.setWeapon(weapon);
	            			break;
	            		}
	            	}
	            	
	                dispose();
	            }
	        });
	        
	        JButton cancelButton = new JButton("Cancel");
	        cancelButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	canceled = true;
	                dispose(); // Close the dialog
	            }
	        });
	        
	        // Set layout
	        setLayout(new GridLayout(4, 2));

	        // Add components to the dialog
	        add(new JLabel("Room:"));
	        add(roomDropDown);

	        add(new JLabel("Person:"));
	        add(personDropDown);

	        add(new JLabel("Weapon:"));
	        add(weaponDropDown);
	        
	        add(cancelButton);
	        add(submitButton);
	        
	        setSize(300, 150);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		public Solution getSolution() {
			return solution;
		}
	}
	
	
	class NextPlayerListener implements ActionListener {
		private static Board board;
		private static CardsPanel cardsPanel;
		private JTextField currPlayer;
		private JTextField roll;

		public NextPlayerListener(Board board, JTextField currPlayer, JTextField roll, CardsPanel cardsPanel) {
			this.board = board;
			this.currPlayer = currPlayer;
			this.roll = roll;
			this.cardsPanel = cardsPanel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// TODO Auto-generated method stub
			if (board.getCurrPlayer().isHuman()) {
				int ans = JOptionPane.showConfirmDialog(null, "Are you done with your turn?", 
						"Yo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (ans == JOptionPane.YES_OPTION) {
					updateGame();
				} 
			} else {
				// if computer player has seen 18 cards, it must know that solution
				int cardsKnown = board.getCurrPlayer().getCardsHeld().size() + board.getCurrPlayer().getCardsSeen().size();
				if (cardsKnown == 18) {
					JOptionPane.showMessageDialog(null, "Person: " + board.getSolution().getPerson() + 
							"\nWeapon: " + board.getSolution().getWeapon() + 
							"\nRoom: " + board.getSolution().getRoom() +
							"\n" + board.getCurrPlayer().getName() + " wins, you suck.");
					System.exit(0);
				} else {
					board.calcTargets(board.getCell(board.getCurrPlayer().getRow(), board.getCurrPlayer().getCol()), board.getRoll());
					
					BoardCell newTarget = board.getCurrPlayer().selectTarget(board.getTargets(), board.getRoomMap(), board.getCardDeck());
					
					if (newTarget != null) {
						board.getCell(board.getCurrPlayer().getRow(), board.getCurrPlayer().getCol()).setOccupied(false);
						board.getCurrPlayer().setCol(newTarget.getColumn());
						board.getCurrPlayer().setRow(newTarget.getRow());
//						board.getCell(board.getCurrPlayer().getRow(), board.getCurrPlayer().getCol()).setOccupied(true);
						
						if (newTarget.isRoomCenter()) {
							Card currRoom = null;
							
			            	ArrayList<Card> roomCards = board.getRoomCards();
			            	for (Card room : roomCards) {
			            		
			            		if (room.getCardName().equals(board.getRoom(board.getCell(board.getCurrPlayer().getRow(), board.getCurrPlayer().getCol())).getName())) {
			            			
			            			currRoom = room;
			            			break;
			            		}
			            	}
			            	Solution computerSolution = board.getCurrPlayer().createSuggestion(currRoom, board.getPersonCards(), board.getWeaponCards(), board.getRoomCards());
			            	
			            	checkSuggestion(computerSolution);
						}
						
					}
				

					updateGame();
				}
				
				
			}
		}

		/*
		 * The method below check the suggestion of the player that is 
		 * suggesting a possible solution.
		 */
		public static void checkSuggestion(Solution testSolution) {
			Pair disprovingPair = board.handleSuggestion(testSolution.getPerson(), testSolution.getWeapon(), testSolution.getRoom(), board.getCurrPlayer());
        	
        	if (disprovingPair == null) {
        		// guess result should say inconclusive
        		setGuessResult("Guess not Disproved", Color.white);
        	} else {
        		// guess result should say is was disproved
        		Card disprovingCard = disprovingPair.getCard();
            	Player disprovingPlayer = disprovingPair.getPlayer();
            	Color disprovingColor = disprovingPlayer.getColor();
            	if (board.isHumanTurn()) {
            		setGuessResult(disprovingCard.toString(), disprovingColor);
            	} else {
            		setGuessResult("Guess Disproved", disprovingColor);
            	}
            	board.getCurrPlayer().addCardsSeen(disprovingCard, disprovingPlayer);

            	cardsPanel.updateSeenCards();        	
            	
        		
        	}
        	// regardless there should be the guess shown
        	String newGuess = testSolution.getPerson() + ", " + testSolution.getRoom() +
        			", " + testSolution.getWeapon();
        	setGuess(newGuess, board.getCurrPlayer().getColor());
		}

		private void updateGame() {
			board.updateTurn();
			String newRoll = Integer.toString(board.roll());
			roll.setText(newRoll);
			
			currPlayer.setText(board.getCurrPlayer().getName());
			currPlayer.setBackground(board.getCurrPlayer().getColor());
			board.getBoardPanel().repaint();
			
		}

	}


	/*
	 * The Game Control Panel creates the full GUI panel that displays
	 * using a 2 x 1 matrix layout
	 */
	public GameControlPanel(Board board, CardsPanel cardsPanel) {
		this.board = board;  // create the panel
		this.cardsPanel = cardsPanel;

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
		NextPlayerListener nextPlayerListener = new NextPlayerListener(board, turn, roll, cardsPanel);
		nextPlayerButton.addActionListener(nextPlayerListener);
		
		JButton makeAccusationButton = new JButton("Make Accusation");
		MakeAccusationListener makeAccusationListener = new MakeAccusationListener(board, turn);
		makeAccusationButton.addActionListener(makeAccusationListener);

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

	public static void setGuess(String guess, Color color) {
		theGuess.setText(guess);
		theGuess.setBackground(color);
	}

	public static void setGuessResult(String newGuessResult, Color color) {
		guessResult.setText(newGuessResult);
		guessResult.setBackground(color);
	}

	public void setText(String g) {
		this.setText(g);
	}

	
}
