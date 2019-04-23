/**
 *  Find max subarray in an array of integers
 * 
 * Idea is to compute the max possible value that ends at a given index.
 * That value could be previous_max + value_at_i or value_at_i
 */
import java.util.*;

class MaxSubArray {
 
    public static void main(String[] args)  {
        int[] input = {-2,1,-3,4,-1,2,1,-5,4};
        int max_ending_here = 0;
        int max_ending_here_begin = 0;
        int max = 0;
        int max_begin = 0;
        int max_end = 0;
        
        for (int i = 0; i < input.length; i++) {
            if ((max_ending_here + input[i]) > input[i]) {
                max_ending_here = max_ending_here + input[i];
                System.out.println("Found new max_ending_here " + max_ending_here);
            } else {
                max_ending_here = input[i];
                System.out.println("Found new max_ending_here " + max_ending_here);
                max_ending_here_begin = i;
            }
            
            if (max_ending_here > max) {
                max = max_ending_here;
                max_begin = max_ending_here_begin;
                max_end = i;
                System.out.println("Found new global max: " + max + " at begin " + max_ending_here_begin + " and end : " + i);
            }
        }
        
        System.out.println("Max subarray : " + max_begin + "-" + max_end + " and val=" + max);
    }
}
