import java.util.Random;

/**
 * GuessAnalysis Class for Team 2 CE - The Orginal Wordle
 *
 * @author Lyndsay Barnes
 * @author Casey Beise
 * @author Will Greene
 */
public class GuessAnalysis {

    /** Length of hidden passcode and length of player's guess code*/
    public static final int CODE_LENGTH = 4;

    /** Total number of color options for random hidden passcode.*/
    public static final int NUM_OF_COLORS = 6;

    /** Char representing the black feedback peg indicating guessed color was the correct color and position.*/
    public static final char BLACK_PEG = 'b';

    /** Char representing the white feedback peg indicating guessed color was the correct color.*/
    public static final char WHITE_PEG = 'w';

    /** Char representing the color red.*/
    public static final char RED = 'R';

    /** Char representing the color green.*/
    public static final char GREEN = 'G';

    /** Char representing the color blue.*/
    public static final char BLUE = 'B';

    /** Char representing the color yellow.*/
    public static final char YELLOW = 'Y';

    /** Char representing the color orange.*/
    public static final char ORANGE = 'O';

    /** Char representing the color purple.*/
    public static final char PURPLE = 'P';

    /** Array of color options.*/
    public static final char[] COLORS = {'R', 'G', 'B', 'Y', 'O', 'P'};

    /** Text string that each player must guess */
    private String passcode;
        

    /**
     * PassCode constructor - creates new secret code
     * @param seed -  number seed for passcode generation for testing
     */
    public GuessAnalysis(int seed) {

        Random rand;

        if (seed != -1) {
            rand = new Random(seed);
        } else {
            rand = new Random();
        }

        char[] secretCodeList = new char[CODE_LENGTH];

        for (int i = 0; i < CODE_LENGTH; i++) {

            // See repeat logic in the for loop below the switch statement
            boolean repeat = true;
            while (repeat) {
                repeat = false;

                int color = rand.nextInt(NUM_OF_COLORS);

                switch (color) {
                    case 0:
                        secretCodeList[i] = RED;
                        break;
                    case 1:
                        secretCodeList[i] = GREEN;
                        break;
                    case 2:
                        secretCodeList[i] = BLUE;
                        break;
                    case 3:
                        secretCodeList[i] = YELLOW;
                        break;
                    case 4:
                        secretCodeList[i] = ORANGE;
                        break;
                    case 5:
                        secretCodeList[i] = PURPLE;
                        break;
                    default:
                        break;
                }

                // To make sure of no repeat colors, when creating passcode
                for (int j = 0; j < i; j++) {
                    if (secretCodeList[i] == secretCodeList[j]) {
                        repeat = true;
                    }
                }
            }
        }

        passcode = "";
        for (int i = 0; i < CODE_LENGTH; i++) {
            passcode += secretCodeList[i];
        }
    }


    /**
     * Compares guess with passcode
     * @param guess player guess of secret code
     * @return true if code is equal to guess
     *         false if not is not equal to guess
     */
    public boolean compareCode(String guess) {
        if (guess.equals(passcode)) {
            return true;
        }

        else {
            return false;
        }
    }


    /**
     * This method evaluates the players guess to determine if there are any colors in the
     * correct sequence compared to the passcode. If found, the number of black pegs will be
     * incremented.
     *
     * @return numBlack the number of black pegs needed in the feedback String.
     */
    public int numCorrectColorAndSlot(String guess) {
        int numBlack = 0;

        for (int i = 0; i < CODE_LENGTH; i++) {
            if (guess.charAt(i) == passcode.charAt(i)) {
                numBlack++;
            }
        }

        return numBlack;
    }


    /**
     * This method evaluates the player's guess to determine if there are any colors
     * correctly guessed compared to the passcode. If found, the number of white pegs
     * will be incremented.
     *
     * @return numWhite the number of white pegs needed in the feedback String.
     */
    public int numCorrectColor(String guess) {
        int numWhite = 0;

        for (int i = 0; i < CODE_LENGTH; i++) {
            if (guess.charAt(i) != passcode.charAt(i)) {
                boolean instance = false;

                for (int j = 0; j < CODE_LENGTH; j++) {
                    if (guess.charAt(i) == passcode.charAt(j)) {
                        instance = true;
                    }
                }

                if (instance) {
                    numWhite++;
                }
            }
        }

        return numWhite;
    }


    /**
     * Returns guess feedback pegs (black and white pegs)
     *
     * @return feedBack code
     */
    public String feedback(String guess) {

        String result = "";
        String resultOne = "";
        int blackPeg = numCorrectColorAndSlot(guess);
        int whitePeg = numCorrectColor(guess);

        for (int i = 0; i < blackPeg; i++) {
            result += BLACK_PEG;
        }

        for (int i = 0; i < whitePeg; i++) {
            result += WHITE_PEG;
        }

        if (result.length() < CODE_LENGTH) {
            for (int i = 0; i < (CODE_LENGTH - result.length()); i++) {
                resultOne += " ";
            }

            result = (result + resultOne);
        }
        return result;
    }
}
