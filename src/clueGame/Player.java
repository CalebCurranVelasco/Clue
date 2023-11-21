package clueGame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Player {
	private String name;
	private Color color;
	private int row;
	private int col;
	protected ArrayList<Card> hand;
	protected Map<Card, Player> cardsSeen;
	private boolean human;
	
	public abstract void updateHand(Card card);
	public abstract Solution createSuggestion(Card currRoom, ArrayList<Card> personList, ArrayList<Card> weaponList, ArrayList<Card> roomList);
	public abstract BoardCell selectTarget(Set<BoardCell> targets, Map<Character, Room> roomMap, ArrayList<Card> cards);
	
	public Player(String name, Color color) {
		super();
		this.name = name;
		this.color = color;
		this.hand = new ArrayList<Card>();
		this.cardsSeen = new HashMap<>();
	}
	
	public Player(String name, Color color, int row, int col, boolean human) {
		super();
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
		this.human = human;
		this.hand = new ArrayList<Card>();
		this.cardsSeen = new HashMap<>();
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

	public void draw(Graphics g, int dimension) {		
		g.setColor(this.color);
		int radius = dimension/2;
		int circleX = (col * dimension) + radius;
		int circleY = (row * dimension) + radius;
		g.fillOval(circleX - radius, circleY - radius, radius * 2, radius * 2);
	}
	
	public void drawPlayers(Graphics g, int dimension, Board board) {
		Set<BoardCell> drawnCells = new HashSet<>();
		for (Player player : board.getPlayerList()) {
			BoardCell currCell = board.getCell(player.getRow(), player.getCol());
			if (drawnCells.contains(currCell)) {
				BoardCell newCell = board.getCell(currCell.getRow(), currCell.getColumn()+1);
				drawnCells.add(newCell);
				player.setRow(newCell.getRow());
				player.setCol(newCell.getColumn());
				player.draw(g, dimension);
				player.setRow(currCell.getRow());
				player.setCol(currCell.getColumn());
			} else {
				currCell.setOccupied(true);
				player.draw(g, dimension);
				drawnCells.add(currCell);
			}
		}	
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
	
	public Map<Card, Player> getCardsSeen() {
		return cardsSeen;
	}

//	public void addCardsSeen(Card card) {
//		this.cardsSeen.put(card);
//	}
	
	public void addCardsSeen(Card card, Player seenFrom) {
		this.cardsSeen.put(card, seenFrom);
	}
	
	public void addCardsSeen(Card card) {
		Player tempPlayer = new ComputerPlayer("Test", Color.BLACK);
		this.cardsSeen.put(card, tempPlayer);
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
