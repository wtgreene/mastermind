import java.util.Scanner;
import java.util.Random;

public class TheOriginalWordle {

        public static final int RANDOM_GAME = -1;
        
        public static final int MIN_ROUNDS = 1;
        
        public static final int MAX_ROUNDS = 10;
        
        public static final int MAX_NAME_CHARACTERS = 50;
        
    public static void main(String args[]) {
    
        if (args.length > 1 || args.length < 0) {
            System.out.println("\nUsage : java cp bin TheOriginalWordle <seed>\n");
            System.exit(1);
        }
        
        displayWelcome();
        
        System.out.print("Player 1 name? ");
        
        Scanner console = new Scanner(System.in);
        String p1Name = console.nextLine();
        
        // Error checking - player 1 name
        
        while (p1Name.length() > MAX_NAME_CHARACTERS) {
            System.out.println("\nName too long - max 50 characters");
            System.out.print("Player 1 name? ");
            p1Name = console.nextLine();
        }
        
        System.out.print("Player 2 name? ");
        String p2Name = console.nextLine();
        
        // Error checking - player 2 name
        
        while (p2Name.length() > MAX_NAME_CHARACTERS) {
            System.out.println("\nName too long - max 50 characters");
            System.out.print("Player 2 name? ");
            p2Name = console.nextLine();
        }
        
        System.out.print("\nNumber of rounds? ");
        
        // Error checking - number of rounds

        while (!console.hasNextInt()) {
            console.next();
            System.out.println("\nInvalid number of rounds. " +
                               "Please enter an integer between 1 and 10, inclusive.");
            System.out.print("Number of rounds? ");
        }
        
        int numRounds = console.nextInt();
                
        while (numRounds < MIN_ROUNDS || numRounds > MAX_ROUNDS) {
            System.out.println("\nInvalid number of rounds. " +
                               "Please enter an integer between 1 and 10, inclusive.");
            System.out.print("Number of rounds? ");
            
            while (!console.hasNextInt()) {
                console.next();
                System.out.println("\nInvalid number of rounds. " +
                               "Please enter an integer between 1 and 10, inclusive.");
                System.out.print("Number of rounds? ");
            }
            
            numRounds = console.nextInt();
        }
        
        System.out.println("\nGreat! codes have been initialized for both players. " +
                           "Lets begin.");
                           
        System.out.println("\nNote : guess format must be 4 characters,");
        System.out.println("       no spaces included and no duplicates e.g. BOYG");
        
        int round = 1;
        int p1Points = 0;
        int p2Points = 0;
        int p1numBlackPegs = 0;
        int p1numWhitePegs = 0;
        int p2numBlackPegs = 0;
        int p2numWhitePegs = 0;
        
        for (int i = 1; i <= numRounds; i++) {
        
            for (int j = 1; j <= Player.MAX_NUM_OF_GUESSES; j++) {
            
                GuessAnalysis p1 = new GuessAnalysis(Integer.parseInt(args[0])        );
                GuessAnalysis p2 = new GuessAnalysis(Integer.parseInt(args[0]) * 2 + 1);
                
                // DELETE BEFORE SUBMISSION - Shows passcodes
                String p1Passcode = p1.x();
                String p2Passcode = p2.x();
                System.out.println();
                System.out.println("p1Passcode : " + p1Passcode);
                System.out.println("p2Passcode : " + p2Passcode);
                
                if (j != 1) {
                    // Display p1 board
                }
                
                System.out.println("\n[Round " + round + ", Guess " + j + "] " +
                                   "Total Points for " + p1Name + " : " + p1Points);
                System.out.print(p1Name + ", please enter guess (R-ed, Y-ellow, B-lue, " +
                                   "G-reen, O-range, P-urple): ");
                
                String p1Guess = console.next();
                
                // Error handling
                
                if (p1.compareCode(p1Guess)) {
                    // Correct guess!
                }
                
                else {
                    p1numBlackPegs = p1.numCorrectColorAndSlot(p1Guess);
                    p1numWhitePegs = p1.numCorrectColor(p1Guess);
                }
                
                if (j != 1) {
                    // Display p2 board
                }
                
                System.out.println("\n[Round " + round + ", Guess " + j + "] " +
                                   "Total Points for " + p2Name + " : " + p2Points);
                System.out.print(p1Name + ", please enter guess (R-ed, Y-ellow, B-lue, " +
                                   "G-reen, O-range, P-urple): ");
                
                String p2Guess = console.next();
                
                // Error handling
                
                if (p2.compareCode(p2Guess)) {
                    // Correct guess!
                }
                
                else {
                    p2numBlackPegs = p2.numCorrectColorAndSlot(p2Guess);
                    p2numWhitePegs = p2.numCorrectColor(p2Guess);
                }
                
                // ERASE : Original purpose to test numBlackPegs & numWhitePegs
                System.out.println("\n" + p1numBlackPegs + " & " + p1numWhitePegs);
                System.out.println(       p2numBlackPegs + " & " + p2numWhitePegs);
            }
            
            System.out.println("\nTotals");
            System.out.println(p1Name + " : " + p1Points + " points");
            System.out.println(p2Name + " : " + p2Points + " points");
            round++;
            
            if (round != numRounds + 1) {
                System.out.println("\nNew codes have been initialized for both players.");
                System.out.println("Let's begin [Round " + round + "]");
            }
        }
        
        if (p1Points > p2Points) {
            System.out.println("\nCongratulations, " + p1Name + "! You are the winner!");
            System.out.print("\nPlay again (Y-es, Q-uit)? ");
            String response = console.next();
        } else if (p2Points > p1Points) {
            System.out.println("\nCongratulations, " + p2Name + "! You are the winner!");
            System.out.print("\nPlay again (Y-es, Q-uit)? ");
            String response = console.next();
        } else {
            System.out.println("\nOh no! It's a tie! Try playing again to settle the tiebreak.");
            System.out.print("\nPlay again (Y-es, Q-uit)? ");
            String response = console.next();
        }
        
        // Error handling
    }
    
    
    /** 
     * This method will print the display message.  
     * This method will also print the directions on how the game is played 
     * and the rules of the game.
     * 
     */
    public static void displayWelcome() {
        
        System.out.println("\n   Welcome to The Original Wordle!");
        System.out.println("A game of logic, with a bit of chance.");
        
        System.out.println("\n             HOW TO PLAY");
        
        System.out.print("\nThe Original Wordle is designed to mimic the game Mastermind. ");
        System.out.print("The goal of the game is to guess a randomized, hidden sequence of ");
        System.out.print("four colors in the correct order while gaining the least amount of ");
        System.out.print("points. This is a two player game in which player’s take turns trying");
        System.out.print("to guess a randomized sequence of colors in the least amount of ");
        System.out.print("guesses possible. The maximum number of guesses per sequence is 8 ");
        System.out.print("guesses. A player gains one point for each guess and receives two ");
        System.out.print("extra points if the sequence is not guessed within the maximum number");
        System.out.print("of guesses allowed. Feedback regarding the guess success is provided");
        System.out.print("back (in the form of white and black key pegs) to the player after ");
        System.out.print("each guess. White key pegs will represent a color that has been ");
        System.out.print("guessed correctly but placed in the wrong position of the sequence. ");
        System.out.print("Black key pegs will be displayed for each color guessed correctly and ");
        System.out.print("in the correct position of the sequence.  When a player receives four ");
        System.out.print("black key pegs as feedback the player has guessed the hidden sequence ");
        System.out.print("of four colors correctly. The game is won by the player who has the ");
        System.out.print("least amount of points at the end of the last round.  Please note ");
        System.out.print("that the number of rounds is determined by the players at the start ");
        System.out.println("of the game.");
        
        System.out.println("\nThis game is for 2 players.");
    }
}