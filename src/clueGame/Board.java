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
import java.util.Map;

public class Board {
	
	public BoardCell[][] grid;
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
    	}
    	
    	catch (BadConfigFormatException e) {
    		System.out.println(e.getMessage());
    	}
    	catch (FileNotFoundException e1) {
    		System.out.println(e1.getMessage());
    	}
    	
    	try {
    		this.loadLayoutConfig();
    	}
    	
    	catch (BadConfigFormatException e) {
    		System.out.println(e.getMessage());
    	}
    	catch (FileNotFoundException e1) {
    		System.out.println(e1.getMessage());
    	}
    }
    
    
    /*
     * This method simply sets the layout config file and the setup config file
     */
    public void setConfigFiles(String csvFile, String txtFile) {
    	this.layoutConfigFile = String.format("data/%s", csvFile);
    	this.setupConfigFile = String.format("data/%s", txtFile);
    }
    
    /*
     * This method reads in the information from the txt file. We receive the room information including the room name and room label.
     * We create a new room with the info read in and add that room into a hash map with the room label as the key.
     */
    public void loadSetupConfig() throws BadConfigFormatException, FileNotFoundException {
    	this.roomMap = new HashMap<Character, Room>();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(setupConfigFile));
			String line;
			
			while((line = in.readLine()) != null) {
				String[] roomInfo = line.split(",");	// Splits line at the commas, and we store into array for easy handling
				
				if("Room".equals(roomInfo[0]) || "Space".equals(roomInfo[0])) {
					Room tempRoom = new Room(roomInfo[1], roomInfo[2].charAt(1));	// Creates a new room object with room name and label
					String tempString = roomInfo[2];
					char roomLabel = tempString.charAt(1);	// Convert the room label from String to Char to store in map
					this.roomMap.put(roomLabel, tempRoom);
				}
				
			}
			in.close();
		} 
		
		catch (IOException e1) {
			System.out.println(e1.getMessage());
		}	
    }
    
    /*
     * This method reads in the information from the csv file. We receive the board information including the rows and columns.
     * We read in the file for the 1st time to receive info on rows and columns of the board. We read in the file for a 2nd time
     * in order to populate the game board and setup room objects and assigning them to their specified cells.
     */
    public void loadLayoutConfig() throws BadConfigFormatException, FileNotFoundException {
    	
    	try (BufferedReader br = new BufferedReader(new FileReader(layoutConfigFile))){
    		String line;
    		numRows = 0;
    		numCols = 0;

    		// to get numRows and numCols
    		while ((line = br.readLine()) != null) {
    			String[] values = line.strip().split(",");
    			
    			//test that our file is a square
    			if (numCols != 0 && numCols != values.length) {
    				throw new BadConfigFormatException();
    			}
    			numCols = values.length;
    			numRows += 1;
    		}
            br.close();
            this.grid = new BoardCell[numRows+1][numCols+1];
    		
    	
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    	
        try (BufferedReader br = new BufferedReader(new FileReader(layoutConfigFile))){	
        	String line;
        	
        	// populating the array with what letter it is in file
        	int i=0;
			while ((line = br.readLine()) != null) {
				String[] values = line.strip().split(",");
				
				
				// checking that each room exists
				
				for (int j=0; j<values.length; j++) {
					
					if (this.roomMap.containsKey(values[j].charAt(0))) {
						char[] detailsArr = values[j].toCharArray();
						this.grid[i][j] = new BoardCell(i,j);
						grid[i][j].setDetails(detailsArr);
						
						if (grid[i][j].isRoomCenter()) {
							Room room = roomMap.get(grid[i][j].getInitial());
							room.setCenterCell(grid[i][j]);
						}
						else if (grid[i][j].isLabel()) {
							Room room = roomMap.get(grid[i][j].getInitial());
							room.setLabelCell(grid[i][j]);
						}
						
					} else {
						throw new BadConfigFormatException("Room not found in text file");
					}
				}
				i++;
				
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
    
    // Basic getter
	public BoardCell getCell(int row, int column) {
		return grid[row][column];
	}
	
    // Basic getter
	public Room getRoom(char label) {
		return roomMap.get(label);
	}
	
    // Basic getter
	public Room getRoom(BoardCell cell) {
		char label = this.grid[cell.getRow()][cell.getColumn()].getInitial();
		return roomMap.get(label);
	}
	
    // Basic getter
	public int getNumColumns() {
		return numCols;
	}
    
	// Basic getter
	public int getNumRows() {
		return numRows;
	}
	
	
	
	
}