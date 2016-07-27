package louise.uno;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import louise.uno.Card.Color;

public class UNOGame {
    final private static int NUMCARDSHAND = 7;
 
    private DeckMain deckMain;
    private Boolean cardSpecialFlag;
    private List <Card>finalDeck;
    private List<Card> discardPile;
    private List<Card> hand1;
    private List<Card> hand2;
    private List<Card> specialCards;
    private List<Card> normalCards;
    private String player1name;
    private String player2name;
    private Serializable colorChosen;
    private int cardNumber;
    private boolean wildColorFlagFirst;
    private boolean wildColorFlagSecond;
    private boolean firstTimePlay;
    int turn=0;
    public UNOGame(){
        initVariables();
        for (int i=0; i<NUMCARDSHAND; i++) {
            hand1.add((Card)finalDeck.get(finalDeck.size()-1));
            finalDeck.remove(finalDeck.size()-1);
            hand2.add((Card)finalDeck.get(finalDeck.size()-1));
            finalDeck.remove(finalDeck.size()-1);
        }
    }
     
public void playGame() {
         
        Scanner stdin = new Scanner(System.in);
        System.out.print("player1 Enter your name:\n");
        player1name=stdin.nextLine();
        System.out.print("player2 Enter your name:\n");
        player2name=stdin.nextLine();
        turn=1;
        while (finalDeck.size() > 0 && hand1.size() > 0 && hand2.size() > 0) {
              cardSpecialFlag=false;    
              playTurn(turn);
            if (turn == 1)
                turn = 2;
            else
                turn = 1;   
        }
         printResult();
        }
         
