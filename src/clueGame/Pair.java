package clueGame;

import java.awt.Color;

public class Pair {
	private Card card;
	private Player player;
	
	public Pair(Card card, Player player) {
		this.card = card;
		this.player = player;
	}
	
	public Card getCard() {
		return card;
	}
	
	public Player getPlayer() {
		return player;
	}
}
