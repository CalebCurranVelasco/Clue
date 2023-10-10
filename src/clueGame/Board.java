/**
 * Board class
 * Holds the board for the clue game. 
 * 
 * @author Caleb Curran-Velasco
 * @author Joshua Ramirez Malerva
 * 
 * 10/9/2023
 */



package clueGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import experiment.TestBoardCell;

public class Board {
	
	private BoardCell[][] grid;
	private int numCols;
	private int numRows;
	private String layoutConfigFile;
	private String setupConfigFile;
	private Map<Character, Room> roomMap;
//	private Set<TestBoardCell> targets; add later
//	private Set<TestBoardCell> visited; add later
    
    /*
    * variable and methods used for singleton pattern
    */
    private static Board theInstance = new Board();
    // constructor is private to ensure only one can be created
    private Board() {
           super() ;
    }
    
    
    // this method returns the only Board
    public static Board getInstance() {
           return theInstance;
    }
   
    /*
     * initialize the board (since we are using singleton pattern)
     */
    public void initialize()
    {
//		grid = new BoardCell[numRows][numCols];
//		for (int i=0; i<numRows; i++) {
//			for (int j=0; j < numCols; j++) {
//				grid[i][j] = new BoardCell(i, j);
//			}
//		}
    }
    
    public void setConfigFiles(String csvFile, String txtFile) {
//    	this.layoutConfigFile = txtFile;
//    	this.setupConfigFile = csvFile;
    }
    
    public void loadSetupConfig() {
//    	try (BufferedReader br = new BufferedReader(new FileReader(setupConfigFile))){
//    		String line;
//    		String[][] valuesArray = null;
//    		numRows = 0;
//
//    		while ((line = br.readLine()) != null) {
//    			String[] values = line.split(",");
//    			if (valuesArray == null) {
//    				numCols = values.length;
//    				valuesArray = new String[numRows][numCols];
//    			}
//    			for (int i=0; i<numRows; i++) {
//    				valuesArray[i] = values;
//    			}
//    			numRows += 1;
//    		}
//    	} catch (BadConfigFormatEcxeption e) {
//    		e.message();
//    	}
    }
    
    public void loadLayoutConfig() {
    	
    }
    
	public BoardCell getCell(int row, int column) {
		return new BoardCell(row, column);
	}
	
	public Room getRoom(char label) {
		return new Room();
	}
	
	public Room getRoom(BoardCell cell) {
		return new Room();
	}
	
	public int getNumColumns() {
		return numCols;
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	
}


