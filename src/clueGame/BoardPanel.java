package clueGame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.platform.engine.support.descriptor.DirectorySource;

public class BoardPanel extends JPanel{

	private static Board board;
	private int cellDimension;
	private boolean canceled;

	public BoardPanel(Board board) {
		this.board = board;
		board.setBoardPanel(this);
		addMouseListener(new TargetListener());
	}


	/*
	 * The function below is the paint component
	 * for the graphics portion of this function.
	 * This function lets us draw the game board for display
	 * using the width and height of each individual cell.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		cellDimension = Math.min(getWidth() / board.getNumColumns(), getHeight() / board.getNumRows());
		
		drawSecretPassage(g);
		
		// The loops below creates the room labels
		for (Player player : board.getPlayerList()) {
			player.drawPlayers(g, cellDimension, board);
		}
		for (int i = 0; i < board.getNumRows(); i++) {
			for (int j=0; j < board.getNumColumns(); j++) {
				BoardCell currCell = board.getCell(i, j);
				if (currCell.isLabel()) {
					g.setColor(Color.CYAN);
					g.setFont(new Font("Arial", Font.PLAIN, this.getHeight()/45));
					g.drawString(board.getRoom(currCell).getName(), j*cellDimension, i*cellDimension);
				}
			}
		}
		
		drawClueBoard(g);
		
		if (board.isHumanTurn()) {
			board.calcTargets(board.getCell(board.getCurrPlayer().getRow(), board.getCurrPlayer().getCol()), board.getRoll());
			
			for (BoardCell target : board.getTargets()) {

				if (!target.isOccupied()) {
					target.drawTarget(g, cellDimension);
				}

			}
		}

	}


	/*
	 * The function below simply draws the clue board
	 * and includes the doorways.
	 */
	private void drawClueBoard(Graphics g) {
		
		for (int i = 0; i < board.getNumRows(); i++) {
			
			for (int j = 0; j < board.getNumColumns(); j++) {
				
				if (board.getCell(i, j).getInitial() == 'W') {
					g.setColor(Color.BLACK);
					g.drawRect(j*cellDimension,i*cellDimension,cellDimension,cellDimension);
				}
				
				if (board.getCell(i, j).isDoorway()) {

					g.setColor(Color.CYAN);
					switch (board.getCell(i, j).getDoorDirection()) {
					case UP:
						g.fillRect(j * cellDimension, i * cellDimension, cellDimension,4);
						g.drawRect(j * cellDimension, i * cellDimension, cellDimension,4);
						break;
					case DOWN:
						g.fillRect(j * cellDimension, i * cellDimension+cellDimension, cellDimension, 4);
						g.drawRect(j * cellDimension, i * cellDimension+cellDimension, cellDimension, 4);
						break;
					case LEFT:
						g.fillRect(j * cellDimension, i * cellDimension, 4,cellDimension);
						g.drawRect(j * cellDimension, i * cellDimension, 4,cellDimension);
						break;
					case RIGHT:
						g.fillRect(j * cellDimension+cellDimension, i * cellDimension, 4, cellDimension);
						g.drawRect(j * cellDimension+cellDimension, i * cellDimension, 4, cellDimension);
						break;
					case NONE:
						break;

					}
				}

			}
		}		
	}


	/*
	 * The function below simply draws the secret passages to display on the JFrame.
	 */
	private void drawSecretPassage(Graphics g) {
		
		for (int i=0; i < board.getNumRows(); i++) {
			
			for (int j=0; j < board.getNumColumns(); j++) {
				
				if (board.getCell(i, j).isSecretPassage()) {
					
					board.getCell(i, j).drawSecret(g, cellDimension);
					g.setColor(Color.BLACK);
					g.setFont(new Font("Arial", Font.PLAIN, this.getHeight() / 45));

					// Calculate the center coordinates of the cell
					int centerX = j * cellDimension + cellDimension / 2;
					int centerY = i * cellDimension + cellDimension / 2;

					// Draw the "S" at the center of the cell
					g.drawString("S", centerX, centerY);

				} 
				
				else {
					board.getCell(i, j).draw(g, cellDimension);
				}

			}
		}
		
	}



	private class TargetListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			if (board.isHumanTurn()) {
				BoardCell clickedCell = board.getCell(e.getY()/cellDimension, e.getX()/cellDimension);
				if (board.getTargets().contains(clickedCell)) {
					board.movePlayer(clickedCell);
					board.setHumanTurn(false);
					repaint();
					
					// if in room make suggestion
					if (clickedCell.isRoomCenter()) {
						// add JDialog and get solution from dialog and send to handleSuggestion in board
						showSuggestionJDialog();
					}

				} else {
					showErrorClick();
				}
			}
		}


		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		private void showErrorClick() {
			String message = "Error: Not a valid target.";
			JOptionPane.showMessageDialog(
					null,
					message,
					"Error",
					JOptionPane.INFORMATION_MESSAGE
					);	
		}
			
		private void showSuggestionJDialog() {
			canceled = false;
			SuggestionDialog suggestionDialog = new SuggestionDialog();
	        suggestionDialog.setVisible(true);

	        // Get the solution from the dialog and handle the suggestion
	        if (canceled == false) {
	        	Solution solution = suggestionDialog.getSolution();
		        board.handleSuggestion(solution.getPerson(), solution.getWeapon(), solution.getRoom(), board.getCurrPlayer());
	        }
	        
		}
	}
	
	private class SuggestionDialog extends JDialog {
		private JTextField roomTextField;
		private JComboBox<String> personDropDown;
		private JComboBox<String> weaponDropDown;
		
		private Solution solution = new Solution();
		
		public SuggestionDialog() {
			super((JDialog)null, "Make a Suggestion", true);
			roomTextField = new JTextField(board.getRoom(board.getCell(board.getCurrPlayer().getRow(), board.getCurrPlayer().getCol())).getName());
	        roomTextField.setEditable(false);
	        
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
	            	ArrayList<Card> roomCards = board.getRoomCards();
	            	for (Card room : roomCards) {
	            		if (room.getCardName().equals(board.getRoom(board.getCell(board.getCurrPlayer().getRow(), board.getCurrPlayer().getCol())).getName())) {
	            			solution.setRoom(room);
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
	        add(new JLabel("Current Room:"));
	        add(roomTextField);

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
}


