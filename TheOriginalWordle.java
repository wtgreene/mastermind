import java.util.Scanner;
import java.util.Random;

public class TheOriginalWordle {

        public static final int RANDOM_GAME = -1;

        public static final int MIN_ROUNDS = 1;

        public static final int MAX_ROUNDS = 10;

        public static final int MAX_NAME_CHARACTERS = 50;

    public static void main(String[] args) {

        if (args.length > 1 || args.length < 0) {
            System.out.println("\nUsage : java cp bin TheOriginalWordle <seed>\n");
            System.exit(1);
        }
        
        int seed = Integer.parseInt(args[0]);

        displayWelcome();

        System.out.print("Player 1 name? ");

        Scanner console = new Scanner(System.in);
        String p1Name = console.nextLine();

        // Error checking
        while (p1Name.length() > MAX_NAME_CHARACTERS) {
            System.out.println("\nName too long - max 50 characters");
            System.out.print("Player 1 name? ");
            p1Name = console.nextLine();
        }
        
        System.out.print("Player 2 name? ");
        String p2Name = console.nextLine();

        // Error checking
        while (p2Name.length() > MAX_NAME_CHARACTERS) {
            System.out.println("\nName too long - max 50 characters");
            System.out.print("Player 2 name? ");
            p2Name = console.nextLine();
        }

        Player player1 = new Player(p1Name, seed);
        Player player2 = new Player(p2Name, seed * 2 + 1);

        System.out.print("\nNumber of rounds? ");

        // Error checking
        while (!console.hasNextInt()) {
            console.next();
            System.out.println("\nInvalid number of rounds. " +
                               "Please enter an integer between 1 and 10, inclusive.");
            System.out.print("Number of rounds? ");
        }

        int numRounds = console.nextInt();

        // Error checking
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

        int p1numBlackPegs = 0;
        int p1numWhitePegs = 0;
        int p2numBlackPegs = 0;
        int p2numWhitePegs = 0;
        boolean playAgain = true;
        
        while (playAgain) { // Indent below rows

            for (int i = 1; i <= numRounds; i++) {
    
                for (int j = 1; j <= Player.MAX_NUM_OF_GUESSES; j++) {
    
                    // DELETE BEFORE SUBMISSION - Shows passcodes
                    String p1Passcode = player1.getX();
                    System.out.println();
                    System.out.println("p1Passcode : " + p1Passcode);
    
                    System.out.println("\n[Round " + i + ", Guess " + j + "] " +
                                       "Total Points for " + player1.getName() + " : " +
                                       player1.getTotalPoints());
                    System.out.print(player1.getName() + ", please enter guess (R-ed, Y-ellow, B-lue, " +
                                       "G-reen, O-range, P-urple): ");
    
                    String p1Guess = console.next();
    
                    boolean guess = false;
    
                    //error handling
                    while (!guess) {
    
                        int[] count = new int[6];
                        for (int k = 0; k < p1Guess.length(); k++) {
                            for(int l = 0; l < GuessAnalysis.COLORS.length; l++) {
                                if (p1Guess.charAt(k) == GuessAnalysis.COLORS[l]) {
                                    count[l]++;
                                }
                            }
                        }
    
                        int total = 0;
    
                        for (int k = 0; k < count.length; k++) {
                            if (count[k] != 0) {
                                total++;
                            }
                        }
    
                        if (total != 4) {
                            guess = false;
                            System.out.println("\nNote : guess format must be 4 characters,");
                            System.out.println("       no spaces included and no duplicates e.g. BOYG");
                            System.out.println("\n[Round " + i + ", Guess " + j + "] " +
                                               "Total Points for " + player1.getName() + " : " +
                                               player1.getTotalPoints());
                            System.out.print(player1.getName() + ", please enter guess (R-ed, Y-ellow, B-lue, " +
                                               "G-reen, O-range, P-urple): ");
                            p1Guess = console.next();
                        } else {
                            guess = true;
                        }
                    }
    
                    player1.addGuess(); // Don't need
                    player1.updateBoard(p1Guess, j);
                    System.out.println();
                    
                    // Display p1 board
                    System.out.println(player1.getBoard());
                    
    
                    if (player1.getCompareCode(p1Guess)) {
                        System.out.println("Congratulations, " + player1.getName() + "! You have guessed the code!");
                        player1.addPoints();
                        break;
                    }
    
                    else {
                        p1numBlackPegs = player1.getNumCorrectColorAndSlot(p1Guess);
                        p1numWhitePegs = player1.getNumCorrectColor(p1Guess);
                        player1.addPoints();
                        if (j == Player.MAX_NUM_OF_GUESSES) {
                            player1.addPoints();
                            player1.addPoints();
                            System.out.println();
                            System.out.print  (player1.getName() + ", you did not guess ");
                            System.out.println("the passcode in 8 guesses.");
                        }
                    }

                }

                System.out.println("\n" + player2.getName() + ", it is now your turn to play.");
    
                for (int j = 1; j <= Player.MAX_NUM_OF_GUESSES; j++) {
    
                    // DELETE BEFORE SUBMISSION - Shows passcodes
                    String p2Passcode = player2.getX();
                    System.out.println();
                    System.out.println("p2Passcode : " + p2Passcode);
    
    
                    System.out.println("\n[Round " + i + ", Guess " + j + "] " +
                                       "Total Points for " + player2.getName() + " : " +
                                       player2.getTotalPoints());
                    System.out.print(p1Name + ", please enter guess (R-ed, Y-ellow, B-lue, " +
                                       "G-reen, O-range, P-urple): ");
    
                    String p2Guess = console.next();
    
                    boolean guess = false;
    
                    //error handling
                    while (!guess) {
    
                        int[] count = new int[6];
                        for (int k = 0; k < p2Guess.length(); k++) {
                            for(int l = 0; l < GuessAnalysis.COLORS.length; l++) {
                                if (p2Guess.charAt(k) == GuessAnalysis.COLORS[l]) {
                                    count[l]++;
                                }
                            }
                        }
    
                        int total = 0;
    
                        for (int k = 0; k < count.length; k++) {
                            if (count[k] != 0) {
                                total++;
                            }
                        }
    
                        if (total != 4) {
                            guess = false;
                            System.out.println("\nNote : guess format must be 4 characters,");
                            System.out.println("       no spaces included and no duplicates e.g. BOYG");
                            System.out.println("\n[Round " + i + ", Guess " + j + "] " +
                                               "Total Points for " + player2.getName() + " : " +
                                               player2.getTotalPoints());
                            System.out.print(player2.getName() + ", please enter guess (R-ed, Y-ellow, B-lue, " +
                                               "G-reen, O-range, P-urple): ");
                            p2Guess = console.next();
                        } else {
                            guess = true;
                        }
                    }
                    
                    player2.addGuess();
                    player2.updateBoard(p2Guess, j);
                    System.out.println();
                    // Display p2 board
                    System.out.println(player2.getBoard());
    
                    // Error handling
    
                    if (player2.getCompareCode(p2Guess)) {
                        System.out.println("Congratulations, " + player2.getName() + "! You have guessed the code!");
                        player2.addPoints();
                        break;
                    }
    
                    else {
                        p2numBlackPegs = player2.getNumCorrectColorAndSlot(p2Guess);
                        p2numWhitePegs = player2.getNumCorrectColor(p2Guess);
                        player2.addPoints();
                        if (j == Player.MAX_NUM_OF_GUESSES) {
                            player2.addPoints();
                            player2.addPoints();
                            System.out.println();
                            System.out.print  (player2.getName() + ", you did not guess ");
                            System.out.println("the passcode in 8 guesses.");                        }
                    }
                }

                System.out.println("\nTotals");
                System.out.println(player1.getName() + " : " + player1.getTotalPoints() + " points");
                System.out.println(player2.getName() + " : " + player2.getTotalPoints() + " points");
    
                if (i != numRounds) {
                    System.out.println("\nNew codes have been initialized for both players.");
                    System.out.println("Let's begin [Round " + (i + 1) + "]");
                }
                
                // Creates new passcodes and resets numGuess for both players
                player1.newRound();
                player2.newRound();
            }
            
            String response = "";

            if (player1.getTotalPoints() < player2.getTotalPoints()) {
                System.out.println("\nCongratulations, " + player1.getName() + "! You are the winner!");
                System.out.print("\nPlay again (Y-es, Q-uit)? ");
                response = console.next();
            } else if (player2.getTotalPoints() < player1.getTotalPoints()) {
                System.out.println("\nCongratulations, " + player2.getName() + "! You are the winner!");
                System.out.print("\nPlay again (Y-es, Q-uit)? ");
                response = console.next();
            } else {
                System.out.println("\nOh no! It's a tie! Try playing again to settle the tiebreak.");
                System.out.print("\nPlay again (Y-es, Q-uit)? ");
                response = console.next();
            }
            
            if (response.charAt(0) == 'Y' || response.charAt(0) == 'y') {
                playAgain = true;
                player1.resetPoints();
                player2.resetPoints();
            }
            
            else {
                playAgain = false;
                System.out.println();
            }
        }
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