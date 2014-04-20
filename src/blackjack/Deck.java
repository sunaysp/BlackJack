package blackjack;

import java.util.ArrayList;
import java.util.List;

// Card will have the name and value. 
class Card {
	String value;
	String name;
	Card(String v, String n) {
		this.value=v;
		this.name=n;
	}
}
/**
 * @author Sunay
 *
 */
// Deck is a pack of 52 cards. Deck is the place from where the game gets the card. 
// There are two constructors. One will initialize the deck into a pack of 52 cards. Another is used to initialize more than 1 set of 52 cards.  
public class Deck {
	public List<Card> deckCards = new ArrayList<Card>();
	String[] values= {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	String[] cardTypes = {"Spades","Clubs","Heart","Diamond"};
	
	Deck() {
		for(String s: cardTypes) {
			for(String v: values) {
				if(v.equals("J")||v.equals("Q")||v.equals("K")) {
					Card c = new Card("10",s.concat(" "+v));
					deckCards.add(c);
				}
				else {
					Card c = new Card(v,s.concat(" "+v));
					deckCards.add(c);
				}
			}
		}
		
	}
	Deck(int numberOfDecks) {
		for(int i=0;i<numberOfDecks;i++) {
			for(String s: cardTypes) {
				for(String v: values) {
					if(v.equals("J")||v.equals("Q")||v.equals("K")) {
						Card c = new Card("10",s.concat(" "+v));
						deckCards.add(c);
					}
					else {
						Card c = new Card(v,s.concat(" "+v));
						deckCards.add(c);
					}
				}
			}
		}
		
	}
}
	