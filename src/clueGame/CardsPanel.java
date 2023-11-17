package clueGame;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class CardsPanel extends JPanel {
	private static HumanPlayer humanPlayer;
	
	/*
	 * This function creates the panel which displays
	 * all the card information using a 3 x 1 matrix.
	 */
	public CardsPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(createPanel("Person"));
		add(createPanel("Room"));
		add(createPanel("Weapon"));
	}
	
	/*
	 * This function creates a panel for displaying
	 * the person, room, and weapon cards that are in hand
	 * and seen by the player.
	 */
	private JPanel createPanel(String cardType) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		TitledBorder title = BorderFactory.createTitledBorder(cardType);
		panel.setBorder(title);
		
		panel.add(createHandPanel(cardType));
		panel.add(createSeenPanel(cardType));
		
		return panel;
	}
	
	/*
	 * This function creates a panel for displaying the player's 
	 * cards that are in its hand. This function is called 
	 * from the function above.
	 */
	private JPanel createHandPanel(String cardType) {
		JPanel handPanel = new JPanel();
		TitledBorder titleHand = BorderFactory.createTitledBorder("In Hand: ");
		handPanel.setBorder(titleHand);
		updateHandPanel(handPanel, cardType);
		return handPanel;
	}
	
	/*
	 * This function updates the display by clearing it
	 * and re-displays the player's cards in hand. 
	 */
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
	
	/*
	 * This function creates a panel which displays
	 * the cards that the player has seen by others.
	 */
	private JPanel createSeenPanel(String cardType) {
		JPanel seenPanel = new JPanel();
		TitledBorder titleSeen = BorderFactory.createTitledBorder("Seen: ");
		seenPanel.setBorder(titleSeen);
		updateSeenPanel(seenPanel, cardType);
		return seenPanel;
	}
	
	/*
	 * This function updates the display by clearing it 
	 * and re-displays the player's seen cards.
	 */
	private void updateSeenPanel(JPanel panel, String cardType) {
		panel.removeAll();
		int counter = 0;
		JTextField field;
		for (Card card : humanPlayer.getCardsSeen().keySet()) {
			if (card.getTypeOfCard().toString().toLowerCase().equals(cardType.toLowerCase())) {
				field = new JTextField(card.getCardName());
				field.setBackground(humanPlayer.getCardsSeen().get(card).getColor());
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
		humanPlayer = new HumanPlayer("Gojo", Color.BLUE);
		Player player1 = new ComputerPlayer("Sukuna", Color.RED);
		Player player2 = new ComputerPlayer("Panda",Color.BLACK);
		Player player3 = new ComputerPlayer("Toji", Color.MAGENTA);
		// create hand
		humanPlayer.addCardsHeld(new Card("Sukuna", CardType.PERSON));
		humanPlayer.addCardsHeld(new Card("Geto", CardType.PERSON));
		humanPlayer.addCardsHeld(new Card("Hidden Room", CardType.ROOM));
		humanPlayer.addCardsHeld(new Card("Inverted Spear of Heaven", CardType.WEAPON));
		
		//adding cards seen
		humanPlayer.addCardsSeen(new Card("Shrine", CardType.ROOM), player1);
		humanPlayer.addCardsSeen(new Card("Dojo", CardType.ROOM), player2);
		humanPlayer.addCardsSeen(new Card("Forest", CardType.ROOM), player3);
		humanPlayer.addCardsSeen(new Card("Toji", CardType.PERSON), player1);
		humanPlayer.addCardsSeen(new Card("Panda", CardType.PERSON), player2);
		humanPlayer.addCardsSeen(new Card("Mahito", CardType.PERSON), player3);
		humanPlayer.addCardsSeen(new Card("Meimei's Battle Axe", CardType.WEAPON), player1);
		humanPlayer.addCardsSeen(new Card("Gakuganji's Guitar", CardType.WEAPON), player2);
		humanPlayer.addCardsSeen(new Card("Nanami's Sword", CardType.WEAPON), player3);
		
		CardsPanel panel = new CardsPanel();
		JFrame frame = new JFrame();
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(180, 750);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true);
	}

}
