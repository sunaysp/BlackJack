package blackjack;

import java.util.ArrayList;

/**
 * @author Sunay
 *
 */
// User will now have the cardsList and the amount in his account. We can introduce name if there are multiple players.
public class User {
	
	public ArrayList<Card> userCards=new ArrayList<Card>();
	int totalchips = 100;
	
	public boolean checkBust(){
		if(BJ.getValue(this.userCards)>21) {
			System.out.println("YOU ARE BUSTED");
			return true;
		}
		return false;
	}
	
}
