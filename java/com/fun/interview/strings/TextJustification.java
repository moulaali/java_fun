import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 => Text justification => 
Given a list of words and an integer L, print strings of length L, uniformly spaced from left to right. No need to add spaces if there is only one word in the line.

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
L = 16
Output:
[
   "This****is****an",
   "example**of*text",
   "justification."
] (Replaced spaces with * to make it clear)
 **/

public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        ArrayList<String> words = new ArrayList<>();
        words.add("This");
        words.add("is");
        words.add("an");
        words.add("example");
        words.add("of");
        words.add("text");
        words.add("justification");
        
        int l = 16;
        ArrayList<String> justified = new ArrayList<>();
        
        int i = 0;
        while (i < words.size()) {
            // word takes the line
            if (words.get(i).length() == l) {
                justified.add(words.get(i));
                i++;
                continue;
            }
            
            // Pack as many words
            StringBuilder sb = new StringBuilder();
            int len = l;
            
            ArrayList<String> tmp = new ArrayList<>();
            while (len > 0 && i < words.size()) {
                if (words.get(i).length() > len) {
                    System.out.printf("No space for word: %s. Next line\n", words.get(i));
                    break;
                }
                sb.append(words.get(i));
                tmp.add(words.get(i));
                len = (len - words.get(i).length());
                sb.append("*");
                len--;
                    
                i++;
            }
            
            int wordCount = tmp.size();
            String w = sb.toString();
            if (w.charAt(w.length() - 1) == '*') {
                w = w.substring(0, w.length() - 1);
            }
            
            // Distribute spaces
            if (wordCount != 1) {
                int diff = (l - w.length());
                int spaceW = (diff/(wordCount -1));
                int leftOver = diff % (wordCount - 1);
                
                StringBuilder sb2 = new StringBuilder();
                for (int k = 0; k < tmp.size() - 1; k++) {
                    sb2.append(tmp.get(k));
                    for (int m = 0; m < spaceW + 1; m++) {
                        sb2.append("*");
                    }
                    if (leftOver > 0 ) {sb2.append('*'); leftOver--;}
                }
                sb2.append(tmp.get(tmp.size() - 1));
                w = sb2.toString();
            }
            
            System.out.printf("Added word: %s. Next line\n", w);
            justified.add(w);
        }
        
        System.out.println("justified: " + Arrays.toString(justified.toArray()));
    }
}
