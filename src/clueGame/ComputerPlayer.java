package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer(String name, Color color) {
		super(name, color);
	}
	

	public ComputerPlayer(String name, Color color, int row, int col, boolean human) {
		super(name, color, row, col, human);
		// TODO Auto-generated constructor stub

	}

	

	@Override
	public void updateHand(Card card) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Solution createSuggestion(Card currRoom, ArrayList<Card> personList, ArrayList<Card> weaponList, ArrayList<Card> roomList) {
		Random random = new Random();
		
		ArrayList<Card> possiblePerson = new ArrayList<Card>();
		ArrayList<Card> possibleWeapon = new ArrayList<Card>();
		ArrayList<Card> possibleRoom = new ArrayList<Card>();
		for (Card person : personList) {
			if (!hand.contains(person) && !cardsSeen.containsKey(person)) {
				possiblePerson.add(person);
			}
		}
		for (Card weapon : weaponList) {
			if (!hand.contains(weapon) && !cardsSeen.containsKey(weapon)) {
				possibleWeapon.add(weapon);
			} 
		}
		int indexPerson = random.nextInt(possiblePerson.size());
		int indexWeapon = random.nextInt(possibleWeapon.size());
		Card personCard = possiblePerson.get(indexPerson % personList.size());
		Card weaponCard = possibleWeapon.get(indexWeapon % weaponList.size());
		Card roomCard = null;
		
		// if the room that we're in has already been seen, randomly pick another one
		if (hand.contains(currRoom) ||  cardsSeen.containsKey(currRoom)) {
			for (Card room : roomList) {
				if (!hand.contains(room) && !cardsSeen.containsKey(room)) {
					possibleRoom.add(room);
				}
			}
			if (!possibleRoom.isEmpty()) {
				int indexRoom = random.nextInt(possibleRoom.size());
				roomCard = possibleRoom.get(indexRoom % roomList.size());
			} else {
				roomCard = currRoom;
			}
			
		}
		else {
			roomCard = currRoom;
		}
		
		return new Solution(personCard, weaponCard, roomCard);
	}
	
	
	public BoardCell selectTarget(Set<BoardCell> targets, Map<Character, Room> roomMap, ArrayList<Card> cards) {
		if (targets.isEmpty()) {
			return null;
		}
				
		for (BoardCell target : targets) {
			if (target.isRoomCenter()) {
				Room room = roomMap.get(target.getInitial());
				String roomName = room.getName();
				Card roomCard = null;
				for (Card card : cards) {
					if (card.getCardName().equals(roomName)) {
						roomCard = card;
					}
				}
				if (!hand.contains(roomCard) && !cardsSeen.containsKey(roomCard)) {
					Player tempPlayer = new ComputerPlayer("Test", Color.BLACK);
					this.cardsSeen.put(roomCard, tempPlayer);
					return target;
				}
			}
		}
		// if no unseen room, return random target
		boolean filled = false;
		BoardCell option = null;
		int count = 0;
		while (filled == false && count <= targets.size()) {
			option = targets.stream().findAny().get();
			if (!option.isOccupied()) {
				filled = true;
			}
			count++;
		}
		return option;
	}
	

}
