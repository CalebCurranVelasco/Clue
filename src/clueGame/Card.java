package clueGame;

public class Card {
	private String cardName;
	private CardType typeOfCard = null;
	
	
	// THIS FUNCTION NOT COMPLETE
	public boolean equals(Card target) {
		if (this.cardName == target.cardName) {
			return true;
			
		}
		else { 
		return false;
		}
	}


	public Card(String cardName, CardType typeOfCard) {
		super();
		this.cardName = cardName;
		this.typeOfCard = typeOfCard;
	}
	
	
	
	
}
