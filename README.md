BlackJack
=========
BlackJack is the game written in Java. More information about blackjack game is found in 
http://en.wikipedia.org/wiki/Blackjack

FILES
=====
All the files are in blackjack package. 
1. src/blackjack/BlackJackGame.java
2. src/blackjack/BJ.java
3. src/blackjack/Deck.java
4. src/blackjack/User.java
5. src/blackjack/UserAction.java
6. src/blackjack/UserActionInterface.java

How to run
===========

Import the project into eclipse and run BlackJackGame.java file. 


Assumptions
===========

1. My code assumes one dealer and one player. However, we can extend it to more than one player with a very few changes. 
2. The dealer will take hits until his or her cards total is 17 or more points than the player. 
3. User's total chips is 100 initially. 
4. All the win payments are in the ratio 1:1 and nothing is exchanged if match is tied. However, i have written code to 
   replace for 2:1 for the BlackJack case in comments in checkBJWinStatus(User user) method in BlackJackGame.java file.
5. Deck will have a pack of 52 cards and cards are randomly taken from this Deck. Once the deck is empty, all the cards 
   taken out from deck are put it back to it. However, we can initialize the deck with more than one pack of cards by 
   calling another constructor which is shown in comments section in BJ.java file. 
6. User can do only hit and stand in this game. All other actions are not implemented in this code. 
