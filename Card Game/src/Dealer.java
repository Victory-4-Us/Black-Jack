//dealer class
//handles the game logic through one round of play
//uses the objects of the Card and Deck classes

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Dealer {

    //builds objects
    private Deck newdeck = new Deck();
    private Scanner sc = new Scanner(System.in);

    //builds a deck as well as the player and dealer hands
    private Card[] deck = newdeck.getDeck();
    private List<Card> playerHand = new ArrayList<>();
    private List<Card> dealerHand = new ArrayList<>();

    //variables used
    private int cardsDealt = 0;
    private String answer;
    
    //shuffles using a Deck object and updates the deck
    private void shuffleCards() {
        newdeck = new Deck();
        newdeck.shuffle();
        deck = newdeck.getDeck();
        
        cardsDealt = 0;
    }

    //deals cards at the beginning of a game
    private Card dealCards() {
        if(cardsDealt < deck.length) {
            return deck[cardsDealt++];
        } else {
            return null;
        }
    }

    //adds a card to either the player or the dealer's hand
    private Card drawCards(List<Card> hand) {
        if (cardsDealt < deck.length) {
            Card drawnCard = dealCards();
            if(hand == playerHand) {
                System.out.println("\nYou drew the " + drawnCard);
            } else if(hand == dealerHand) {
                System.out.println("\nDealer drew the " + drawnCard);
            }

            return drawnCard;
        } else {
            System.out.println("\nNo more cards left in the deck.");
            return null;
        }
    }  

    //introduces the game and gives the rules
    public void intro() {
        System.out.println("\n++++++++++++++++++++++");
        System.out.println("======================");
        System.out.println("Welcome to Black Jack!");
        System.out.println("======================");
        System.out.println("++++++++++++++++++++++");
        rules();
    }

    //displays the rules
    public void rules() {
        System.out.println("\nGame Rules:");
        System.out.println("* Get as close as you can to 21 without going over (busting)");
        System.out.println("* Going over 21 will result in an immediate game over");
        System.out.println("* \"Hitting\" means you want another card");
        System.out.println("* \"standing\" means you're happy with your hand and want no more cards");
        System.out.println("* Aces are worth either 1 or 11 and all face cards are worth 10");
        System.out.println("* After you stand, the dealer will try to beat your hand");
        System.out.println("* If the dealer gets closer to 21 than you or you bust, you lose");
        System.out.println("* If you are closer to 21 or the dealer busts, you win");
        System.out.println("* Best of luck");
    }

    //testing method
    //prints the deck in whole
    private void printDeck() {
        System.out.println("DECK PRINTED");
        for(Card card : deck) {
            System.out.println(card);
        }
    }

    //prints the hand of either the player or the dealer
    private void printHand(String name, List<Card> hand) {
        System.out.println("\n" + name + "'s hand:");
        for(Card card : hand) {
            System.out.println(card);
        }
    }

    //calculates the total value of a hand
    private int handTotal(List<Card> hand) {
        int total = 0;
        int aceCount = 0;
    
        //determines if the ace is used as a 1 or 11
        for (Card handCard : hand) {
            if (handCard.getRank().equals("Ace")) {
                aceCount++;
                total += 11;
            } else {
                total += handCard.getValue();
            }
        }
    
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }
    
        return total;
    }

    //plays one round of the game and uses class methods to do so
    public boolean playGame() {

        //clears out the hands for a fresh game.
        playerHand.clear();
        dealerHand.clear();

        shuffleCards();

        boolean playing = true;
        boolean what = true;

        startHand(playerHand, "Player");

        while(playing) {
            what = true;

            while(what) {
                System.out.print("\nWould you like to hit or stand? hit/stand\n>");
                answer = sc.nextLine();

                if(answer.equalsIgnoreCase("hit")) {
                    what = false;
                    playerHand.add(drawCards(playerHand));
                    printHand("Player", playerHand);
                    System.out.println("\nYour hand equals: " + handTotal(playerHand));
                } else if(answer.equalsIgnoreCase("stand")) {
                    what = false;
                    playing = false;
                } else {
                    System.out.println("\nInvalid answer. Please try again.");
                }
            }
            if (handTotal(playerHand) > 21) {
                what = false;
                playing = false;
            }
        }


        if(handTotal(playerHand) > 21) {
            System.out.println("\nYou Lost.");
            return false;
        } else {
            System.out.println("\n**My Turn**");
            return myTurn();
        }
    }

    //dealer's turn
    public boolean myTurn() {
        dealerHand.clear();
        
        startHand(dealerHand, "Dealer");

        //the dealer hits until they have 17 or higher
        //future intelligence can be added to make the dealer smarter
        while (handTotal(dealerHand) < handTotal(playerHand)) {
            System.out.println("\nDealer hits...");
            dealerHand.add(drawCards(dealerHand));
            printHand("Dealer", dealerHand);
            System.out.println("\nDealer's hand total: " + handTotal(dealerHand));
        }

        if(handTotal(dealerHand) >= 17 && handTotal(dealerHand) <= 21) {
            System.out.println("\nDealer Stands");
        }

        int dealerTotal = handTotal(dealerHand);
        int playerTotal = handTotal(playerHand);
    
        // Determine winner
        if (dealerTotal > 21) {
            System.out.println("\nDealer busts! You win!");
            return true;
        } else if (dealerTotal > playerTotal) {
            System.out.println("\nDealer wins!");
            return false;
        } else if (dealerTotal < playerTotal) {
            System.out.println("\nYou win!");
            return true;
        } else {
            System.out.println("\nIt's a tie!");
            return false;
        }
    }    

    //starts the hand with 2 cards
    public void startHand(List<Card> hand, String name) {
        for (int i = 0; i < 2; i++) {
            hand.add(dealCards());
        }

        printHand(name, hand);
        System.out.println("\n" + name + "'s hand total: " + handTotal(hand));
    }
}