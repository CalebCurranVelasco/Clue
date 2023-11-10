package clueGame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public abstract class Player {
	private String name;
	private Color color;
	private int row;
	private int col;
	protected ArrayList<Card> hand;
	protected ArrayList<Card> cardsSeen;
	private boolean human;
	
	public abstract void updateHand(Card card);
	public abstract Solution createSuggestion(Card currRoom, ArrayList<Card> personList, ArrayList<Card> weaponList, ArrayList<Card> roomList);
	public abstract BoardCell selectTarget(Set<BoardCell> targets, Map<Character, Room> roomMap, ArrayList<Card> cards);
	
	public Player(String name, Color color, int row, int col, boolean human) {
		super();
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
		this.human = human;
		this.hand = new ArrayList<Card>();
		this.cardsSeen = new ArrayList<Card>();
	}

	public Card disproveSuggestion(Card person, Card weapon, Card room) {
		if (hand.contains(person)) {
			return person;
		}
		if (hand.contains(weapon)) {
			return weapon;
		}
		if (hand.contains(room)) {
			return room;
		}
		return null;
	}
	
	
	public boolean isHuman() {
		return human;
	}

	public void setHuman(boolean human) {
		this.human = human;
	}

	public ArrayList<Card> getCardsHeld() {
		return hand;
	}

	public void addCardsHeld(Card card) {
		this.hand.add(card);
	}
	
	public ArrayList<Card> getCardsSeen() {
		return cardsSeen;
	}

	public void addCardsSeen(Card card) {
		this.cardsSeen.add(card);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	
}
