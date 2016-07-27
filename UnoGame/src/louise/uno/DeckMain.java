package louise.uno;


import java.awt.Color;
import java.awt.color.ColorSpace;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import louise.uno.Card.CardValue;

public class DeckMain {
    @SuppressWarnings("unused")
	final private static int MAXCARDS  = 108;
    private static List<Card> deck= new ArrayList<Card>();
    private static List<Card>specialCard=new ArrayList<Card>();
    private static List<Card>normalCard=new ArrayList<Card>();
       
    public List<Card> prepareDeck(){
         
        initCards();
        shuffleCards();
        return deck;
    }
     
     
 public List<Card> prepareDeckSpecial(){
         
        initSpecialCards();
        shuffleCards();
        return specialCard;
    }
  
 public List<Card> prepareDeckNormal(){
     
    initNormalCards();
    shuffleCards();
        return normalCard;
 }
     
    public void initCards(){
        for(ColorSpace color:ColorSpace.values()){
        for(int j=0; j<2;j++){
            for(CardValue cardValue:Card.CardValue.values()){
                 if(j==1 && cardValue.equals(CardValue.ZERO)||j==1 && (cardValue.equals(CardValue.WILD)||cardValue.equals(CardValue.WILD_FOUR))){
                    continue;    
                 }
                 else if (cardValue.equals(CardValue.WILD)||cardValue.equals(CardValue.WILD_FOUR)) {
                     deck.add(new Card(cardValue, null));
                }
                 else{
                     deck.add(new Card(cardValue, color));
                 }
             
            }
         }
        }
         
    }
 
     
    public void initSpecialCards(){
        for(Color color:Color.values()){
        for(int j=0; j<2;j++){
            for(CardValue cardValue:Card.CardValue.values()){
                 if(j==1 && (cardValue.equals(CardValue.WILD)||cardValue.equals(CardValue.WILD_FOUR))){
                    continue;    
                 }
                 else if (cardValue.equals(CardValue.WILD)||cardValue.equals(CardValue.WILD_FOUR)) {
                     specialCard.add(new Card(cardValue, null));
                }
                 else if(cardValue.equals(CardValue.PICK_TWO)||cardValue.equals(CardValue.REVERSE)||cardValue.equals(CardValue.SKIP))
                     specialCard.add(new Card(cardValue, color));
                 }
             
            }
         }
        }
         
    public void initNormalCards(){
        for(Color color:Color.values()){
        for(int j=0; j<2;j++){
            for(CardValue cardValue:Card.CardValue.values()){
                 if(j==1 && cardValue.equals(CardValue.ZERO)){
                    continue;    
                 }
                if (!(cardValue.equals(CardValue.WILD)||cardValue.equals(CardValue.WILD_FOUR)||cardValue.equals(CardValue.PICK_TWO)||cardValue.equals(CardValue.SKIP)||cardValue.equals(CardValue.REVERSE))) {
                     normalCard.add(new Card(cardValue, color));
                 }
            }
        }
        }
             
             
          
        }
    public boolean canPlay(List<Card> list1,Card card) {
 
    for (int i=0; i<list1.size(); i++)
        if (((Card)list1.get(i)).canPlay(card))
            return true;
             
    // If we get here, no card was found.
    return false;
}
 
public boolean canPlay(Card card,Card otherCard) {
     
    // Try to find a card in our collection that can be played on c.
    if(card.canPlay(otherCard))
            return true;
             
         
    return false;
             
}
 
public boolean canPlayColor(List<Card> list1,Color otherCardColor) {
    for(Card tempCard :list1){
        if(tempCard.color.equals(otherCardColor)){
            return true;
        }
    }
    return false;
}
    public void shuffleCards(){
        Collections.shuffle(deck);
        Collections.shuffle(specialCard);
        Collections.shuffle(normalCard);
    }
    
}
