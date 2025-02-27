//Card Game (black jack)
import java.util.Scanner;
public class Test {
    public static void main(String[] args) throws Exception {
        //creates objects
        Scanner sc = new Scanner(System.in);
        Dealer dealer = new Dealer();

        //variables used
        String wrong = "\nInvalid answer. Please try again.";
        int gamesTotal = 0;
        int gamesWon = 0;

        //introduces the game while also giving the rules
        dealer.intro();

        //game loop
        boolean playing = true;
        while (playing) {
            //plays one round of the game
            boolean playerWon = dealer.playGame();

            //updates and displays the score
            gamesTotal++;
            if (playerWon) {
                gamesWon++;
            }
            System.out.println("\nYou've won " + gamesWon + " out of " + gamesTotal + " games.");

            

            //loops until a valid answer is given
            while (true) {
                //asks the player if they want to play again
                System.out.print("\nType a number to continue\n[1] Play again\n[2] Rules\n[3] Current score\n[4] Quit\n>");
                String answer = sc.nextLine();
                if (answer.equals("1")) {
                    break;
                } else if(answer.equals("2")) {
                    dealer.rules();
                } else if(answer.equals("3")) {
                    System.out.println("\nYou've won " + gamesWon + " out of " + gamesTotal + " games.");
                } else if (answer.equals("4")) {
                    System.out.println("\nGoodbye");
                    playing = false;
                    break;
                } else {
                    System.out.println(wrong);
                }
            }
        }

        //closes the scanner
        sc.close();
    }
}
