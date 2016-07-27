package louise.uno;

import java.awt.color.ColorSpace;

import louise.uno.Card.CardValue;

@SuppressWarnings("unused")
public class Card {

	public enum Color { YELLOW, BLUE, GREEN, RED; }
	 
    public enum CardValue { ZERO,ONE,TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, 
                       NINE, PICK_TWO, REVERSE, SKIP, WILD, WILD_FOUR; }
     
    CardValue cardValue;
    Color color;

public Card ( CardValue cardValue, Color colorSpace) {
    this.cardValue = cardValue;
    this.color = colorSpace;
}
    
    public Card(CardValue cardValue2, ColorSpace color2) {
	// TODO Auto-generated constructor stub
}

	public String toString(){
        if(cardValue.equals(CardValue.WILD)||cardValue.equals(CardValue.WILD_FOUR)){
            return cardValue.name();
        }
        else{
        return cardValue.name()+"-"+color.name();
        }
        }
     
    public boolean canPlay(Card otherCard) {
         
        return this.cardValue == otherCard.cardValue || this.color == otherCard.color;
    }
     
  public boolean canPlayColor(Card otherCard) {
         
        return this.color == otherCard.color;
    }
     
} 

