package blackjack;
import java.util.*; 

/**
 * @author Sunay
 *
 */
public class BlackJackGame {
	
	ArrayList<Card> dealercards=new ArrayList<Card>(); // This will hold dealer cards.
	int dealvalue = 0; // This will have the deal value.
	boolean isGameOver=false; // Flag used to see if the game has over and to start a new Game
	
	// Display method is used to display the Dealer's One card and the User's both cards. Player will see dealer's one card and his two cards. 
	public void display(User user) {
		System.out.println("Dealer's Up card is: "+ this.dealercards.get(1).name);
		
		System.out.print("User's Up cards are: ");
		for(Card c: user.userCards) {
			System.out.print(c.name+"\t");
		}
		System.out.println();
	}
	
	// displayResult method is used to display all the cards of dealer and the Player.
	public void dislayResult(ArrayList<Card> dealercards,User user) {
		System.out.print("Dealer's cards: ");
		for(Card c: dealercards) {
			System.out.print(c.name+"\t");
		}
		System.out.print("\nYour cards: ");
		for(Card c: user.userCards) {
			System.out.print(c.name+"\t");
		}
		System.out.println("\nDealer's Value: "+BJ.getValue(dealercards) +"\tYour's total Value: "+BJ.getValue(user.userCards));
		System.out.println("Chips left in your account: "+user.totalchips);
		
	}
	
	//This method is used for the first step of the game. Gives two cards to the user and to the dealer. It also checks for the BlackJack   
	public void giveCards(User user) {
		System.out.println("Lets Play ");
		//Deletes the previous or existing cards if any
		dealercards.clear();
		dealercards.add(BJ.getCard()); // BJ.getCard() method gives the card from the Deck. More information at BJ.java file. 
		dealercards.add(BJ.getCard());
		
		//Deletes the previous or existing cards if any
		user.userCards.clear();
		user.userCards.add(BJ.getCard());
		user.userCards.add(BJ.getCard());

		this.checkBJWinStatus(user); // Checks for blackjack
			
	}
	
	// This method checks for blackjack and if it is true, sets isGameOver flag to true. Else it sets to false and display's the user's card and Dealer's upcard.
	public void checkBJWinStatus(User user){
		boolean dealerstatus=BJ.isBlackJack(dealercards);
		boolean userstatus=BJ.isBlackJack(user.userCards);
		if(dealerstatus && userstatus){ // Check if both has blackjack
			System.out.println("Both BlackJack! Equal ");
			user.totalchips+=this.dealvalue;
			this.isGameOver=true;
			dislayResult(dealercards,user);
		}
		else if(dealerstatus){ // Else check if dealer has blackjack
			System.out.println("Dealer have BlackJack! You lost ");
			this.isGameOver=true;
			dislayResult(dealercards,user);
		}
		else if(userstatus){ // Else check if user has blackjack
			System.out.println("You have the BlackJack! You Won ");
			user.totalchips+=2*this.dealvalue; // returns the money back to the user in the ratio 1:1. i.e. both user's money and dealer's money.
			this.isGameOver=true;
			dislayResult(dealercards,user);
		}
		else { // Else continue the game
			this.isGameOver=false;
			display(user);
		}
	}
	
// check the game status. Usually called when player takes Stand. 
	public void checkWinStatus(User user) {
		int dealervalue=BJ.getValue(this.dealercards);
		int uservalue=BJ.getValue(user.userCards);
		if(dealervalue > uservalue) {
			System.out.println("You Lost");
			isGameOver=true;
		}
		else if(dealervalue == uservalue) {
			System.out.println("Tied");
			user.totalchips+=this.dealvalue; // Return the money back to the player
			isGameOver=true;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("BLACK JACK GAME "); 
		User user = new User();  // user will now have 100 chips and game starts. 
		
		while(user.totalchips>0) {
			BlackJackGame game = new BlackJackGame();
			System.out.println("Press Start : 1\nExit: 0");
			Scanner reader = new Scanner(System.in);
			try {
				int input = reader.nextInt();
				if(input==1){
					System.out.println("You currently have " + user.totalchips+ " Enter the deal amount ");
					while(true){
						try {
							Scanner reader1 = new Scanner(System.in);
							game.dealvalue = reader1.nextInt();
							if(game.dealvalue>user.totalchips || game.dealvalue<=0) { // Check for input value in the range {0, totalchips}
								System.out.println("Invalid Amount! Please Enter the proper amount..");
								continue;
							}
							break;
						} catch(InputMismatchException e) { // if user enters character or other than integer, ask user to enter again
							System.out.println("Please enter a valid input");
						}
						
					}
					user.totalchips-=game.dealvalue; // take out deal value from the user's account
					UserAction uaction = new UserAction();
					game.giveCards(user); // Give cards to dealer and user and check if anyone has blackjack
					while(game.isGameOver==false) { // IF no one has black jack, game proceeds and user is asked to enter hit or stand.
						System.out.println("Enter Hit - 1		Stand - 2");
						try {
							Scanner reader2 = new Scanner(System.in);
							int actionvalue = reader2.nextInt();
							if(actionvalue==1) {
								if(uaction.hit(user)) { // If hit results in BUST, end the game and start new game
									game.dislayResult(game.dealercards, user);
									game.isGameOver=true;
									break;
								}
								else if(BJ.getValue(user.userCards)==21) { // If hit results in 21 
									if(BJ.getValue(game.dealercards)==21) { // check if dealer has blackjack. 
										System.out.println("Tied");
										user.totalchips+=game.dealvalue;
										game.isGameOver=true;
										break;
									}
									else {
										System.out.println("You Won"); // else user wins the game
										game.dislayResult(game.dealercards, user);
										user.totalchips+=2*game.dealvalue;
										game.isGameOver=true;
										break;
									}
								}
								else // If hit doesn't result in BUST, continue the game.
									game.display(user);
									continue;
							}
							else if(actionvalue==2) { // If user takes a stand, check who has won the game.
								game.checkWinStatus(user); // checks the status initially.
								if(game.isGameOver!=true) { // if dealer has less value than user's and also less than 17, dealer will take new cards. 
									uaction.stand(game.dealercards,user,game.dealvalue);
								}
								game.dislayResult(game.dealercards,user);
								game.isGameOver=true;
								break;
							}
							else { // if user enters invalid digits, ask the player to enter again
								System.out.println("Enter valid input");
							}
						} catch(InputMismatchException e) { // If user enter character or string or anything apart from numbers, ask him to enter again
							System.out.println("Please enter a valid input");
							
						}
					}
					System.out.println("New Game! ");
					
				}
				else if(input!=0) {
					System.out.println("Please enter valid input");
				}
				else { // If user quits, exit game
					System.out.println("Bye");
					System.exit(0);
				}
			} catch(InputMismatchException ex) { // Validates the user input for start/Exit game
				System.out.println("Please enter valid input");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Insuffecient chips to play"); // If user is bankrupt, exit the game
		
		
	}

}
