package clueGame;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClueGame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private BoardPanel boardPanel;
	private CardsPanel cardsPanel;
	private GameControlPanel gameControlPanel;
	private static Board board;
	
	
	/*
	 * This function creates the main panel for the 
	 * Clue board and adds all other panels to display.
	 */
	public ClueGame() {
		setTitle("Clue Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createPanels();
		splashScreen();
		add(boardPanel, BorderLayout.CENTER);
		add(cardsPanel, BorderLayout.EAST);
		add(gameControlPanel, BorderLayout.SOUTH);
	}
	
	/*
	 * This function creates all the panels
	 * that are going to be displayed on the GUI.
	 */
	public void createPanels() {
		boardPanel = new BoardPanel(board);
		cardsPanel= new CardsPanel(board);
		gameControlPanel = new GameControlPanel(board);
	}
	
	private static void splashScreen() {
		String message = "You are Gojo Satoru.\nCan you find the solution \nbefore the the advanced \nAI players?";
        JOptionPane.showMessageDialog(null, message, "Welcome to Clue", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		
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
