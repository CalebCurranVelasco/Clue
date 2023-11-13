package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class HumanPlayer extends Player {

	public HumanPlayer(String name, Color color, int row, int col, boolean human) {
		super(name, color, row, col, human);
	}
	
	

	@Override
	public void updateHand(Card card) {
		// TODO Auto-generated method stub
		super.getCardsHeld().add(card);
	}



	@Override
	public Solution createSuggestion(Card currRoom, ArrayList<Card> personList, ArrayList<Card> weaponList,
			ArrayList<Card> roomList) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public BoardCell selectTarget(Set<BoardCell> targets, Map<Character, Room> roomMap, ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
