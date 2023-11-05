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

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import experiment.TestBoardCell;

public class Board {

	public BoardCell[][] grid;
	private int numCols;
	private int numRows;
	private String layoutConfigFile;
	private String setupConfigFile;
	private Map<Character, Room> roomMap;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	private ArrayList<Player> playerList;
	private ArrayList<Card> cardDeck;
	private boolean human = false;
	private Color currentColor;

	/*
	 * variable and methods used for singleton pattern
	 */
	private static Board theInstance = new Board();

	// constructor is private to ensure only one can be created
	private Board() {
		super();
	}

	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}

	/*
	 * initialize the board (since we are using singleton pattern)
	 */
	public void initialize() {
		// We have 2 different try/catch statements that will each catch their own
		// badConfigFormatException
		// This way, we know which file exactly is giving us the error.
		try {

			this.loadSetupConfig();
		}

		catch (BadConfigFormatException e) {
			System.out.println("Error occurred while processing ClueSetup.txt");
		} catch (FileNotFoundException e1) {
			System.out.println("Error loading in ClueSetup.txt file");
		}

		try {
			this.loadLayoutConfig();
		}

		catch (BadConfigFormatException e) {
			System.out.println("Error occurred while processing ClueLayout.csv");
		} catch (FileNotFoundException e1) {
			System.out.println("Error loading ClueLayout.csv");
		}

		calculateAdjacencies();
	}

	/*
	 * This method simply sets the layout config file and the setup config file
	 */
	public void setConfigFiles(String csvFile, String txtFile) {
		this.layoutConfigFile = String.format("data/%s", csvFile);
		this.setupConfigFile = String.format("data/%s", txtFile);
	}

	/*
	 * This method reads in the information from the txt file. We receive the room
	 * information including the room name and room label. We create a new room with
	 * the info read in and add that room into a hash map with the room label as the
	 * key.
	 */
	public void loadSetupConfig() throws BadConfigFormatException, FileNotFoundException {
		this.roomMap = new HashMap<Character, Room>();
		this.playerList = new ArrayList<Player>();
		this.cardDeck = new ArrayList<Card>();

		try {
			BufferedReader in = new BufferedReader(new FileReader(setupConfigFile));
			String line;

			while ((line = in.readLine()) != null) {
				String[] roomInfo = line.split(","); // Splits line at the commas, and we store into array for easy
				// handling

				// Only rooms should have a card made for them (9 room cards ONLY)
				if ("Room".equals(roomInfo[0])) {
					Room tempRoom = new Room(roomInfo[1], roomInfo[2].charAt(1)); // Creates a new room object with room
					// name and label
					String tempString = roomInfo[2];
					char roomLabel = tempString.charAt(1); // Convert the room label from String to Char to store in map
					this.roomMap.put(roomLabel, tempRoom);

					Card roomCard = new Card(roomInfo[1], CardType.ROOM); // Added the room as a card object here
					cardDeck.add(roomCard); // Send card to card deck

				}

				// We want to add the unused spaces and walkway spaces to the room map, but
				// don't make it a card
				else if ("Space".equals(roomInfo[0])) {
					Room tempRoom = new Room(roomInfo[1], roomInfo[2].charAt(1));
					String tempString = roomInfo[2];
					char roomLabel = tempString.charAt(1); // Convert the room label from String to Char to store in map
					this.roomMap.put(roomLabel, tempRoom);
				}

				// Code below creates all players from the txt file
				else if ("Player".equals(roomInfo[0])) {
					if (human == false) {
						int playerRow = Integer.parseInt(roomInfo[3]); // Reads in the row as int
						int playerCol = Integer.parseInt(roomInfo[4]); // Reads in the col as int
						Color playerColor = Color.getColor(roomInfo[2]); // Use Color class to convert string color to
						// color object

						Player humanPlayer = new HumanPlayer(roomInfo[1], playerColor, playerRow, playerCol); // Create human player
						Card humanCard = new Card(roomInfo[1], CardType.PERSON); // Need to create player card
						playerList.add(humanPlayer);
						cardDeck.add(humanCard); // Add human card to card deck
						human = true; // No longer need any more human players
					}
					// The code below works the same way as the code above
					else {
						int computerRow = Integer.parseInt(roomInfo[3]);
						int computerCol = Integer.parseInt(roomInfo[4]);
						Color computerColor = Color.getColor(roomInfo[2]);

						Player computerPlayer = new ComputerPlayer(roomInfo[1], computerColor, computerRow,
								computerCol);
						Card computerCard = new Card(roomInfo[1], CardType.PERSON);
						playerList.add(computerPlayer);
						cardDeck.add(computerCard);
					}

					// Weapons don't need much declaration since they're meant to be a type of card
					// only
				} else if ("Weapons".equals(roomInfo[0])) {
					Card weaponCard = new Card(roomInfo[1], CardType.WEAPON);
					cardDeck.add(weaponCard);

				}
			}
			in.close();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
	}

	/*
	 * This method reads in the information from the csv file. We receive the board
	 * information including the rows and columns. We read in the file for the 1st
	 * time to receive info on rows and columns of the board. We read in the file
	 * for a 2nd time in order to populate the game board and setup room objects and
	 * assigning them to their specified cells.
	 */
	public void loadLayoutConfig() throws BadConfigFormatException, FileNotFoundException {

		try (BufferedReader br = new BufferedReader(new FileReader(layoutConfigFile))) {
			String line;
			numRows = 0;
			numCols = 0;

			// to get numRows and numCols
			while ((line = br.readLine()) != null) {
				String[] values = line.strip().split(",");

				// test that our file is a square
				if (numCols != 0 && numCols != values.length) {
					throw new BadConfigFormatException();
				}

				numCols = values.length;
				numRows += 1;
			}

			br.close();
			this.grid = new BoardCell[numRows + 1][numCols + 1];

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		try (BufferedReader br = new BufferedReader(new FileReader(layoutConfigFile))) {
			String line;

			// populating the array with what letter it is in file
			int i = 0;
			while ((line = br.readLine()) != null) {
				String[] values = line.strip().split(",");

				// checking that each room exists

				for (int j = 0; j < values.length; j++) {

					if (this.roomMap.containsKey(values[j].charAt(0))) {
						char[] detailsArr = values[j].toCharArray();
						this.grid[i][j] = new BoardCell(i, j);
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

	public void calculateAdjacencies() {
		int[][] directions = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } }; // left, right, up, down
		char roomInitial;
		DoorDirection doorDirection;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				BoardCell currentCell = grid[i][j];

				// for walkways
				if (currentCell.getInitial() == 'W') {

					// add room center to adjList of door and visa versa
					if (currentCell.isDoorway() == true) {

						doorDirection = currentCell.getDoorDirection();
						if (doorDirection == DoorDirection.UP) {
							roomInitial = grid[i - 1][j].getInitial();
						}

						else if (doorDirection == DoorDirection.DOWN) {
							roomInitial = grid[i + 1][j].getInitial();
						}

						else if (doorDirection == DoorDirection.LEFT) {
							roomInitial = grid[i][j - 1].getInitial();
						}

						else {
							roomInitial = grid[i][j + 1].getInitial();
						}

						// setting the roomCenter cell as an adjCell to doorway and visa versa
						Room centerRoom = roomMap.get(roomInitial);
						BoardCell centerCell = centerRoom.getCenterCell();
						currentCell.addAdjacency(centerCell);
						centerCell.addAdjacency(currentCell);
					}

					// iterate through all four possible directions
					for (int k = 0; k < directions.length; k++) {
						// update coordinates and see if legal
						int newRow = i + directions[k][0];
						int newCol = j + directions[k][1];

						if (newRow >= 0 && newCol >= 0 && newRow < numRows && newCol < numCols
								&& grid[newRow][newCol].getInitial() == 'W') {
							currentCell.addAdjacency(grid[newRow][newCol]);
						}
					}
				}

				// for room center add secret Passage to adjList
				if (currentCell.isRoomCenter()) {
					roomInitial = currentCell.getInitial();
					char roomToAdd = ' ';
					boolean secretPass = false;

					for (int x = 0; x < numRows; x++) {
						if (secretPass == false) {

							for (int y = 0; y < numCols; y++) {
								if (grid[x][y].getInitial() == roomInitial && grid[x][y].isSecretPassage()) {
									roomToAdd = grid[x][y].getSecretPassage();
									secretPass = true;
									break;
								}
							}
						}
					}
					if (secretPass == true) {
						boolean found = false;

						for (int x = 0; x < numRows; x++) {
							if (found == false) {

								for (int y = 0; y < numCols; y++) {
									if (grid[x][y].getInitial() == roomToAdd && grid[x][y].isRoomCenter()) {
										currentCell.addAdjacency(grid[x][y]);
										found = true;
										break;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public void findAllTargets(BoardCell startCell, int pathLength) {
		Set<BoardCell> adjList = startCell.getAdjList();
		for (BoardCell cell : adjList) {
			if (visited.contains(cell) || (cell.isOccupied() && !cell.isRoomCenter())) {
				continue;
			} else {
				visited.add(cell);

				if (pathLength == 1 || cell.isRoomCenter()) { // found target since adj cells are one cell away
					targets.add(cell);
					startCell.addAdjacency(cell);
				} else {
					findAllTargets(cell, pathLength - 1); // recursive call
				}
				visited.remove(cell);
			}
		}
	}

	public void calcTargets(BoardCell startCell, int pathLength) {
		this.visited = new HashSet<BoardCell>();
		this.targets = new HashSet<BoardCell>();
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
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

	public Set<BoardCell> getAdjList(int row, int col) {
		return grid[row][col].getAdjList();
	}

	public Set<BoardCell> getTargets() {
		return targets;
	}

	public Player getPlayer(String playerColor) {
		currentColor = Color.getColor(playerColor);
		for (Player player : playerList) {
			if (player.getColor() == currentColor) {
				return player;

			}
		}
		return null;
	}

	/*
	 * Function below is supposed to convert the string color representation
	 * into a proper Color object for GUI use later.
	 */
	public Color getColor(String colorName) {
		switch (colorName.toLowerCase()) {
		case "blue":
			currentColor = Color.BLUE;
			break;
		case "magenta":
			currentColor = Color.MAGENTA;
			break;
		case "red":
			currentColor = Color.RED;
			break;
		case "green":
			currentColor = Color.GREEN;
			break;
		case "black":
			currentColor = Color.BLACK;
			break;
		case "white":
			currentColor = Color.WHITE;
			break;
		}
		return currentColor;

	}

}