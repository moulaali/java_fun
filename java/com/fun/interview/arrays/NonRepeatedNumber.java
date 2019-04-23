/**
 *  Find NonRepeated number in sorted array

Input:   arr[] = {1, 1, 3, 3, 4, 5, 5, 7, 7, 8, 8}
Output:  4

Input:   arr[] = {1, 1, 3, 3, 4, 4, 5, 5, 7, 7, 8}
Output:  8

Idea is to look the mid number and see if its index is odd or even. if it is odd, its 
left neighbor should ne same. if it is even, it's right neighbor should be same.
 */
 
import java.util.*;

class NonRepeatedNumber {
    
    public static void main(String[] args)  {
        int[] input = {1, 1, 2, 3, 3, 5, 5};
        System.out.println(find(input, 0, input.length - 1));
    }
    
    static int find(int[] input, int start, int end) {
        System.out.println("find called with start, end: " + start + ", " + end);
        if (start == end) {
            System.out.println("Reached one element at start: " + start);
            if(isValid(input, start)) {
                return -1;
            } else {
                return input[start];
            }
        }
        
        int mid = start + ((int) (end - start) / 2);
        if (isValid(input, mid)) {
            System.out.println("mid = " + mid + " is " + input[mid] + " valid.checking right half");
            return find(input, mid + 1, end);
        } else {
            System.out.println("mid = " + mid + " is " + input[mid] + " invalid. checking left half");
            return find(input, start, mid - 1);
        }
    }
    
    static boolean isValid(int[] input, int i) {
        if (i % 2 == 0) {
            return (i + 1 < input.length &&  (input[i + 1] == input[i]));
        } else {
            return (i >= 0 && (input[i - 1] == input[i]));
        }
    }
}
