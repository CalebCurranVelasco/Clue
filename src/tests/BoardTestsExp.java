package tests;

import java.util.Set;
import experiment.TestBoard;
import experiment.TestBoardCell;
import org.junit.Assert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTestsExp {
	TestBoard board;
	
	@BeforeEach
	public void setUp() {
		
		board = new TestBoard();
		
	}
	
	
	/*
	 * The test below tests the cell [0, 0] and ensures that [1, 0] and [0, 1] are the only adjacent spots in the list
	 */
	
	@Test
	public void testAdjacency() {
		
		TestBoardCell cell = board.getCell(0, 0);	// Sets cell
		Set<TestBoardCell> testList = cell.getAdjList();	// Gets list of all adjacent spots on board
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));	// Ensures that [1, 0] is an adjacent spot from [0, 0]
		Assert.assertTrue(testList.contains(board.getCell(0, 1)));	// Enures that [0, 1] is an adjacent spot from [0, 0]
		Assert.assertEquals(2, testList.size());
	}

	
	
	/*
	 * The test below tests the cell [0, 0] and ensures that, with a roll of 4, what are the possible cells that I could reach
	 * from that inital cell. 
	 */
	
	@Test
	public void testTargetsNormal() {
		
		TestBoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 4);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3,0)));
		Assert.assertTrue(targets.contains(board.getCell(2,1)));
		Assert.assertTrue(targets.contains(board.getCell(0,1)));
		Assert.assertTrue(targets.contains(board.getCell(1,2)));
		Assert.assertTrue(targets.contains(board.getCell(0,3)));
		Assert.assertTrue(targets.contains(board.getCell(1,0)));
		
		
	}
	
	
	/*
	 * The test below tests the cell [0, 3] and ensures that, with a roll of 3, what are the possible cells that I could reach
	 * from that initial cell. 
	 * 
	 * The cell [0, 0] is occupied and [2, 2] is a room. This means that [0, 0] is not a target, but [2, 2] should be because
	 * that cell indicates that we can enter the room through this cell. Therefore, we want to include this cell as a target.
	 */
	
	@Test
	public void testRoom() {
		
		board.getCell(0,0).setOccupied(true);
		board.getCell(2,2).setRoom(true);
		TestBoardCell cell = board.getCell(0, 3);
		board.calcTargets(cell, 3);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertFalse(targets.contains(board.getCell(0, 0)));
		Assert.assertTrue(targets.contains(board.getCell(1, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 3)));
		Assert.assertTrue(targets.contains(board.getCell(3, 3)));
		Assert.assertTrue(targets.contains(board.getCell(2, 2)));
		
	}
	
	/*
	 * The test below tests the cell [3, 0] and ensures that, with the max roll of 6, what are the possible cells that I could reach
	 * from that initial cell.
	 * 
	 * This test should pass once the code is implemented IF AND ONLY IF the tests above pass.
	 */

	@Test
	public void testMaxRoll() {
		
		TestBoardCell cell = board.getCell(3, 0);
		board.calcTargets(cell, 6);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCell(1, 0)));
		Assert.assertTrue(targets.contains(board.getCell(0, 1)));
		Assert.assertTrue(targets.contains(board.getCell(2, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(3, 2)));
		Assert.assertTrue(targets.contains(board.getCell(0, 3)));
		Assert.assertTrue(targets.contains(board.getCell(2, 3)));

	}
	
	/*
	 * The test below tests the cell [2, 2] and ensures that, with a roll of 3, what are the possible cells that I could reach
	 * from that initial cell.
	 * 
	 * This test should have 8 available spots for a 4x4 board. This will later have to be modified if it fails for actual clue board.
	 */
	
	@Test
	public void middleOfBoard() {
		TestBoardCell cell = board.getCell(2, 2);
		board.calcTargets(cell, 3);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCell(1, 0)));
		Assert.assertTrue(targets.contains(board.getCell(3, 0)));
		Assert.assertTrue(targets.contains(board.getCell(0, 1)));
		Assert.assertTrue(targets.contains(board.getCell(2, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(3, 2)));
		Assert.assertTrue(targets.contains(board.getCell(0, 3)));
		Assert.assertTrue(targets.contains(board.getCell(2, 3)));
	}
	
	
	/*
	 * The test below tests the cell [1, 2] and ensures that [1, 3] and [2, 2] are adjacent spots in the list.
	 * [1, 1] and [0, 2] are adjacent spots that have occupied that cell, but we're not testing whether the cell is occupied.
	 * We are simply testing to make sure that these 2 cells are adjacent to the initial one.
	 */
	@Test
	public void testMixedAdjacency() {
		board.getCell(1, 1).setOccupied(true);
		board.getCell(0, 2).setOccupied(true);
		TestBoardCell cell = board.getCell(1, 2);
		Set<TestBoardCell> testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(1, 3)));
		Assert.assertTrue(testList.contains(board.getCell(2, 2)));
		Assert.assertTrue(testList.contains(board.getCell(0, 2)));
		Assert.assertTrue(testList.contains(board.getCell(1, 1)));
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}


