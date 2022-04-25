import java.util.*;

public class Player {

    public static final int MAX_NUM_OF_GUESSES = 8;
    public static final int MAX_ROWS_COLUMNS = 8;


    private int totalPoints;
    private int numGuess;
    private String playerName;
    private String[][] gameBoard;
    private GuessAnalysis ga;
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
    
    public String getX() {
        return ga.x();
    }

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

    public boolean getCompareCode(String guess) {
        return ga.compareCode(guess);
    }
    
    public int getNumCorrectColorAndSlot(String guess) {
        return ga.numCorrectColorAndSlot(guess);
    }
    
    public int getNumCorrectColor(String guess) {
        return ga.numCorrectColor(guess);
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

    public String getBoard() {
        String s = "";
        for (int i = MAX_ROWS_COLUMNS - numGuess; i < MAX_ROWS_COLUMNS; i++) {
            s += Arrays.toString(gameBoard[i]) + "\n";
        }
        return s;
    }
}
