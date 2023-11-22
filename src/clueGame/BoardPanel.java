package clueGame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.junit.platform.engine.support.descriptor.DirectorySource;

public class BoardPanel extends JPanel{

	private static Board board;
	private int cellDimension;

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
		//		int cellDimension = 50;
		cellDimension = Math.min(getWidth() / board.getNumColumns(), getHeight() / board.getNumRows());
		//board.getCell(0,20).draw(g, cellDimension);
		//board.getCell(2,1).draw(g, cellDimension);
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

				} else {
					board.getCell(i, j).draw(g, cellDimension);
				}

			}
		}
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
		if (board.isHumanTurn()) {
			board.calcTargets(board.getCell(board.getCurrPlayer().getRow(), board.getCurrPlayer().getCol()), board.getRoll());
			System.out.println(board.getTargets().size());
			for (BoardCell target : board.getTargets()) {
				//        			System.out.println(target);

				if (!target.isOccupied()) {
					target.drawTarget(g, cellDimension);
				}

			}
		}

	}



	private class TargetListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (board.isHumanTurn()==true) {
				BoardCell clickedCell = board.getCell(e.getY()/cellDimension, e.getX()/cellDimension);
				if (board.getTargets().contains(clickedCell)) {
					board.movePlayer(clickedCell);
					board.setHumanTurn(false);
					repaint();

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
	}
}


