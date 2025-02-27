//Card class
//handles the construction of a card
public class Card {
    private String suit;
    private String rank;
    private int value;

    //constructor
    public Card() {

    }

    //builds a card
    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    //gets suit
    public String getSuit() {
        return suit;
    }

    //gets rank
    public String getRank() {
        return rank;
    }

    //gets value
    public int getValue() {
        return value;
    }

    //displays the card
    public void showCard() {
        System.out.println("The " + rank + " of " + suit + "(Value: " + value + ")");
    }

    @Override
    public String toString() {
        return rank + " of " + suit + " (Value: " + value + ")";
    }

    //I was going to add an ascii print of the card
    //could develop in the future
    public void displayCard() {

    }
}
