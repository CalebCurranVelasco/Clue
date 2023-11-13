package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.Player;

class ComputerAITest {

	private static Board board;

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

	// tests that the computer will pick the only person and weapon it
	// hasn't seen. Also tests that if it hasn't seen the room it is 
	// in right now, then it guesses that room
	@Test
	void createSuggestionNotSeenRoom() {
		Player player = new ComputerPlayer("Sukuna", Color.RED);
		Card card = new Card("Dojo", CardType.ROOM);
		Card card1 = new Card("Split Soul Katana", CardType.WEAPON);
		Card card2 = new Card("Gojo", CardType.PERSON);
		Card card3 = new Card("Geto", CardType.PERSON);
		Card card4 = new Card("Nanami's Sword", CardType.WEAPON);
		Card card5 = new Card("Cursed Warehouse", CardType.ROOM);
		Card card6 = new Card("Sukuna", CardType.PERSON);
		Card card7 = new Card("Chain of a Thousand Miles", CardType.WEAPON);
		Card card8 = new Card("Temple", CardType.ROOM);
		ArrayList<Card> weaponList = new ArrayList<Card>();
		ArrayList<Card> personList = new ArrayList<Card>();
		ArrayList<Card> roomList = new ArrayList<Card>();
		weaponList.add(card1);
		weaponList.add(card4);
		weaponList.add(card7);
		// Has not seen card7
		board.getPlayer("red").addCardsHeld(card1);
		board.getPlayer("red").addCardsSeen(card4, player);
		// person
		personList.add(card2);
		personList.add(card3);
		personList.add(card6);
		// hasn't seen card 2
		board.getPlayer("red").addCardsHeld(card3);
		board.getPlayer("red").addCardsSeen(card6, player);
		//rooms
		roomList.add(card);
		roomList.add(card5);
		roomList.add(card8);
		board.getPlayer("red").addCardsSeen(card, player);
		// test weapon

		assertTrue(card7.equals(board.getPlayer("red").createSuggestion(card8, personList, weaponList, roomList).getWeapon()));
		assertTrue(card2.equals(board.getPlayer("red").createSuggestion(card8, personList, weaponList, roomList).getPerson()));
		assertTrue(card8.equals(board.getPlayer("red").createSuggestion(card8, personList, weaponList, roomList).getRoom()));
	}
	
	// tests that if the computer has seen the room its in it doesn't suggest it
	@Test
	void createSuggestionSeenRoom() {
		Player player = new ComputerPlayer("Sukuna", Color.RED);
		Card card = new Card("Dojo", CardType.ROOM);
		Card card1 = new Card("Split Soul Katana", CardType.WEAPON);
		Card card2 = new Card("Gojo", CardType.PERSON);
		Card card3 = new Card("Geto", CardType.PERSON);
		Card card4 = new Card("Nanami's Sword", CardType.WEAPON);
		Card card5 = new Card("Cursed Warehouse", CardType.ROOM);
		Card card6 = new Card("Sukuna", CardType.PERSON);
		Card card7 = new Card("Chain of a Thousand Miles", CardType.WEAPON);
		Card card8 = new Card("Temple", CardType.ROOM);
		ArrayList<Card> weaponList = new ArrayList<Card>();
		ArrayList<Card> personList = new ArrayList<Card>();
		ArrayList<Card> roomList = new ArrayList<Card>();
		weaponList.add(card1);
		weaponList.add(card4);
		weaponList.add(card7);
		// Has not seen card7
		board.getPlayer("red").addCardsHeld(card1);
		board.getPlayer("red").addCardsSeen(card4, player);
		// person
		personList.add(card2);
		personList.add(card3);
		personList.add(card6);
		// hasn't seen card 2
		board.getPlayer("red").addCardsHeld(card3);
		board.getPlayer("red").addCardsSeen(card6, player);
		//rooms
		roomList.add(card);
		roomList.add(card5);
		roomList.add(card8);
		board.getPlayer("red").addCardsSeen(card, player);
		// test weapon

		assertTrue(card7.equals(board.getPlayer("red").createSuggestion(card, personList, weaponList, roomList).getWeapon()));
		assertTrue(card2.equals(board.getPlayer("red").createSuggestion(card, personList, weaponList, roomList).getPerson()));
		assertFalse(card.equals(board.getPlayer("red").createSuggestion(card, personList, weaponList, roomList).getRoom()));
	}
	
	
	/*
	 * The test below creates a test for a target room
	 * that has not been seen yet. The test below asserts true
	 * for the line of code below because the computer player has
	 * the room within its list of targets. This happens only after we 
	 * calculate the targets.
	 */
	@Test
	void selectTargetRoomNotSeen() {
		board.calcTargets(board.getCell(9, 6), 1);
		Set<BoardCell> targets = board.getTargets();
		ArrayList<Card> cards = board.getCardDeck();
		assertTrue(board.getComputerPlayer("Red").selectTarget(targets, board.getRoomMap(), cards).isRoomCenter());
		
	}

}
