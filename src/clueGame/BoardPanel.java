package clueGame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.platform.engine.support.descriptor.DirectorySource;

public class BoardPanel extends JPanel{
	
	private static Board board;

	public BoardPanel(Board board) {
		this.board = board;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		int cellDimension = 50;
		int cellDimension = Math.min(getWidth() / board.getNumColumns(), getHeight() / board.getNumRows());
		//board.getCell(0,20).draw(g, cellDimension);
		//board.getCell(2,1).draw(g, cellDimension);
		for (int i=0; i < board.getNumRows(); i++) {
			for (int j=0; j < board.getNumColumns(); j++) {

				board.getCell(i, j).draw(g, cellDimension);
			}
		}
		for (Player player : board.getPlayerList()) {
			player.draw(g, cellDimension);
		}
		for (int i = 0; i < board.getNumRows(); i++) {
			for (int j=0; j < board.getNumColumns(); j++) {
				BoardCell currCell = board.getCell(i, j);
				if (currCell.isLabel()) {
					g.setColor(Color.CYAN);
					g.setFont(new Font("Arial", Font.PLAIN, this.getHeight()/40));
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
                    	System.out.println("he");
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
}
//		setSize(new Dimension(400, 250));
//		setTitle("Clue Game – CSCI306");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		CardsPanel cardsPanel = new CardsPanel();
//		add(cardsPanel, BorderLayout.EAST);
//		
//		GameControlPanel controlPanel = new GameControlPanel();
//		add(controlPanel, BorderLayout.SOUTH);
		
	
	


