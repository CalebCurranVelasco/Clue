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
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;




import experiment.TestBoardCell;

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
    		this.loadLayoutConfig();
		} catch (BadConfigFormatException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public void setConfigFiles(String csvFile, String txtFile) {
    	this.layoutConfigFile = String.format("data/%s", csvFile);
    	this.setupConfigFile = String.format("data/%s", txtFile);
    }
    
    // The Text File
    public void loadSetupConfig() throws BadConfigFormatException {
    	this.roomMap = new HashMap<Character, Room>();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(setupConfigFile));
			String line;
			
			while((line = in.readLine()) != null) {
				String[] roomInfo = line.split(",");	// Splits line at the commas, and we store into array for easy handling
				
				if("Room".equals(roomInfo[0]) || "Space".equals(roomInfo[0])) {
//					System.out.println(roomInfo[2].charAt(1));
					Room tempRoom = new Room(roomInfo[1], roomInfo[2].charAt(1));	// Creates a new room object with room name and label
					String tempString = roomInfo[2];
					char roomLabel = tempString.charAt(1);	// Convert the room label from String to Char to store in map
					this.roomMap.put(roomLabel, tempRoom);
				}
				
			}
			in.close();
//		    for (Map.Entry<Character, Room> entry : roomMap.entrySet()) {
//		        char key = entry.getKey();
//		        Room value = entry.getValue();
//		        System.out.println("Key: " + key + ", Value: " + value);
//		    }
		} 
		catch (FileNotFoundException e) {
    		System.out.println(e.getMessage());
		} 
		catch (IOException e1) {
			System.out.println(e1.getMessage());
		}	
    }
    
    // The CSV file
    public void loadLayoutConfig() throws BadConfigFormatException {
//    	BOMInputStream bis = new BOMInputStream(layoutConfigFile.getInputStream());
    	try (BufferedReader br = new BufferedReader(new FileReader(layoutConfigFile, StandardCharsets.UTF_8))){
    		String line;
    		numRows = 0;
    		numCols = 0;
    		
            // Read the file and check for BOM
            if (br.markSupported()) {
                br.mark(1);
                int bomChar = br.read();
                if (bomChar != 0xFEFF) {
                    br.reset(); // Reset the reader position if there's no BOM
                }
            }

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
    		
    	} catch (FileNotFoundException e) {
    		System.out.println(e.getMessage());
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
    	
        try (BufferedReader br = new BufferedReader(new FileReader(layoutConfigFile, StandardCharsets.UTF_8))){	
        	String line;
            // Read the file and check for BOM
            if (br.markSupported()) {
                br.mark(1);
                int bomChar = br.read();
                if (bomChar != 0xFEFF) {
                    br.reset(); // Reset the reader position if there's no BOM
                }
            }
        	
        	// populating the array with what letter it is in file
        	int i=0;
			while ((line = br.readLine()) != null) {
				String[] values = line.strip().split(",");
				
				
				// checking that each room exists
				
				for (int j=0; j<values.length; j++) {
					System.out.println(values[j].length());
//					
					if (values[j].length() > 1 && values[j].charAt(1) != '#' && values[j].charAt(1) != '*' && values[j].charAt(1) != '^' && values[j].charAt(1) != '>' && values[j].charAt(1) != '<' && values[j].charAt(1) != 'v') {
						System.out.println(values[j].charAt(0));
						System.out.println(values[j].charAt(1));
						System.out.println("heeeeee");
						if (this.roomMap.containsKey(values[j].charAt(1))) {
							char[] detailsArr = values[j].toCharArray();
							this.grid[i][j] = new BoardCell(i,j);
//							System.out.println("before");
							grid[i][j].setDetails(detailsArr);
//							System.out.println("after");
							
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
					else if (this.roomMap.containsKey(values[j].charAt(0))) {
						char[] detailsArr = values[j].toCharArray();
						this.grid[i][j] = new BoardCell(i,j);
//						System.out.println("before");
						grid[i][j].setDetails(detailsArr);
//						System.out.println("after");
						
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
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
    }
    
	public BoardCell getCell(int row, int column) {
//		System.out.println(row + " " + column);
		return grid[row][column];
	}
	
	public Room getRoom(char label) {
		return roomMap.get(label);
	}
	
	public Room getRoom(BoardCell cell) {
		char label = this.grid[cell.getRow()][cell.getColumn()].getInitial();
		return roomMap.get(label);
	}
	
	public int getNumColumns() {
		return numCols;
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	
}