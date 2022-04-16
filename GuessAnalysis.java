public class GuessAnalysis {

    private String guess;
    private String code;
    
    public GuessAnalysis (String guess, String code) {
        this.guess = guess;
        this.code = code;
    }
    
    public boolean isCorrectCode() {
        if (guess.equals(code)) {
            return true;
        }
        
        else {
            return false;
        }
    }
    
    public int numCorrectColorAndSlot() {
        int numBlack = 0;
        
        for (int i = 0; i < TheOriginalWordle.CODE_LENGTH; i++) {
            if (guess.charAt(i) == code.charAt(i)) {
                numBlack++;
            }
        }
        
        return numBlack;
    }
    
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
}