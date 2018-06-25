/**
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
 
import java.util.*;

class LongestSubstringWoRepeatedChars {
 
    public static void main(String[] args)  {
        String input = "abcdefgdrshxyzuvq";
        Map<Character, Integer> m = new HashMap<>();

        int begin = 0;
        int end = 0;
        int curMax = 0;
        int max = 0;
        int curBegin = 0;
        int maxBegin = 0;
        int loopcount = 0;

        while (begin < input.length() && end < input.length()) {
            Character c = input.charAt(end);
            if (m.get(c) == null) {
                System.out.println("Extending window with: " + c);
                m.put(c, end);
                
                curMax++;
                if (curMax > max) {
                    System.out.println("Setting new max to " + curMax + " at begin: " + begin);
                    max = curMax;
                    maxBegin = begin;
                }
            } else {
                System.out.println("Char repeated: " + c + " Moving begin");
                // Move the begin beyond the repetition
                while (input.charAt(begin) != c) {
                    m.remove(input.charAt(begin));
                    begin++;
                    curMax--;
                }
                begin++;
                
                curBegin = begin;
                System.out.println("Reset window. curBegin, curMax : " + curBegin + " " + curMax + " begin at: " + input.charAt(begin));
            }
            end++;
        }
        
        System.out.println("Max substring " + input.substring(maxBegin, maxBegin + max));
    }
}
