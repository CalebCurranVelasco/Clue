package clueGame;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClueGame extends JFrame {

	private static final long serialVersionUID = 1L;
	private BoardPanel boardPanel;
	private static CardsPanel cardsPanel;
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
		
		add(boardPanel, BorderLayout.CENTER);
		add(cardsPanel, BorderLayout.EAST);
		add(gameControlPanel, BorderLayout.SOUTH);
//		splashScreen();
	}

	/*
	 * This function creates all the panels
	 * that are going to be displayed on the GUI.
	 */
	public void createPanels() {
		boardPanel = new BoardPanel(board);
		cardsPanel= new CardsPanel(board);
		gameControlPanel = new GameControlPanel(board, cardsPanel);
	}

	/*
	 * The function below creates a splash screen
	 * which basically just displays the game's intro.
	 */
	private static void splashScreen() {
		String message = "You are Gojo Satoru.\nCan you find the solution \nbefore the the advanced \nAI players?";
		JOptionPane.showMessageDialog(null, message, "Welcome to Clue", JOptionPane.INFORMATION_MESSAGE);
	}

	/*
	 * The main method below is what lets us
	 * display the whole game. It wraps everything up.
	 */
	public static void main(String[] args) {
		playWAV("data/music.wav");

		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
		board.dealDeck();
		ClueGame frame = new ClueGame();  // create the panel
		//frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(800, 700);  // size the frame
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
		splashScreen();
	}
	
	private static void playWAV(String filePath) {
	    try {
	        File audioFile = new File(filePath);
	        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioStream);
	        clip.start();
	    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	        e.printStackTrace();
	    }
	}

}
