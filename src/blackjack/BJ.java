package blackjack;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Sunay
 *
 */
public class BJ {
	public static int TWENTYONE=21; 
	public static Deck deck = new Deck(); // Initialise deck. 
	/*
		public static Deck deck = new Deck(2); // Use this if we want to use to 2 decks(pack of 52 cards) in the game.
	 */
	public static Card getCard() {
		if(deck.deckCards.size()==0) { // If deckCards are empty, all cards are pulled out. Initialize a new Set of cards.
			BJ.deck = new Deck();
			/*
			public static Deck deck = new Deck(2); // Use this if we want to use to 2 decks(pack of 52 cards) in the game.
		 */
		}
		Random randomGenerator = new Random();
		int card = randomGenerator.nextInt(deck.deckCards.size()-1); // Get the card randomly from the deckCards and remove from it
		Card c = deck.deckCards.get(card);
		deck.deckCards.remove(c);
		return c; // return the card
	}
	
	// This method will give the total value of the list of cards.  
	public static int getValue(ArrayList<Card> cardList) {
		int sum_one = 0;
		int sum_eleven=0;
		boolean flag=false;
		// If A exists in the list, consider all the possible options and return the best score. 
		// Case: A : 1 or 11 // Returns 11
		// Case: A A: 11, 1 or 1, 1 // Returns 12
		// Case: K 9 A A: 41 or 31 or 21 // Returns 21 
		for(int i=0; i<cardList.size(); i++){ 
			if(cardList.get(i).value.equals("A") && flag==false) {
				sum_one=sum_one+1;
				sum_eleven=sum_eleven+11;
				flag=true;
			}
			else {
				if(cardList.get(i).value.equals("A")) {
					sum_one=sum_one+1;
					sum_eleven=sum_eleven+1;
				}
				else {
					sum_one += Integer.valueOf(cardList.get(i).value);
					sum_eleven += Integer.valueOf(cardList.get(i).value);;
				}
			
			}
		}
		if(sum_eleven<=21 && sum_eleven>sum_one) {
			return sum_eleven;
		}
		return sum_one;
	}
	
	// This method returns true if cardList has the value of 21, else returns false
	public static boolean isBlackJack(ArrayList<Card> cardList) {
		if(BJ.getValue(cardList)==TWENTYONE){
			return true;
		}
		return false;
		
	}
		
}
