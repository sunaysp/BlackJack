package blackjack;

import java.util.ArrayList;

/**
 * @author Sunay
 *
 */
// User action interface
public interface UserActionInterface {
	public boolean hit(User user);
	public void stand(ArrayList<Card> dealerCards, User user, int dealValue);

}
