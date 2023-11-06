package clueGame;
import java.awt.Color;
import java.util.ArrayList;

public abstract class Player {
	private String name;
	private Color color;
	private int row;
	private int col;
	private ArrayList<Card> cardsHeld;

	public abstract void updateHand(Card card);

	public Player(String name, Color color, int row, int col) {
		super();
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
	}
	
	
	public ArrayList<Card> getCardsHeld() {
		return cardsHeld;
	}

	public void setCardsHeld(Card cardsHeld) {
		this.cardsHeld.add(cardsHeld);
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
