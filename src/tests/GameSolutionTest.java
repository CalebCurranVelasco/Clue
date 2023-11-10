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

	 
	/*
	 * The test below tests the accusation. In this scenario, we set the solution to a 
	 * specific person, room, and weapon card. We then create 4 cases where we check the 
	 * accusation and only 1 of the 4 statements should match the exact solution set.
	 */
	@Test
	void testAccusation() {
		board.setSolution(new Card("Gojo", CardType.PERSON), new Card("Split Soul Katana", CardType.WEAPON), new Card("Dojo", CardType.ROOM));
		assertTrue(board.checkAccusation(new Card("Gojo", CardType.PERSON), new Card("Split Soul Katana", CardType.WEAPON), new Card("Dojo", CardType.ROOM)));
		assertFalse(board.checkAccusation(new Card("Geto", CardType.PERSON), new Card("Split Soul Katana", CardType.WEAPON), new Card("Dojo", CardType.ROOM)));
		assertFalse(board.checkAccusation(new Card("Gojo", CardType.PERSON), new Card("Nanami's Sword", CardType.WEAPON), new Card("Dojo", CardType.ROOM)));
		assertFalse(board.checkAccusation(new Card("Gojo", CardType.PERSON), new Card("Split Soul Katana", CardType.WEAPON), new Card("Temple", CardType.ROOM)));
	}
	
	
	
	/*
	 * The test below is a test for disproving a suggestion. The sscenario
	 * below creates 2 cards and adds the cards to the player's (whose color is blue) hand.
	 * We then create 2 scenarios in which the equals( ) method is called to compare the 
	 * card types and ensure the name of the cards match.
	 */
	@Test
	void disproveSuggestion() {
		Card card = new Card("Dojo", CardType.ROOM);
		Card card1 = new Card("Gojo", CardType.PERSON);
		board.getPlayer("blue").addCardsHeld(card);
		board.getPlayer("blue").addCardsHeld(card1);
		assertTrue(card.equals(board.getPlayer("blue").disproveSuggestion(new Card("Geto", CardType.PERSON), new Card("Split Soul Katana", CardType.WEAPON), card)));
		assertTrue(card.equals(board.getPlayer("blue").disproveSuggestion(card, new Card("Split Soul Katana", CardType.WEAPON), card)));
		assertEquals(null, board.getPlayer("blue").disproveSuggestion(new Card("Geto", CardType.PERSON), new Card("Split Soul Katana", CardType.WEAPON), new Card("Temple", CardType.ROOM)));
	}
	
	
	/*
	 * The test below tests the handling of a suggestion. In this test,
	 * we create 6 cards and add a card to each players' hand.
	 * We then create a test in which we use the equals( ) method to handle 
	 * the suggestion. The handleSuggestion( ) function takes in a 
	 * person, weapon, and room as well as the player handling the suggestion.
	 */
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
	
	
	/*
	 * The test below creates a test for the case where only the player 
	 * can disprove a suggestion. We create 8 cards below and give each player
	 * a card in their hands. We are disproving the suggestion because nobody contains
	 * the cards that are being handled in the suggestion, except the player whose
	 * name corresponds with the color "blue."
	 * 
	 */
	@Test
	void onlyPlayerCanDisprove() {
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
	
	/*
	 * The test below works similar to the one above, except 
	 * that no one can disprove since no cards match any of the players' hands.
	 */
	@Test
	void noneCanDisprove() {
		Card card = new Card("Dojo", CardType.ROOM);
		Card card1 = new Card("Split Soul Katana", CardType.WEAPON);
		Card card2 = new Card("Gojo", CardType.PERSON);
		Card card3 = new Card("Geto", CardType.PERSON);
		Card card4 = new Card("Nanami's Sword", CardType.WEAPON);
		Card card5 = new Card("Cursed Warehouse", CardType.ROOM);
		Card card6 = new Card("Sukuna", CardType.PERSON);
		Card card7 = new Card("Chain of a Thousand Miles", CardType.WEAPON);
		Card card8 = new Card("Temple", CardType.ROOM);
		board.getPlayer("blue").addCardsHeld(card);
		board.getPlayer("magenta").addCardsHeld(card1);
		board.getPlayer("red").addCardsHeld(card2);
		board.getPlayer("green").addCardsHeld(card3);
		board.getPlayer("black").addCardsHeld(card4);
		board.getPlayer("white").addCardsHeld(card5);
		assertEquals(null, board.handleSuggestion(card6, card7, card8, board.getPlayer("red")));
	}
	
	/*
	 * The test below works similar to the one above, except 
	 * that two players can disprove the suggestion.
	 */
	@Test
	void twoCanDisprove() {
		Card card = new Card("Dojo", CardType.ROOM);
		Card card1 = new Card("Split Soul Katana", CardType.WEAPON);
		Card card2 = new Card("Gojo", CardType.PERSON);
		Card card3 = new Card("Geto", CardType.PERSON);
		Card card4 = new Card("Nanami's Sword", CardType.WEAPON);
		Card card5 = new Card("Cursed Warehouse", CardType.ROOM);
		Card card6 = new Card("Sukuna", CardType.PERSON);
		Card card7 = new Card("Chain of a Thousand Miles", CardType.WEAPON);
		Card card8 = new Card("Temple", CardType.ROOM);
		board.getPlayer("blue").addCardsHeld(card);
		board.getPlayer("magenta").addCardsHeld(card1);
		board.getPlayer("red").addCardsHeld(card2);
		board.getPlayer("green").addCardsHeld(card3);
		board.getPlayer("black").addCardsHeld(card4);
		board.getPlayer("white").addCardsHeld(card5);
		assertEquals(card4, board.handleSuggestion(card6, card4, card, board.getPlayer("red")));
	}

}
