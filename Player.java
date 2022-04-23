import java.util.*;

public class Player {

    public static final int MAX_NUM_OF_GUESSES = 8;
    public static final int MAX_ROWS_COLUMNS = 8;


    private int totalPoints;
    private int numGuess;
    private String playerName;
    private String[][] gameBoard;
    private GuessAnalysis ga;

    /**
     * Player constructor initializes board of guesses array
     * @param playerName The name of the player
     */
    public Player(String playerName) {
        this.playerName = playerName;
        gameBoard = new String[MAX_ROWS_COLUMNS][MAX_ROWS_COLUMNS];
        //ga = new GuessAnalysis(); //Can we get rid of the seed since we are hard coding the testing?
        numGuess = 0;
        totalPoints = 0;

    }

    /**
     * This is a getter method for playerName
     */
    public String getName() {
        return playerName;
    }

    /**
     * This is a getter method for totalPoints
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /**
     * Adds points to totalPoints for every guess made by a player
     */
    public void addPoints() {
        totalPoints++;

    }

    /**
     * Decreases numGuess by 1 for every guess player makes
     * @param numGuess
     */
    public void addGuess() {
        numGuess++;
    }


    /**
     * getter method for numGuess
     * @return numGuess
     */
    public int getGuess() {
        //
        return 0;
    }

    /**
     * This method will return the String 2D array that continually
     * updates and stores each guess with its appropriate feedback per a single round.
     *
     * @param guess players attempted String guess of the hidden code
     * @param numGuess the guess number associated to the player's guess that is first passed in
     * @return gameBoard String 2D array of guesses and their appropriate feedback
     */
    public String [][] updateBoard(String guess, int numGuess) {

        String playerFeedback = ga.feedback(guess);
        int row = MAX_ROWS_COLUMNS - numGuess;
        // do we need to throw an error if numGuess is greater than 8??

        for (int i = 0; i < 4; i++) {
            gameBoard[row][i] = guess.substring(i, i + 1);
        }

        for (int i = 0; i < 4; i++) {
            gameBoard[row][i + 4] = playerFeedback.substring(i, i + 1);
        }

        return gameBoard;
    }

    public String getBoard() {
        String s = "";
        for (int i = numGuess - 1; i >= 0; i--) {
            s += Arrays.toString(gameBoard[i]) + "\n";
        }
        return s;
    }
}
