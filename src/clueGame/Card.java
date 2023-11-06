package clueGame;

public class Card {
	private String cardName;
	private CardType typeOfCard = null;
	
	public Card(String cardName, CardType typeOfCard) {
		super();
		this.cardName = cardName;
		this.typeOfCard = typeOfCard;
	}
	
	
	
	public String getCardName() {
		return cardName;
	}



	public void setCardName(String cardName) {
		this.cardName = cardName;
	}



	public CardType getTypeOfCard() {
		return typeOfCard;
	}



	public void setTypeOfCard(CardType typeOfCard) {
		this.typeOfCard = typeOfCard;
	}



	// THIS FUNCTION NOT COMPLETE
	public boolean equals(Card target) {
		if (this.cardName == target.cardName && this.typeOfCard == target.typeOfCard) {
			return true;
		} else { 
			return false;
		}
	}



	
	
	
	
}
