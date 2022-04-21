public class PassCode {

    public static final int CODE_LENGTH = 4;
        
    public static final int NUM_OF_COLORS = 6;
    
    public static final int BLACK_PEG = 'B';
    
    public static final int WHITE_PEG = 'W';
    
    public static final char RED = 'R';
    
    public static final char GREEN = 'G';
    
    public static final char BLUE = 'B';
    
    public static final char YELLOW = 'Y';
    
    public static final char ORANGE = 'O';
    
    public static final char PURPLE = 'P';
    
    private String guess;
    
    private String passcode;
    private String feedback;
    
    /**
     * PassCode constructor - creates new secret code
     */
    public PassCode() {
        // TODO
    }
    
    /**
     * Compares guess with passcode
     * @param guess player guess of secret code
     * @return true if code is equal to guess
     *         false if not is not equal to guess
     */
    public boolean compareCode() {
        // TODO
    }
    
    // I think we can delete this method but leaving it for now just in case
    public boolean isCorrectCode() {
        if (guess.equals(code)) {
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
    public int numCorrectColorAndSlot() {
        int numBlack = 0;
        
        for (int i = 0; i < TheOriginalWordle.CODE_LENGTH; i++) {
            if (guess.charAt(i) == code.charAt(i)) {
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
    public int numCorrectColor() {
        int numWhite = 0;
        
        for (int i = 0; i < TheOriginalWordle.CODE_LENGTH; i++) {
            if (guess.charAt(i) != code.charAt(i)) {
                boolean instance = false;
                
                for (int j = 0; j < TheOriginalWordle.CODE_LENGTH; j++) {
                    if (guess.charAt(i) == code.charAt(j)) {
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
    public String feedback() {
        // TODO
    }
    
    /**
     * This method will return the String 2D array that continually
     * updates and stores each guess with its appropriate feedback per a single round.
     *
     * @param guess players attempted String guess of the hidden code
     * @param numGuess the guess number associated to the player's guess that is first passed in
     * @return String 2D array of guesses and their appropriate feedback
     */
    public String[][] giveFeedback(String guess, int numGuess) {
        // TODO
    }
}