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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
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
		grid = new BoardCell[numRows][numCols];
		for (int i=0; i<numRows; i++) {
			for (int j=0; j < numCols; j++) {
				grid[i][j] = new BoardCell(i, j);
			}
		}
    }
    
    public void setConfigFiles(String csvFile, String txtFile) {
    	this.layoutConfigFile = String.format("data/%d", csvFile);
    	this.setupConfigFile = String.format("data/%d", txtFile);
    }
    
    public void loadSetupConfig() throws BadConfigFormatException {
    	this.roomMap = new HashMap<Character, Room>();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(setupConfigFile));
			String line;
			
			while((line = in.readLine()) != null) {
				String[] roomInfo = line.split(",");	// Splits line at the commas, and we store into array for easy handling
				
				if(roomInfo[0] == "Room") {
					Room tempRoom = new Room(roomInfo[1], roomInfo[2]);	// Creates a new room object with room name and label
					roomMap.put(roomInfo[2], tempRoom);
				}
				else if(roomInfo[0] == "Space") {
					Room tempRoom = new Room(roomInfo[1], roomInfo[2]);
				}
			}				
		}
		 
		catch (FileNotFoundException e) {
    		System.out.println(e.getMessage());
		} 
		catch (IOException e1) {
			System.out.println(e1.getMessage());
		}	
    }
    
    public void loadLayoutConfig() throws BadConfigFormatException {
    	try (BufferedReader br = new BufferedReader(new FileReader(layoutConfigFile))){
    		String line;
    		numRows = 0;
    		numCols = 0;

    		// to get numRows and numCols
    		while ((line = br.readLine()) != null) {
    			String[] values = line.split(",");
    			
    			//test that our file is a square
    			if (numCols != 0 && numCols != values.length) {
    				throw new BadConfigFormatException();
    			}
    			numCols = values.length;
    			numRows += 1;
    		}
            br.close();
    		
    	} catch (FileNotFoundException e) {
    		System.out.println(e.getMessage());
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
    	
        try (BufferedReader br = new BufferedReader(new FileReader(setupConfigFile))){	
        	String line;
        	String[][] valuesArray = null;
        	valuesArray = new String[numRows][numCols];
        	
        	// populating the array with what letter it is in file
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				
				// checking that each room exists
				for (int i=0; i<values.length; i++) {
					if (roomMap.containsKey((values[i]))) {
						continue;
					} else {
						throw new BadConfigFormatException();
					}
				}
				
				for (int i=0; i<numRows; i++) {
					valuesArray[i] = values;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
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


