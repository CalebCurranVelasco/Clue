package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTest {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;

	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout306.csv", "ClueSetup306.txt");		
		// Initialize will load config files 
		board.initialize();
	}

	// Ensure that player does not move around within room
	// These cells are YELLOW on the planning spreadsheet
	@Test
	public void testAdjacencyRooms()
	{
		// Testing Shrine's center (Should only include the door)
		Set<BoardCell> testList = board.getAdjList(2, 3);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCell(3, 6)));

		
		
		// Testing Training Grounds Center (has a doorway and Dojo's center)
		testList = board.getAdjList(14, 2);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(16, 2)));
		assertTrue(testList.contains(board.getCell(2, 20)));
		
	}


	// Ensure door locations include their rooms and also additional walkways
	// These cells are GREEN on the planning spreadsheet
	@Test
	public void testAdjacencyDoors()
	{
		// Testing Hidden Room door with correponding adjacencies (with center)
		
		Set<BoardCell> testList = board.getAdjList(9, 6);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(8, 6)));
		assertTrue(testList.contains(board.getCell(9, 7)));
		assertTrue(testList.contains(board.getCell(10, 6)));
		assertTrue(testList.contains(board.getCell(9, 2)));


		// Testing Isolation Chamber door with corresponding adjacencies (with center)
		testList = board.getAdjList(9, 17);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(8, 17)));
		assertTrue(testList.contains(board.getCell(9, 16)));
		assertTrue(testList.contains(board.getCell(10, 17)));
		assertTrue(testList.contains(board.getCell(9, 20)));
	}

	// Test a variety of walkway scenarios
	// These tests are ORANGE on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Testing the walkway on the right edge of the board (1 adjacency only)
		Set<BoardCell> testList = board.getAdjList(13, 23);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCell(13, 22)));

		// Testing the walkway next to the Forest room (2 adjacencies only)
		testList = board.getAdjList(19, 16);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(19, 15)));
		assertTrue(testList.contains(board.getCell(20, 16)));
		

		// Testing the walkway on middle of board next to unused space (3 adjacencies only)
		testList = board.getAdjList(7, 10);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(7, 9)));
		assertTrue(testList.contains(board.getCell(7, 11)));
		assertTrue(testList.contains(board.getCell(6, 10)));

		// Testing the walkway on middle right of board (4 adjacencies only)
		testList = board.getAdjList(8,15);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(8, 16))); // Spot to the right
		assertTrue(testList.contains(board.getCell(9, 15))); // Spot to the bottom
		assertTrue(testList.contains(board.getCell(7, 15))); // Spot to the left 
		assertTrue(testList.contains(board.getCell(7, 15))); // Spot to the top

	}


	// Testing the Cursed Warehouse's center using rolls 1, 3, and 4
	// These are LIGHT GRAY on the planning spreadsheet
	@Test
	public void testTargetsInCursedWarehouse() {
		// test a roll of 1
		board.calcTargets(board.getCell(2, 13), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCell(5, 12)));

		// test a roll of 3
		board.calcTargets(board.getCell(2, 13), 3);
		targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(4, 11)));
		assertTrue(targets.contains(board.getCell(5, 10)));	
		assertTrue(targets.contains(board.getCell(6, 11)));
		assertTrue(targets.contains(board.getCell(7, 12)));	
		assertTrue(targets.contains(board.getCell(6, 13)));	
		assertTrue(targets.contains(board.getCell(5, 14)));	


		// test a roll of 4 (Tested all 11 targets to ensure pass)
		board.calcTargets(board.getCell(2, 13), 4);
		targets= board.getTargets();
		assertEquals(11, targets.size());
		assertTrue(targets.contains(board.getCell(4, 10)));
		assertTrue(targets.contains(board.getCell(4, 14)));	
		assertTrue(targets.contains(board.getCell(5, 9)));
		assertTrue(targets.contains(board.getCell(5, 11)));	
		assertTrue(targets.contains(board.getCell(5, 13)));
		assertTrue(targets.contains(board.getCell(5, 15)));	
		assertTrue(targets.contains(board.getCell(6, 10)));
		assertTrue(targets.contains(board.getCell(6, 12)));	
		assertTrue(targets.contains(board.getCell(6, 14)));	
		assertTrue(targets.contains(board.getCell(7, 11)));	
		assertTrue(targets.contains(board.getCell(7, 13)));
	}

	@Test
	public void testTargetsInHiddenRoom() {
		// test a roll of 1
		board.calcTargets(board.getCell(9, 2), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(9, 6)));
		assertTrue(targets.contains(board.getCell(19, 20))); // Center of the Forest room (Secret passage to Forest)

		// test a roll of 3
		board.calcTargets(board.getCell(9, 2), 3);
		targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(7, 6)));
		assertTrue(targets.contains(board.getCell(8, 7)));	
		assertTrue(targets.contains(board.getCell(9, 8)));
		assertTrue(targets.contains(board.getCell(10, 7)));	
		assertTrue(targets.contains(board.getCell(11, 6)));	
		assertTrue(targets.contains(board.getCell(19, 20))); // Center of the Forest room (Secret passage to Forest)


		// test a roll of 4 (Tested all 11 targets to ensure pass)
		board.calcTargets(board.getCell(9, 2), 4);
		targets= board.getTargets();
		assertEquals(11, targets.size());
		assertTrue(targets.contains(board.getCell(8, 6)));
		assertTrue(targets.contains(board.getCell(6, 6)));	
		assertTrue(targets.contains(board.getCell(7, 7)));
		assertTrue(targets.contains(board.getCell(8, 8)));	
		assertTrue(targets.contains(board.getCell(9, 7)));	
		assertTrue(targets.contains(board.getCell(10, 6)));	
		assertTrue(targets.contains(board.getCell(10, 8)));	
		assertTrue(targets.contains(board.getCell(11, 5)));	
		assertTrue(targets.contains(board.getCell(11, 7)));	
		assertTrue(targets.contains(board.getCell(12, 6)));	
		assertTrue(targets.contains(board.getCell(19, 20))); // Center of the Forest room (Secret passage to Forest)

	}

	// Testing targets at a door and different walkways with a roll of 1, 3, and 4
	// These are LIGHT GRAY on the planning spreadsheet
	@Test
	public void testTargetsAtDoor() {
		
		// test a roll of 1, at the Forest's door
		board.calcTargets(board.getCell(17, 15), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(19, 20)));	// Center of forest room
		assertTrue(targets.contains(board.getCell(16, 15)));	
		assertTrue(targets.contains(board.getCell(18, 15)));	
		assertTrue(targets.contains(board.getCell(17, 14)));	


		// test a roll of 3
		board.calcTargets(board.getCell(17, 15), 3);
		targets= board.getTargets();
		assertEquals(12, targets.size());
		assertTrue(targets.contains(board.getCell(19, 20)));	
		assertTrue(targets.contains(board.getCell(14, 15)));
		assertTrue(targets.contains(board.getCell(15, 14)));	
		assertTrue(targets.contains(board.getCell(15, 16)));
		assertTrue(targets.contains(board.getCell(16, 13)));	
		assertTrue(targets.contains(board.getCell(16, 15)));	
		assertTrue(targets.contains(board.getCell(17, 12)));	
		assertTrue(targets.contains(board.getCell(17, 14)));	
		assertTrue(targets.contains(board.getCell(18, 13)));
		assertTrue(targets.contains(board.getCell(18, 15)));
		assertTrue(targets.contains(board.getCell(19, 14)));	
		assertTrue(targets.contains(board.getCell(19, 16)));	
		assertTrue(targets.contains(board.getCell(20, 15)));	


		// test a roll of 4
		board.calcTargets(board.getCell(17, 15), 4);
		targets= board.getTargets();
		assertEquals(15, targets.size());
		assertTrue(targets.contains(board.getCell(19, 20)));
		assertTrue(targets.contains(board.getCell(13, 15)));
		assertTrue(targets.contains(board.getCell(14, 14)));	
		assertTrue(targets.contains(board.getCell(14, 16)));
		assertTrue(targets.contains(board.getCell(15, 13)));
		assertTrue(targets.contains(board.getCell(15, 15)));
		assertTrue(targets.contains(board.getCell(16, 12)));
		assertTrue(targets.contains(board.getCell(16, 14)));
		assertTrue(targets.contains(board.getCell(17, 11)));
		assertTrue(targets.contains(board.getCell(17, 13)));
		assertTrue(targets.contains(board.getCell(18, 12)));
		assertTrue(targets.contains(board.getCell(18, 14)));
		assertTrue(targets.contains(board.getCell(19, 13)));	
		assertTrue(targets.contains(board.getCell(19, 15)));
		assertTrue(targets.contains(board.getCell(20, 16)));	
		assertTrue(targets.contains(board.getCell(21, 15)));
		
		



		
	}

	@Test
	public void testTargetsInWalkway1() {
		// test a roll of 1
		board.calcTargets(board.getCell(5, 0), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCell(5, 1)));	

		// test a roll of 3
		board.calcTargets(board.getCell(5, 0), 3);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(4, 2)));
		assertTrue(targets.contains(board.getCell(5, 3)));
		assertTrue(targets.contains(board.getCell(6, 2)));	

		// test a roll of 4
		board.calcTargets(board.getCell(5, 0), 4);
		targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(4, 1)));
		assertTrue(targets.contains(board.getCell(4, 3)));
		assertTrue(targets.contains(board.getCell(5, 2)));	
		assertTrue(targets.contains(board.getCell(5, 4)));	
		assertTrue(targets.contains(board.getCell(6, 1)));
		assertTrue(targets.contains(board.getCell(6, 3)));	
	}

	@Test
	public void testTargetsInWalkway2() {
		// test a roll of 1
		board.calcTargets(board.getCell(18, 6), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(17, 6)));
		assertTrue(targets.contains(board.getCell(18, 7)));
		assertTrue(targets.contains(board.getCell(19, 6)));
		assertTrue(targets.contains(board.getCell(18, 5)));

		
		// test a roll of 3
		board.calcTargets(board.getCell(18, 6), 3);
		targets= board.getTargets();
		assertEquals(13, targets.size());
		assertTrue(targets.contains(board.getCell(15, 6)));
		assertTrue(targets.contains(board.getCell(16, 5)));
		assertTrue(targets.contains(board.getCell(16, 7)));	
		assertTrue(targets.contains(board.getCell(17, 4)));
		assertTrue(targets.contains(board.getCell(17, 6)));
		assertTrue(targets.contains(board.getCell(17, 8)));
		assertTrue(targets.contains(board.getCell(18, 5)));
		assertTrue(targets.contains(board.getCell(18, 7)));
		assertTrue(targets.contains(board.getCell(18, 9)));
		assertTrue(targets.contains(board.getCell(19, 6)));
		assertTrue(targets.contains(board.getCell(20, 5)));
		assertTrue(targets.contains(board.getCell(21, 6)));


		// test a roll of 4
		board.calcTargets(board.getCell(18, 6), 4);
		targets= board.getTargets();
		assertEquals(18, targets.size());
		assertTrue(targets.contains(board.getCell(14, 6)));
		assertTrue(targets.contains(board.getCell(15, 5)));
		assertTrue(targets.contains(board.getCell(15, 7)));	
		assertTrue(targets.contains(board.getCell(16, 4)));
		assertTrue(targets.contains(board.getCell(16, 6)));
		assertTrue(targets.contains(board.getCell(16, 8)));
		assertTrue(targets.contains(board.getCell(17, 3)));
		assertTrue(targets.contains(board.getCell(17, 5)));
		assertTrue(targets.contains(board.getCell(17, 7)));
		assertTrue(targets.contains(board.getCell(17, 9)));
		assertTrue(targets.contains(board.getCell(18, 8)));
		assertTrue(targets.contains(board.getCell(18, 10)));
		assertTrue(targets.contains(board.getCell(19, 5)));
		assertTrue(targets.contains(board.getCell(19, 7)));
		assertTrue(targets.contains(board.getCell(19, 9)));
		assertTrue(targets.contains(board.getCell(20, 6)));
		assertTrue(targets.contains(board.getCell(21, 5)));
		assertTrue(targets.contains(board.getCell(22, 6)));


		
	}

	@Test
	// test to make sure occupied locations do not cause problems
	public void testTargetsOccupied() {
		
		// testing a roll of 3 at [13, 20] with [13, 18] occupied
		board.getCell(13, 18).setOccupied(true);
		board.calcTargets(board.getCell(13, 20), 3);
		board.getCell(13, 18).setOccupied(false);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCell(12, 18)));
		assertTrue(targets.contains(board.getCell(14, 18)));
		assertTrue(targets.contains(board.getCell(13, 19)));	
		assertFalse(targets.contains( board.getCell(13, 17)));	// Space that's occupied

		// we want to make sure we can get into a room, even if flagged as occupied
		board.getCell(5, 13).setOccupied(true);
		board.getCell(2, 13).setOccupied(true);		// This space is the Cursed Warehouse's room center
		board.calcTargets(board.getCell(5, 12), 1);
		board.getCell(5, 13).setOccupied(false);
		board.getCell(2, 13).setOccupied(false);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(5, 11)));	
		assertTrue(targets.contains(board.getCell(6, 12)));	
		assertTrue(targets.contains(board.getCell(2, 13)));	// The room center is still a target even if occupied

		// check leaving a room with a blocked doorway
		board.getCell(16, 2).setOccupied(true);
		board.calcTargets(board.getCell(14, 2), 3);
		board.getCell(16, 2).setOccupied(false);
		targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCell(2, 20))); // The only way out the room is through the secret passage
		

	}
}


