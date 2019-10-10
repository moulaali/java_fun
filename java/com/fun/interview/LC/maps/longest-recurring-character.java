import java.util.*;

/**
 * Find longest recurring character in a string
 *
 * <P> Idea is to keep track of frequency of current max and current char. Every time
 * the char switch happens, check and update the global max. This approach does not
 * need extra memory and can be in O(n) time
 *
 * @author Moulaali Shaik
 */
public class LongestRecurringCharacters {

    public static void main(String[] args) {
        String testInput1 = "ABBBCCD";  // B
        String testInput2 = "ABBBCCCCD";  // C
        String testInput3 = "ABCD"; // A

        System.out.println("Longest Recurring Char for " + testInput1 + " is : " + getLongestRecurringChar(testInput1));
        System.out.println("Longest Recurring Char for " + testInput2 + " is : " + getLongestRecurringChar(testInput2));
        System.out.println("Longest Recurring Char for " + testInput3 + " is : " + getLongestRecurringChar(testInput3));

    }

    static char getLongestRecurringChar(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }

        char longestChar = input.charAt(0);
        int maxFrequency = 1;
        int currentMaxFrequency = 1;
        char prevChar = input.charAt(0);

        for (int i = 1; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (currentChar == prevChar) {
                currentMaxFrequency++;
            } else {
                // switch happened. check if we have new maximum
                if (currentMaxFrequency > maxFrequency) {
                    longestChar = prevChar;
                    maxFrequency = currentMaxFrequency;
                }

                // Reset for the new char
                currentMaxFrequency = 1;
                prevChar = input.charAt(i);
            }
        }

        return longestChar;
    }
}
