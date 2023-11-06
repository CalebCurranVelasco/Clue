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
//	@Test
//	public void testHumanPlayer() {
//		assertEquals(board.getPlayer("Blue"), board.getHumanPlayer());
//		assertEquals(board.getPlayer("Red"), board.getComputerPlayer("Red"));
//		assertEquals(6, playerList.size());
//	}
//	
//	@Test
//	void Cards() {
//		assertEquals(21, deck.size());
//		
//	}
//	
//	@Test
//	void Solution() {
//		assertTrue(solution.getWeapon() != null);
//		assertTrue(solution.getPlayer() != null);
//		assertTrue(solution.getRoom() != null);
//	}
//	
//	@Test
//	void cardDealtToPlayer() {
//		assertTrue(player.getWeapon() != null);
//	}

}
