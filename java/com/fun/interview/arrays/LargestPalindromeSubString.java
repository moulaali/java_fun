import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


/**
 * Find the longest palindrome in a given string
 * "forgeekskeegfor" -> geeksskeeg
 * "bananas" -> "anana"
 */
public class LargestPalindromeSubstring {
    
    public static void main(String args[] ) throws Exception {
        String input = "forgeeksskeegfor";
        int max = -1;
        String maxStr = "";

        // for each char, go forward and backwards as far as you can
        for (int i = 0; i < input.length(); i++) {
            
            System.out.printf("\n\n --- Finding largest palindrome at %d. char %c\n --- ", i, input.charAt(i));
            int offset = 1;
            
            // Case when i and i + 1 are same
            if (i < input.length() - 1 && input.charAt(i) == input.charAt(i + 1)) {
                System.out.printf("Char at i and i + 1 are same for i=%c; will scan i-1 and i + 2", i);
                offset = 2;
            }
            
            // Case when i and i + 1 are different
            for (int j = i + offset, k = i - 1; j < input.length() && k >= 0; j++, k--) {
                System.out.printf("Trying %d, %d as start and end. chars : %c, chars %c \n", k, j, input.charAt(j), input.charAt(k));
                if (input.charAt(j) == input.charAt(k)) {
                  if (j - k > max) {
                      max = (j - k);
                      maxStr = input.substring(k, j + 1);
                      System.out.printf("Found max pal at start=%d, end=%d %s \n ", k, j, maxStr);
                  } 
                }  else {
                      System.out.println("Skipping since chars are not equal");
                      break;
                  }
            }
        }
        
        System.out.println("Max palindrome : " + maxStr);
    }
}
