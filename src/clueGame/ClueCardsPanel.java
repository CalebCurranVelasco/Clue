package clueGame;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ClueCardsPanel extends JPanel {
	private static HumanPlayer humanPlayer;
	
	public ClueCardsPanel() {
		setLayout(new GridLayout(3, 1));
		
		add(createPanel("Person"));
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
		return handPanel;
	}
	
	private void updateHandPanel(JPanel panel, String cardType) {
		panel.removeAll();
		int counter = 0;
		JTextField field;
		for (Card card : humanPlayer.getCardsHeld()) {
			if (card.getTypeOfCard().toString().toLowerCase().equals(cardType.toLowerCase())) {
				field = new JTextField(card.getCardName());
				field.setBackground(humanPlayer.getColor());
				panel.add(field);
				counter++;
			}
		}
		if (counter == 0) {
			field = new JTextField("None");
			panel.add(field);
		}
		panel.setLayout(new GridLayout(counter,1));
	}
	
	private JPanel createSeenPanel(String cardType) {
		JPanel seenPanel = new JPanel();
		TitledBorder titleSeen = BorderFactory.createTitledBorder("Seen: ");
		seenPanel.setBorder(titleSeen);
		updateSeenPanel(seenPanel, cardType);
		return seenPanel;
	}
	
	private void updateSeenPanel(JPanel panel, String cardType) {
		panel.removeAll();
		int counter = 0;
		JTextField field;
		for (Card card : humanPlayer.getCardsSeen()) {
			if (card.getTypeOfCard().toString().toLowerCase().equals(cardType.toLowerCase())) {
				field = new JTextField(card.getCardName());
				field.setBackground(humanPlayer.getColor());
				panel.add(field);
				counter++;
			}
		}
		if (counter == 0) {
			field = new JTextField("None");
			panel.add(field);
		}
		panel.setLayout(new GridLayout(counter,1));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		humanPlayer = new HumanPlayer("Gojo", Color.BLUE, 0, 0, false);
		// create hand
		humanPlayer.addCardsHeld(new Card("Sukuna", CardType.PERSON));
		humanPlayer.addCardsHeld(new Card("Geto", CardType.PERSON));
		humanPlayer.addCardsHeld(new Card("Hidden Room", CardType.ROOM));
		humanPlayer.addCardsHeld(new Card("Inverted Spear of Heaven", CardType.WEAPON));
		
		//adding cards seen
		humanPlayer.addCardsSeen(new Card("Shrine", CardType.ROOM));
		humanPlayer.addCardsSeen(new Card("Dojo", CardType.ROOM));
		humanPlayer.addCardsSeen(new Card("Forest", CardType.ROOM));
		humanPlayer.addCardsSeen(new Card("Toji", CardType.PERSON));
		humanPlayer.addCardsSeen(new Card("Panda", CardType.PERSON));
		humanPlayer.addCardsSeen(new Card("Mahito", CardType.PERSON));
		humanPlayer.addCardsSeen(new Card("Meimei's Battle Axe", CardType.WEAPON));
		humanPlayer.addCardsSeen(new Card("Gakuganji's Guitar", CardType.WEAPON));
		humanPlayer.addCardsSeen(new Card("Nanami's Sword", CardType.WEAPON));
		
		ClueCardsPanel panel = new ClueCardsPanel();
		JFrame frame = new JFrame();
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(180, 750);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true);
	}

}