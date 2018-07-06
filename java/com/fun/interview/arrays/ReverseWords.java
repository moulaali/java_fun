/**

Given an input string, reverse the string word by word.

Example:  

Input: "the sky is blue",
Output: "blue is sky the".
Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.


 */
 
import java.util.*;

class ReverseWords {
 
    static boolean first = true;
    
    public static void main(String[] args)  {
        String input = "  the   sky  is  blue  ";
        char state = 'u';
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = input.length() - 1; i >= 0; i--) {
            char c = input.charAt(i);
            if (c != ' ') {
                System.out.println("char: " + c + " is not space. pushing to stack");
                state = 'w';
                stack.push(c);
            } else {
                // space
                System.out.println("char: " + c + " is space. and isStackEmpty" + stack.isEmpty());
                if (!stack.isEmpty()) {
                    // Append the word to answer 
                    popStack(sb, stack);
                } 
            }
        }
        
        popStack(sb, stack);
        
        System.out.println("reversed: " + sb.toString());
    }
    
    static void popStack(StringBuilder sb, Stack stack) {
        if (!first) {
            sb.append(" ");
        } else {
            first = false;
        }
                    
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
    }
}
