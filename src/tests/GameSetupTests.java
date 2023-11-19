package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.CardType;

class GameSetupTests {
	
	private static Board board;

	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
		board.dealDeck();
	}
	
	/*
	 * The test below tests the players are read in properly from the txt file.
	 * We ensure that the color assigned to each player matches the character it's assigned to.
	 */
	@Test
	public void testPlayerColor() {
		assertEquals("Gojo", board.getPlayer("cyan").getName());
		assertEquals("Toji", board.getPlayer("pink").getName());
		assertEquals("Sukuna", board.getPlayer("orange").getName());
		assertEquals("Geto", board.getPlayer("green").getName());
		assertEquals("Panda", board.getPlayer("white").getName());
	}
	
	
	/*
	 * The test below tests a weapon, person, and room to ensure that 
	 * the weapon, person, and room objects contain the correct enum within theeir attributes.
	 */
	@Test
	public void testDeck() {
		assertEquals(CardType.PERSON, board.getCardType("Gojo"));
		assertEquals(CardType.WEAPON, board.getCardType("Inverted Spear of Heaven"));
		assertEquals(CardType.ROOM, board.getCardType("Training Grounds"));
	}

	/*
	 * The test below tests the human player and computer player and ensures that
	 * the human player is Gojo, which is assigned the color blue. Everyone else
	 * is set as a computer player (1 human, 5 computers).
	 */
	@Test
	public void testHumanComputerPlayer() {
		assertEquals(board.getPlayer("Cyan"), board.getHumanPlayer());
		assertEquals(board.getPlayer("Orange"), board.getComputerPlayer("Orange"));
		assertEquals(6, board.getPlayerList().size());
	}

	/*
	 * The test below tests the card deck and just ensures that the card deck
	 * contains 21 cards (6 players, 6 weapons, 9 rooms).
	 */
	@Test
	void Cards() {
		assertEquals(21, board.getCardDeck().size());
	}
	
	/*
	 * The test below tests the solution deck and ensures that
	 * 1. The player, weapon, and room cards aren't null
	 * 2. The player, weapon, and room cards contain the right enum for classification.
	 */
	@Test
	void Solution() {
		assertTrue(board.getSolution().getPerson() != null);
		assertTrue(board.getSolution().getWeapon() != null);
		assertTrue(board.getSolution().getRoom() != null);
		assertTrue(board.getSolution().getPerson().getTypeOfCard() == CardType.PERSON);
		assertTrue(board.getSolution().getWeapon().getTypeOfCard() == CardType.WEAPON);
		assertTrue(board.getSolution().getRoom().getTypeOfCard() == CardType.ROOM);
	}
	
	
	/*
	 * The test below tests that playeres have cards dealt to them that don't include the solution cards
	 * and the correct number of cards are dealt to the player.
	 */
	@Test
	void cardDealtToPlayer() {
		assertTrue(board.getPlayerList().get(0).getCardsHeld() != null);
		assertTrue(board.getPlayerList().get(0).getCardsHeld().get(0) != board.getSolution().getPerson());
		assertTrue(board.getPlayerList().get(0).getCardsHeld().get(0) != board.getSolution().getWeapon());
		assertTrue(board.getPlayerList().get(0).getCardsHeld().get(0) != board.getSolution().getRoom());
		assertTrue(21 == 3 + board.getPlayerList().get(0).getCardsHeld().size() + board.getPlayerList().get(1).getCardsHeld().size() + board.getPlayerList().get(2).getCardsHeld().size() + board.getPlayerList().get(3).getCardsHeld().size() + board.getPlayerList().get(4).getCardsHeld().size() + board.getPlayerList().get(5).getCardsHeld().size());
		assertEquals(board.getPlayerList().get(0).getCardsHeld().size(), board.getPlayerList().get(1).getCardsHeld().size());
	}

}
