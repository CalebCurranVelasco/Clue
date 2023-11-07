package clueGame;
import java.awt.Color;
import java.util.ArrayList;

public abstract class Player {
	private String name;
	private Color color;
	private int row;
	private int col;
	private ArrayList<Card> cardsHeld;
	private boolean human;

	public abstract void updateHand(Card card);

	public Player(String name, Color color, int row, int col, boolean human) {
		super();
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
		this.human = human;
		this.cardsHeld = new ArrayList<Card>();
	}
	
	
	public boolean isHuman() {
		return human;
	}

	public void setHuman(boolean human) {
		this.human = human;
	}

	public ArrayList<Card> getCardsHeld() {
		return cardsHeld;
	}

	public void addCardsHeld(Card card) {
		this.cardsHeld.add(card);
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
