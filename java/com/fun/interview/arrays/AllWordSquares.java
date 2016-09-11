
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Demo class to retrieve all the word squares
 *
 * @author Moulaali Shaik
 */
class AllWordSquares {
    public static void main(String[] args) {
        // Corresponding word squares [(BALL, AREA, LEAD, LADY), (LADY, AREA, DEAR, YARD,)]
        List<String> input = new ArrayList<>(Arrays.asList(
            "AREA",
            "BALL",
            "DEAR",
            "LADY",
            "LEAD",
            "YARD",
            "ABBBBBB"
        ));
        List<String> chosenWords = new ArrayList<>();
        System.out.println(getAllWordSquares(chosenWords, input, 4));
    }
    
    private static List<List<String>> getAllWordSquares(List<String> chosenWords, List<String> words, int size) {
        List<List<String>> wordSquares = new ArrayList<>();
        
        // If chosenWords already form a wordsquare
        if (chosenWords.size() == size) {
            wordSquares.add(chosenWords);
            return wordSquares;
        }

        for (String word : words) {
            // not rightly sized word
            if (word.length() != size) {
                continue;
            }
            
            // see if the word is a possible candidate for the current row position
            if (isWordSquareOfSize(chosenWords, word)) {
                List<String> newlyChosenWords = new ArrayList<>(chosenWords);
                newlyChosenWords.add(word);
                List<String> remainingWords = new ArrayList<>(words);
                remainingWords.remove(word);
                
                wordSquares.addAll(getAllWordSquares(newlyChosenWords, remainingWords, size));
            }
        }
        return wordSquares;
    }
    
    private static boolean isWordSquareOfSize(List<String> chosenWords, String newWord) {
        int newWordPosition = chosenWords.size();
        for (int i = 0; i < newWordPosition; i++) {
            if (newWord.charAt(i) != chosenWords.get(i).charAt(newWordPosition)) {
                return false;
            }
        }
        return true;
    }
}
