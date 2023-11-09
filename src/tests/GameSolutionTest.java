package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

class GameSolutionTest {

	private static Board board;
//
	@BeforeEach
	public void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
//		board.dealDeck();
	
	}

	
//	 Check accusation tests
	@Test
	void testAccusation() {
		board.setSolution(new Card("Gojo", CardType.PERSON), new Card("Split Soul Katana", CardType.WEAPON), new Card("Dojo", CardType.ROOM));
		assertTrue(board.checkAccusation(new Card("Gojo", CardType.PERSON), new Card("Split Soul Katana", CardType.WEAPON), new Card("Dojo", CardType.ROOM)));
	}
	
	@Test
	void disproveSuggestion() {
		Card card = new Card("Dojo", CardType.ROOM);
		board.getPlayer("blue").addCardsHeld(card);
		assertTrue(card.equals(board.getPlayer("blue").disproveSuggestion(new Card("Gojo", CardType.PERSON), new Card("Split Soul Katana", CardType.WEAPON), card)));
	}
	
	@Test
	void handleSuggesstion() {
		Card card = new Card("Dojo", CardType.ROOM);
		Card card1 = new Card("Split Soul Katana", CardType.WEAPON);
		Card card2 = new Card("Gojo", CardType.PERSON);
		Card card3 = new Card("Geto", CardType.PERSON);
		Card card4 = new Card("Nanami's Sword", CardType.WEAPON);
		Card card5 = new Card("Cursed Warehouse", CardType.ROOM);
		board.getPlayer("blue").addCardsHeld(card);
		board.getPlayer("magenta").addCardsHeld(card1);
		board.getPlayer("red").addCardsHeld(card2);
		board.getPlayer("green").addCardsHeld(card3);
		board.getPlayer("black").addCardsHeld(card4);
		board.getPlayer("white").addCardsHeld(card5);
		assertTrue(card3.equals(board.handleSuggestion(card3, card4, card5, board.getPlayer("white"))));
	}
	
	@Test
	void handleSuggesstionNull() {
		Card card = new Card("Dojo", CardType.ROOM);
		Card card1 = new Card("Split Soul Katana", CardType.WEAPON);
		Card card2 = new Card("Gojo", CardType.PERSON);
		Card card3 = new Card("Geto", CardType.PERSON);
		Card card4 = new Card("Nanami's Sword", CardType.WEAPON);
		Card card5 = new Card("Cursed Warehouse", CardType.ROOM);
		Card card6 = new Card("Sukuna", CardType.PERSON);
		Card card7 = new Card("Chain of a Thousand Miles", CardType.WEAPON);
		board.getPlayer("blue").addCardsHeld(card);
		board.getPlayer("magenta").addCardsHeld(card1);
		board.getPlayer("red").addCardsHeld(card2);
		board.getPlayer("green").addCardsHeld(card3);
		board.getPlayer("black").addCardsHeld(card4);
		board.getPlayer("white").addCardsHeld(card5);
		assertEquals(null, board.handleSuggestion(card6, card7, card, board.getPlayer("blue")));
	}

}
