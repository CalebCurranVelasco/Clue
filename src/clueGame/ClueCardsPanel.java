package clueGame;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ClueCardsPanel extends JPanel {
	
	public ClueCardsPanel() {
		setLayout(new GridLayout(3, 1));
		
		add(createPanel("People"));
		add(createPanel("Room"));
		add(createPanel("Weapon"));
	}
	
	private JPanel createPanel(String cardType) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		
		TitledBorder title = BorderFactory.createTitledBorder(cardType);
		panel.setBorder(title);
		
		panel.add(createHandPanel(cardType));
		panel.add(createSeenPanel(cardType));
		
//		TitledBorder titleHand = BorderFactory.createTitledBorder("In Hand: ");
//		JPanel handPanel = new JPanel();
//		
//		
//		TitledBorder titleSeen = BorderFactory.createTitledBorder("Seen: ");
		
		return panel;
	}
	
	private JPanel createHandPanel(String cardType) {
		JPanel handPanel = new JPanel();
		TitledBorder titleHand = BorderFactory.createTitledBorder("In Hand: ");
		handPanel.setBorder(titleHand);
		updateHandPanel(handPanel, cardType);
	}
	
	private void updatehandPanel(JPanel panel, String cardType) {
		panel.removeAll();
		int count = 0;
		for (Card card : HumanPlayer.)
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClueCardsPanel panel = new ClueCardsPanel();
		JFrame frame = new JFrame();
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(750, 180);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true);
	}

}
