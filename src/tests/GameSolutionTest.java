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

class GameSolutionTest {

	private static Board board;
//
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

	
	// Check accusation tests
	@Test
	void testAccusation() {
		board.getSolution().setRoom(new Card("Dojo", CardType.ROOM));
		board.getSolution().setWeapon(new Card("Split Soul Katana", CardType.WEAPON));
		board.getSolution().setPerson(new Card("Gojo", CardType.PERSON));
//		Player player = new HumanPlayer("Caleb", Color.black, 1, 1, true);
		assertTrue(board.checkAccusation(new Card("Gojo", CardType.PERSON), new Card("Split Soul Katana", CardType.WEAPON), new Card("Dojo", CardType.ROOM)));
	}
	
	@Test
	void disproveSuggestion() {
		fail("Not yet implemented");
	}
	
	@Test
	void handleSuggesstion() {
		fail("Not yet implemented");
	}

}
