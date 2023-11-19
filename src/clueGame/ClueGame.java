package clueGame;
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ClueGame extends JFrame {
	
	private BoardPanel boardPanel;
	private CardsPanel cardsPanel;
	private GameControlPanel gameControlPanel;
	private static Board board;
	
	public ClueGame() {
		setTitle("Clue Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createPanels();
		add(boardPanel, BorderLayout.CENTER);
		add(cardsPanel, BorderLayout.EAST);
		add(gameControlPanel, BorderLayout.SOUTH);
	}
	
	public void createPanels() {
		boardPanel = new BoardPanel(board);
		cardsPanel = new CardsPanel(board);
		gameControlPanel = new GameControlPanel(board);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
		board.dealDeck();
		ClueGame frame = new ClueGame();  // create the panel
		//frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(800, 700);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
	}

}
