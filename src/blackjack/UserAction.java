package blackjack;

import java.util.ArrayList;

/**
 * @author Sunay
 *
 */
// This class will have all the user actions. It implements the user action interface. User can either hit or stand in the game 
public class UserAction implements UserActionInterface {
	// Get the card from the deck and check if user is BUSTED. This method returns True if user is busted, else returns false
	public boolean hit(User user) {
		user.userCards.add(BJ.getCard());
		return user.checkBust();
	}
		
	// If user takes a stand, check both the value and if dealer's value is less than 17 and less than user's value, dealer will hit 
	// until his value is case 1. equal to or more than user's value 
	// case 2. equal to or more than 17 
	public void stand(ArrayList<Card> dealerCards, User user, int dealValue) {
		int value = BJ.getValue(dealerCards);
		int userValue=BJ.getValue(user.userCards);
		boolean status=false;
		while(value<17) {
			dealerCards.add(BJ.getCard());
			value = BJ.getValue(dealerCards);
			if(value>21) {
				System.out.println("Dealer is Busted! YOU WON ");
				user.totalchips+=2*dealValue;
				status=true;
				break;
			}
			else if(value>userValue) {
				System.out.println("You Lost");
				status=true;
				break;
			}
			else if(value==userValue) {
				System.out.println("Tied");
				user.totalchips+=dealValue;
				status=true;
				break;
			}
		}
		if(status==false) {
			if(value<userValue){
				System.out.println("You Won");
				user.totalchips+=2*dealValue;
			}
		}
	}
	
}
