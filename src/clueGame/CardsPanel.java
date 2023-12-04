package clueGame;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class CardsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static Board board;
	private JPanel seenPanelForPerson;
    private JPanel seenPanelForRoom;
    private JPanel seenPanelForWeapon;

	/*
	 * This function creates the panel which displays
	 * all the card information using a 3 x 1 matrix.
	 */
	public CardsPanel(Board board) {
		this.board = board;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		seenPanelForPerson = new JPanel();
        seenPanelForRoom = new JPanel();
        seenPanelForWeapon = new JPanel();
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
		for (Card card : board.getHumanPlayer().getCardsHeld()) {
			if (card.getTypeOfCard().toString().equalsIgnoreCase(cardType.toLowerCase())) {
				field = new JTextField(card.getCardName(),20);
				field.setEditable(false);
				field.setBackground(board.getHumanPlayer().getColor());
				panel.add(field);
				counter++;
			}
		}
		if (counter == 0) {
			field = new JTextField("None",20);
			field.setEditable(false);
			panel.add(field);
		}
		panel.setLayout(new GridLayout(counter,1));
	}

	/*
	 * This function creates a panel which displays
	 * the cards that the player has seen by others.
	 */
	private JPanel createSeenPanel(String cardType) {
        JPanel seenPanel = null;
        if ("Person".equals(cardType)) {
            seenPanel = seenPanelForPerson;
        } else if ("Room".equals(cardType)) {
            seenPanel = seenPanelForRoom;
        } else if ("Weapon".equals(cardType)) {
            seenPanel = seenPanelForWeapon;
        }
        
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
		
		for (Card card : board.getCurrPlayer().getCardsSeen().keySet()) {
			System.out.println("In Panel: " + card);
			if (card.getTypeOfCard().toString().equalsIgnoreCase(cardType.toLowerCase())) {
				System.out.println("in if: " + card.getCardName());
				field = new JTextField(card.getCardName(), 20);
				field.setEditable(false);
				field.setBackground(board.getCurrPlayer().getCardsSeen().get(card).getColor());
				panel.add(field);
				counter++;
			}
		}
		if (counter == 0) {
			field = new JTextField("None",20);
			field.setEditable(false);
			panel.add(field);
		}

		panel.setLayout(new GridLayout(counter,1));
	}
	
    public void updateSeenCards() {
//    	updateSeenPanel(seenPanelForPerson, "Person");
//        updateSeenPanel(seenPanelForRoom, "Room");
//        updateSeenPanel(seenPanelForWeapon, "Weapon");
    	createPanel("Person");
    	createPanel("Room");
        createPanel("Weapon");
    }

}
