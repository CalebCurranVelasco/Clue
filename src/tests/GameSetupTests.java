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
	
	@Test
	public void testPlayerColor() {
		assertEquals("Gojo", board.getPlayer("blue").getName());
		assertEquals("Toji", board.getPlayer("magenta").getName());
		assertEquals("Sukuna", board.getPlayer("red").getName());
		assertEquals("Geto", board.getPlayer("green").getName());
		assertEquals("Panda", board.getPlayer("black").getName());
	}
	
	@Test
	public void testDeck() {
		assertEquals(CardType.PERSON, board.getCardType("Gojo"));
		assertEquals(CardType.WEAPON, board.getCardType("Inverted Spear of Heaven"));
		assertEquals(CardType.ROOM, board.getCardType("Training Grounds"));
	}
//	
	@Test
	public void testHumanComputerPlayer() {
		assertEquals(board.getPlayer("Blue"), board.getHumanPlayer());
		assertEquals(board.getPlayer("Red"), board.getComputerPlayer("Red"));
		assertEquals(6, board.getPlayerList().size());
	}

	@Test
	void Cards() {
		assertEquals(21, board.getCardDeck().size());
	}
	
	// tests that the solution cards are dealt correctly
	@Test
	void Solution() {
		assertTrue(board.getSolution().getPerson() != null);
		assertTrue(board.getSolution().getWeapon() != null);
		assertTrue(board.getSolution().getRoom() != null);
		assertTrue(board.getSolution().getPerson().getTypeOfCard() == CardType.PERSON);
		assertTrue(board.getSolution().getWeapon().getTypeOfCard() == CardType.WEAPON);
		assertTrue(board.getSolution().getRoom().getTypeOfCard() == CardType.ROOM);
	}
	
	// tests that players have cards and that they do not have any of the solution cards and it is
	// the correct number of cards
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
