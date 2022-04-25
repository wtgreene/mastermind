import java.util.*;

/**
 * Player Class for Team 2 CE - The Orginal Wordle
 *
 * @author Lyndsay Barnes
 * @author Casey Beise
 * @author Will Greene
 */
public class Player {

    /** An integer value set to 8 representing how many times a single player can attempt to
        guess the hidden passcode */
    public static final int MAX_NUM_OF_GUESSES = 8;

    /** An integer value set to 8 representing the maximum number of rows and columns for the
        player’s game board */
    public static final int MAX_ROWS_COLUMNS = 8;

    /** The number of points a player has */
    private int totalPoints;
    
    /** The number of guesses a player has made during a round */
    private int numGuess;
    
    /** Player's name */
    private String playerName;
    
    /** Array(s) representing each player's guess throughout each round */
    private String[][] gameBoard;
    
    /** Instance of GuessAnalysis class used to create new passcodes */
    private GuessAnalysis ga;
    
    /** Random seed provided for testing */
    private int seed;
    

    /**
     * Player constructor initializes board of guesses array
     * @param playerName The name of the player
     */
    public Player(String playerName, int seed) {
        this.playerName = playerName;
        this.seed = seed;
        gameBoard = new String[MAX_ROWS_COLUMNS][MAX_ROWS_COLUMNS];
        ga = new GuessAnalysis(seed);
        numGuess = 0;
        totalPoints = 0;
    }


    /**
     * Creates new GuessAnalysis object, and resets numGuess and gameBoard for each round
     */
    public void newRound() {

        if (seed != -1) {
            seed = seed * seed + 1;
            ga = new GuessAnalysis(seed);
            numGuess = 0;
        }

        else {
            ga = new GuessAnalysis(seed);
        }

        gameBoard = new String[MAX_ROWS_COLUMNS][MAX_ROWS_COLUMNS]; //resets the gameBoard each round
        numGuess = 0; //resets the number of guess for the round
    }


    /**
     * Compares the players guess to the passcode.
     * @return true if guess is equal to passcode.
     * @return false if guess is not equal to passcode.
     */
    public boolean getCompareCode(String guess) {
        return ga.compareCode(guess);
    }


    /**
     * Gets number of pegs in guess that are the same color and slot as the passcode.
     * @return number of pegs in guess that are the same color and slot as the passcode.
     */
    public int getNumCorrectColorAndSlot(String guess) {
        return ga.numCorrectColorAndSlot(guess);
    }


    /**
     * Gets number of pegs in guess that are the same color as the passcode
     * @return number of pegs in guess that are the same color as the passcode
     */
    public int getNumCorrectColor(String guess) {
        return ga.numCorrectColor(guess);
    }


    /**
     * This is a getter method for playerName
     * @return playerName
     */
    public String getName() {
        return playerName;
    }


    /**
     * This is a getter method for totalPoints
     * @return totalPoints
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
     * Resets players totalPoints to zero for each new game
     */
    public void resetPoints() {
        totalPoints = 0;
    }


    /**
     * Increases numGuess by 1 for every guess player makes
     * @param numGuess
     */
    public void addGuess() {
        numGuess++;
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


    /**
     * Creates a string representation of the gameBoard
     * @return s string representation of the gameBoard
     */
    public String getBoard() {
        String s = "";
        for (int i = MAX_ROWS_COLUMNS - numGuess; i < MAX_ROWS_COLUMNS; i++) {
            s += Arrays.toString(gameBoard[i]) + "\n";
        }
        return s;
    }
}
