import java.util.Scanner;
import java.util.Random;

public class TheOriginalWordle {

        public static final int RANDOM_GAME = -1;
        
        public static final int CODE_LENGTH = 4;
        
        public static final int NUM_OF_COLORS = 6;
        
        public static final int NUM_OF_GUESSES_PER_ROUND = 8;
        
        public static final int MAX_ROUNDS = 10;
        
    public static void main(String args[]) {
    
        if (args.length > 1 || args.length < 0) {
            System.out.println("\nUsage : java cp bin TheOriginalWordle <seed>\n");
            System.exit(1);
        }
        
        System.out.println("\n   Welcome to The Original Wordle!");
        System.out.println("A game of logic, with a bit of chance.\n");
        
        System.out.println("This game is for 2 players.");
        System.out.print("Player 1 name? ");
        
        Scanner console = new Scanner(System.in);
        String p1Name = console.nextLine();
        
        // Error checking - player 1 name
        
        while (p1Name.length() > 50) {
            System.out.println("\nName too long - max 50 characters");
            System.out.print("Player 1 name? ");
            p1Name = console.nextLine();
        }
        
        System.out.print("Player 2 name? ");
        String p2Name = console.nextLine();
        
        // Error checking - player 2 name
        
        while (p2Name.length() > 50) {
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
                
        while (numRounds < 1 || numRounds > MAX_ROUNDS) {
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
        
        int round = 1;
        int p1Points = 0;
        int p2Points = 0;
        
        System.out.println("\nGreat! 4-color codes have been initialized for both players. " +
                           "Lets begin.");
                           
        System.out.println("\nNote : guess format must be 4 characters, " +
                           "no spaces included e.g. YWGG");
        
        for (int i = 0; i < numRounds; i++) { // "For each round ..."
            
            char[] secretCodeList = new char[CODE_LENGTH];
            
            for (int j = 0; j < CODE_LENGTH; j++) { // Should we make this a class?
                
                Random rand = new Random();
                int color = rand.nextInt(NUM_OF_COLORS);
                
                switch (color) {
                    case 0:
                        secretCodeList[j] = 'R'; // Red
                        break;
                    case 1:
                        secretCodeList[j] = 'G'; // Greene
                        break;
                    case 2:
                        secretCodeList[j] = 'B'; // Blue
                        break;
                    case 3:
                        secretCodeList[j] = 'Y'; // Yellow
                        break;
                    case 4:
                        secretCodeList[j] = 'K'; // Black
                        break;
                    case 5:
                        secretCodeList[j] = 'W'; // White
                        break;
                    default:
                        break;
                }
            }
            
            String secretCode = "";
            for (int l = 0; l < CODE_LENGTH; l++) {
                secretCode += secretCodeList[l];
            }
            
            int numGuesses = 1;
            
            for (int k = 0; k < NUM_OF_GUESSES_PER_ROUND; k++) {
            
                System.out.println("\n[Round " + round + ", Guess " + numGuesses + "] " +
                                   "Total Points for " + p1Name + " : " + p1Points);
                System.out.print(p1Name + ", please enter guess (R-ed, Y-ellow, B-lue, " +
                                   "G-reen, Blac-K, W-hite): ");
                
                String p1Guess = console.next();
                
                // Error handling
                
                GuessAnalysis p1 = new GuessAnalysis(p1Guess, secretCode);
                
                int p1numBlackPegs = 0;
                int p1numWhitePegs = 0;
                
                if (!p1.isCorrectCode()) {
                    p1Points++;
                    p1numBlackPegs = p1.numCorrectColorAndSlot();
                    p1numWhitePegs = p1.numCorrectColor();
                }
                
                else {
                    // Correct guess!
                }
                
                System.out.println("\n[Round " + round + ", Guess " + numGuesses + "] " +
                                   "Total Points for " + p2Name + " : " + p2Points);
                System.out.print(p2Name + ", please enter guess (R-ed, Y-ellow, B-lue, " +
                                   "G-reen, Blac-K, W-hite): ");
                                   
                String p2Guess = console.next();
                                   
                // Error handling
                
                GuessAnalysis p2 = new GuessAnalysis(p2Guess, secretCode);
                
                int p2numBlackPegs = 0;
                int p2numWhitePegs = 0;
                
                if (!p2.isCorrectCode()) {
                    p2Points++;
                    p2numBlackPegs = p2.numCorrectColorAndSlot();
                    p2numWhitePegs = p2.numCorrectColor();
                }
                
                else {
                    // Correct guess!
                }
                
                // ERASE : Original purpose to test numBlackPegs & numWhitePegs
                System.out.println("\n" + p1numBlackPegs + " & " + p1numWhitePegs);
                System.out.println(       p2numBlackPegs + " & " + p2numWhitePegs);
                
                // Display scoreboard (call scoreboard class?)
                
                numGuesses++;
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
}