    //}
public void printResult() {
    if (finalDeck.size() == 0)
        System.out.println("Sorry, the game has ended in a draw.");
    else if (hand1.size() == 0)
        System.out.println(player1name+" Congratulations, you win =)");
    else
        System.out.println(player2name+" Congratulations, you win =)");
}
 
// Plays one turn for the player number indicated.
public void playTurn(int player) {
    Scanner stdin = new Scanner(System.in);
     
    if(!wildColorFlagFirst){
        if(!firstTimePlay)
    System.out.println("The card at the top of the discard pile is "+discardPile.get(discardPile.size()-1));
    }
    else{
        System.out.println("Card you have to discard is of chosen color: "+colorChosen);
    }
    if(firstTimePlay){
        firstTimePlay=false;
        discardPile.add(normalCards.get(normalCards.size()-1));
        System.out.println("The card at the top of the discard pile is "+discardPile.get(discardPile.size()-1));
    }
            if (player == 1) {
                if(wildColorFlagFirst){
                    if(deckMain.canPlayColor(hand1, colorChosen)){
                        wildColorFlagFirst=false;
                        System.out.println("\n"+ player1name+", here is your hand:\n"+hand1);
                        System.out.println("Which card would you like to discard? Please give the associated number."); 
                        try{
                            cardNumber = stdin.nextInt();
                        if(!(cardNumber>=0 && cardNumber<hand1.size()-1)){
                            throw new Exception();
                        }
                        }
                        catch (Exception e) {
                            System.out.println("Number is not valid .it should be between 0 and "+hand1.size() +" \ngame is exiting now");
                            System.exit(1);
                        }
                               specialCardProcessing(cardNumber,hand1);
                               if(!cardSpecialFlag){
                                discardPile.add(hand1.get(cardNumber));
                                hand1.remove(cardNumber);
                               }
                                
                             
                    }
                    else{
                        System.out.println("Sorry that is not a valid card. You lost your opportunity to drop a card. A card has been drawn for you. "+finalDeck.get(finalDeck.size()-1));
                        finalDeck.remove(finalDeck.size()-1);
                        }
                       }
               
                if (deckMain.canPlay(hand1,(Card)discardPile.get(discardPile.size()-1))) {
                        System.out.println("\n"+ player1name+", here is your hand:\n"+hand1);
                        System.out.println("Which card would you like to discard? Please give the associated number.");
                    try {   
                            cardNumber = stdin.nextInt();
                            if(!(cardNumber>=0 && cardNumber<hand1.size())){   throw new Exception();}
                            }
             
            catch (Exception e) {
                System.out.println("Number is not valid .it should be between 0 and "+(hand1.size()-1) +" \ngame is exiting now");
                System.exit(1);
            }
            specialCardProcessing(cardNumber,hand1);
             
         
            if(!cardSpecialFlag){
            // Only play this card if it's really valid!
            if (deckMain.canPlay((Card)hand1.get(cardNumber),(Card)discardPile.get(discardPile.size()-1))){
                discardPile.add(hand1.get(cardNumber));
                hand1.remove(cardNumber);
            }
            else
                System.out.println("Sorry that is not a valid card. You lost your opportunity to drop a card.");
             
            // UNO =)
            if (hand1.size() == 1)
                System.out.println(player1name+" Player One says UNO!!!!");
        }
             
        }
         
        // Add a card and show the result.
        else {
            System.out.println("Sorry, you can't play on this card " +player1name+ " Your hand is --> "+ hand1);
            System.out.println("\n A card has been drawn for you. "+finalDeck.get(finalDeck.size()-1));
            hand1.add((Card)finalDeck.get(finalDeck.size()-1));
            finalDeck.remove(finalDeck.size()-1);
            System.out.println(player1name+", here is your resulting hand:\n"+hand1);
        }
    }
    else {
        if(wildColorFlagSecond){
            if(deckMain.canPlayColor(hand2, colorChosen)){
                wildColorFlagSecond=false;
                System.out.println("\n"+ player2name+", here is your hand:\n"+hand2);
                System.out.println("What card would you like to discard? Please give the associated number."); 
                try{
                    cardNumber = stdin.nextInt();
                if(!(cardNumber>=0 && cardNumber<=hand1.size()-1)){
                    throw new Exception();
                }
                }
                catch (Exception e) {
                    System.out.println("Number is not valid .it should be between 0 and "+(hand1.size()-1) +" \ngame is exiting now");
                    System.exit(1);
                }
                specialCardProcessing(cardNumber,hand2);
                       if(!cardSpecialFlag){
                        discardPile.add(hand2.get(cardNumber));
                        hand2.remove(cardNumber);
                         }
                     
            }
            else{
                System.out.println("Sorry you dont have  a valid card. You lost your opportunity to drop a card. A card has been drawn for you. "+finalDeck.get(finalDeck.size()-1));
                finalDeck.remove(finalDeck.size()-1);
                }
               }
         
     
        if (deckMain.canPlay(hand2,(Card)discardPile.get(discardPile.size()-1))) {
            System.out.println(player2name+", here is your hand:\n"+hand2);
            System.out.println("What card would you like to discard? Please give the associated number.");
            try{
                cardNumber = stdin.nextInt();
            if(!(cardNumber>0 && cardNumber<hand2.size())){
                throw new Exception();
            }
            }
            catch (Exception e) {
                System.out.println("Number is not valid .it should be between 0 and "+(hand2.size()-1) +" \ngame is exiting now");
                System.exit(1);
            }
            specialCardProcessing(cardNumber,hand2);
            if(!cardSpecialFlag){
            // Only play this card if it's really valid!
            if (deckMain.canPlay((Card)hand2.get(cardNumber),(Card)discardPile.get(discardPile.size()-1))){
                 
                discardPile.add(hand2.get(cardNumber));
                hand2.remove(cardNumber);
            }
            else
                System.out.println("Sorry that is not a valid card. You lost your opportunity to drop a card.");
             
            // UNO =)
            if (hand2.size() == 1)
                System.out.println("Player Two says UNO!!!!");
        }
        }
         
        // Add a card and show the result.
        else {
            System.out.println("Sorry, you can't play on this card. A card has been drawn for you.");
            hand2.add((Card)finalDeck.get(finalDeck.size()-1));
            System.out.println(player1name+", here is your resutling hand:\n"+hand2);
        }
         
    }
}
public void initVariables(){
    wildColorFlagFirst=false;
    wildColorFlagSecond=false;
    player1name="";
    player2name="";
    cardSpecialFlag=false;
    firstTimePlay=true;
    deckMain=new DeckMain();
    finalDeck=deckMain.prepareDeck();
    hand1 = new ArrayList<Card>();
    hand2 = new ArrayList<Card>();
    discardPile=new ArrayList<Card>();
    specialCards=deckMain.prepareDeckSpecial();
    normalCards=deckMain.prepareDeckNormal();
}
 
public boolean checkCardSpecial(int value, List<Card> list){
    Card tmpCard=list.get(value);
    if(!(value>=0||value<=6)){
        System.out.println("Wrong input\n ");
        return false;
    }
    else if(specialCards.contains(tmpCard)){
         
    return true;    
    }
    return false;
}
 
 
public void specialCardProcessing(int value, List<Card> list){
    Card tmpCard=(Card)list.get(value);
        if(tmpCard.cardValue.equals(Card.CardValue.PICK_TWO)){
            if(turn==1){
            System.out.println("you have used PICK_TWO Card.\n. Two Cards have been drawn for "+player2name);
             
            hand2.add((Card)finalDeck.get(finalDeck.size()-1));
            hand2.add((Card)finalDeck.get(finalDeck.size()-1));
             
             
            }
            else{
                System.out.println("you have used PICK_TWO Card.\n. Two Cards have been drawn for "+player1name);
                hand1.add((Card)finalDeck.get(finalDeck.size()-1));
                hand1.add((Card)finalDeck.get(finalDeck.size()-1));
            }
            cardSpecialFlag=true;
            finalDeck.remove(finalDeck.size()-1);
            finalDeck.remove(finalDeck.size()-1);
            if(turn==1 &&(finalDeck.size() > 0 && hand1.size() > 0 && hand2.size() > 0)){
            playTurn(turn);
             
            }
            else if(turn==2 &&(finalDeck.size() > 0 && hand1.size() > 0 && hand2.size() > 0))
                playTurn(turn)  ;
            }
             
             
         
         
        if(tmpCard.cardValue.equals(Card.CardValue.SKIP)||tmpCard.cardValue.equals(Card.CardValue.REVERSE)){
            if(turn==1&&(finalDeck.size() > 0 && hand1.size() > 0 && hand2.size() > 0)){
                cardSpecialFlag=true;
                discardPile.add(tmpCard);
                hand1.remove(tmpCard);
                playTurn(turn);
            }
                else if(turn==2 &&(finalDeck.size() > 0 && hand1.size() > 0 && hand2.size() > 0)){
                    cardSpecialFlag=true;
                    discardPile.add(tmpCard);
                    hand2.remove(tmpCard);
                 
                    playTurn(turn);
        }
                 
            }
        if(tmpCard.cardValue.equals(Card.CardValue.WILD_FOUR)){
            cardSpecialFlag=true;
            if(turn==1 &&(finalDeck.size() > 0 && hand1.size() > 0 && hand2.size() > 0)){
                System.out.println(player1name+ " has used WILD FOUR , 4 cards have been drawn for ."+player2name);
             
            for(int i=0;i<4;i++){
                hand2.add((Card)finalDeck.get(finalDeck.size()-1));
                finalDeck.remove(finalDeck.size()-1);
            }
            playTurn(turn);
            }
        else if(turn==2 &&(finalDeck.size() > 0 && hand1.size() > 0 && hand2.size() > 0)){
                    System.out.println(player2name+" has used WILD FOUR , 4 cards have been drawn for "+player1name);
                     
                    for(int i=0;i<4;i++){
                        hand1.add((Card)finalDeck.get(finalDeck.size()-1));
                        finalDeck.remove(finalDeck.size()-1);
                    }
                    playTurn(turn);
                }
        }
        if(tmpCard.cardValue.equals(Card.CardValue.WILD)){
            cardSpecialFlag=true;
             
                Scanner stdin= new Scanner(System.in);  
                System.out.println(" WILD card has been used by You. Choose Color from the following <0-3>\n");
                for(Color color:Color.values()){
                    System.out.println(color.name());
                     
                }
                 
                int input=stdin.nextInt();
                if(input>=0 && input<=3){
                    switch(input){
                    case 0: System.out.println("you have selected Yellow color");
                             colorChosen=Color.YELLOW;
                             if(turn==1  &&(finalDeck.size() > 0 && hand1.size() > 0 && hand2.size() > 0)){
                                 wildColorFlagSecond=true;
                                 playTurn(2);
                             }
                             else{
                                 wildColorFlagFirst=true;
                                 playTurn(1);
                             }
                             break;
                    case 1: System.out.println("you have selected Blue color");
                              colorChosen=Color.BLUE;
                              if(turn==1 &&(finalDeck.size() > 0 && hand1.size() > 0 && hand2.size() > 0)){
                                  wildColorFlagSecond=true;
                                  playTurn(2);
                               }
                            else if(turn==2 &&(finalDeck.size() > 0 && hand1.size() > 0 && hand2.size() > 0)){
                                wildColorFlagFirst=true;
                                playTurn(1);
                                  }
                                 break;
                    case 2: System.out.println("you have selected Green color");
                              colorChosen=Color.GREEN;
                              if(turn==1 &&(finalDeck.size() > 0 && hand1.size() > 0 && hand2.size() > 0)){
                                  wildColorFlagSecond=true;
                                  playTurn(2);
                            break;
                              }
                             else{
                                 wildColorFlagFirst=true;
                             playTurn(1);
                            break;
                               }
                                
                    case 3: System.out.println("you have selected Red color");
                            colorChosen=Color.RED;;
                            if(turn==1 &&(finalDeck.size() > 0 && hand1.size() > 0 && hand2.size() > 0)){
                                wildColorFlagSecond=true;
                                playTurn(2);
                            break;
                             }
                            else{
                                wildColorFlagFirst=true;
                            playTurn(1);
                            break;
                     
                    }
                }
                     
                }
                 
        }
    }
    public static void main(String[] args) {
    UNOGame myGame = new UNOGame();
    myGame.playGame();
}
}

