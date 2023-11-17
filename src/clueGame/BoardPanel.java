package clueGame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public BoardPanel() {
		setSize(new Dimension(400, 250));
		setTitle("Clue Game – CSCI306");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ClueCardsPanel cardsPanel = new ClueCardsPanel();
		add(cardsPanel, BorderLayout.EAST);
		
		GameControlPanel controlPanel = new GameControlPanel();
		add(controlPanel, BorderLayout.SOUTH);
		
	}
	
	
	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}


	private void setTitle(String string) {
		// TODO Auto-generated method stub
		
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}

}
