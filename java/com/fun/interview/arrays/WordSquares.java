
import java.util.Arrays;

/**
 * A wordsquare is a list of words that read same horizontally or vertically.
 */
class WordSquares {
    public static void main(String[] args) {
        String[] input = {
            "BALL",
            "AREA",
            "LEAD",
            "LADY",
        };
        System.out.println(isWordSquare(input));  // Prints true
    }
    
    private static boolean isWordSquare(String[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                if (input[i].charAt(j)!= input[j].charAt(i)) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
