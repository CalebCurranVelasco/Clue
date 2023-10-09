package clueGame;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import experiment.TestBoardCell;

public class Board {
	private BoardCell[][] grid;
	private int numCols;
	private int numRows;
	private String layoutConfigFile;
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
    }
    
    public void setConfigFiles(String csvFile, String txtFile) {
    	
    }
    
    public void loadSetupConfig() {
    	
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


