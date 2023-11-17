package clueGame;
import javax.swing.JFrame;

public class ClueGame extends JFrame {
	
	private BoardPanel boardPanel;
	private CardsPanel cardsPanel;
	private GameControlPanel gameControlPanel;
	
	public ClueGame() {
		setSize(750,930);
		setTitle("Clue Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createPanels();
	}
	
	public void createPanels() {
		boardPanel = new BoardPanel
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameControlPanel panel = new GameControlPanel();  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(750, 930);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
	}

}
