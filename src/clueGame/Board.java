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
    	try {
    		this.loadSetupConfig();
    		this.loadLayoutConfig();
		} catch (BadConfigFormatException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public void setConfigFiles(String csvFile, String txtFile) {
    	this.layoutConfigFile = String.format("data/%s", csvFile);
    	this.setupConfigFile = String.format("data/%s", txtFile);
    }
    
    public void loadSetupConfig() throws BadConfigFormatException {
    	this.roomMap = new HashMap<Character, Room>();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(setupConfigFile));
			String line;
			
			while((line = in.readLine()) != null) {
				String[] roomInfo = line.split(",");	// Splits line at the commas, and we store into array for easy handling
				
				if("Room".equals(roomInfo[0])) {
					Room tempRoom = new Room(roomInfo[1], roomInfo[2].charAt(0));	// Creates a new room object with room name and label
					String tempString = roomInfo[2];
					char roomLabel = tempString.charAt(1);	// Convert the room label from String to Char to store in map
					this.roomMap.put(roomLabel, tempRoom);
				}
				else if("Space".equals(roomInfo[0])) {
					Room tempRoom = new Room(roomInfo[1], roomInfo[2].charAt(0));
					String tempString = roomInfo[2];					
					char roomLabel = tempString.charAt(1);	// Convert the room label from String to Char to store in map
					this.roomMap.put(roomLabel, tempRoom);
				}
			}
			in.close();
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
            this.grid = new BoardCell[numRows][numCols];
    		
    	} catch (FileNotFoundException e) {
    		System.out.println(e.getMessage());
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
    	
        try (BufferedReader br = new BufferedReader(new FileReader(layoutConfigFile))){	
        	String line;
        	
        	// populating the array with what letter it is in file
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				
				// checking that each room exists
				for (int i=0; i<values.length; i++) {
					if (this.roomMap.containsKey(values[i].charAt(0))) {
						
						for (int j=0; j<numCols; j++) {
							char[] detailsArr = values[j].toCharArray();
							System.out.println(detailsArr);
							this.grid[i][j] = new BoardCell(i,j,detailsArr);
						}
						
					} else {
						throw new BadConfigFormatException("Room not found in text file");
					}
				}
				
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
    }
    
	public BoardCell getCell(int row, int column) {
		return this.grid[row][column];
	}
	
	public Room getRoom(char label) {
		return roomMap.get(label);
	}
	
	public Room getRoom(BoardCell cell) {
		char initial = this.getCell(cell.getRow(),cell.getColumn()).getInitial();
		return this.getRoom(initial);
	}
	
	public int getNumColumns() {
		return numCols;
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	
}