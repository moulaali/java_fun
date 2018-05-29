import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Partition the array into two subsets such that the difference in sums is minmal
 */
public class Main {

    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        partition(input);

        int[] input2 = {1, 5, 11, 5, 12};
        partition(input2);
    }
    
    private static void partition(int[] input) {
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i];
        }
        
        int[][] sumInclusion = new int[sum + 1][input.length + 1];
        
        // Sum of 0 is possible for any set by excluding all elements
        for (int i = 0; i < input.length ; i++) {
            sumInclusion[0][i] = 1;
        }

        for (int s = 1; s < sum + 1; s++) {
            for (int i = 1; i < input.length + 1; i++) {
                if (input[i - 1] > s) {
                    continue;
                }
                // Insert 1 if it is possible get a sum of S either through inclusion or exclusion
                sumInclusion[s][i] = Math.max(
                  sumInclusion[s][i-1],
                  sumInclusion[s - input[i - 1]][i-1]);
            }
        }
        
        // We now know if we can achieve a sum of s from above matix.
        // Now try to acheive s/2 (optimal) or the best minimal difference
        for (int optimalSum = ((int) sum/2); optimalSum >= 0; optimalSum--) {
          if (sumInclusion[optimalSum][input.length] == 1) {
            System.out.println("Possible to get a sum of " + optimalSum + "with elements"
              + Arrays.toString(sumInclusion[optimalSum]));
            break;
          } else {
            System.out.println("Cannot acheive a sum of " + optimalSum);
          }
        }

        return;
    }
}
