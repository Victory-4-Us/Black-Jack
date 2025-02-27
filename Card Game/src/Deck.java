//Deck class
//handles the construction and shuffling of the deck
//uses the Card class
import java.util.Random;
public class Deck {
    Card[] cards = new Card[52];
    private Random randy = new Random();
    int cardsDealt = 0;

    //constructor that builds a deck of cards
    public Deck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        int count = 0;

        //builds a deck in an unshuffled order
        for(String suit : suits) {
            for(int i = 0; i < ranks.length; i++) {
                cards[count++] = new Card(suit, ranks[i], values[i]);
            }
        }
    }

    //returns a copy of the deck
    public Card[] getDeck() {
        return cards.clone();
    }

    //shuffles the deck using the Fisher-Yates Shuffle
    public void shuffle() {
        for (int i = cards.length - 1; i > 0; i--) {
            int j = randy.nextInt(i + 1);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }
}