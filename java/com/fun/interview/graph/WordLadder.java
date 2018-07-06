/**
 *
 * Word Ladder
 * 
 * Input:  Dictionary = {POON, PLEE, SAME, POIE, PLEA, PLIE, POIN}
 *           start = TOON
             target = PLEA
 * Output: 7
 * Explanation: TOON - POON - POIN - POIE - PLIE - PLEE - PLEA
 * 
 */
 
import java.util.*;

class WordLadder {
    
    public static void main(String[] args)  {
        Set<String> dict = new HashSet<>(Arrays.asList(
            "POON", "PLEE", "SAME", "POIE", "PLEA", "PLIE", "POIN"));
        String from = "TOON";
        String to = "PLEA";
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> pre = new HashMap<>();
        
        q.add(from);
        pre.put(from, "");
        
        while (!q.isEmpty()) {
            
            String e = q.remove();
            System.out.println("Removed " + e + " from queue");
            
            for (String word : dict) {
                if (!visited.contains(word)
                   && isEdge(word, e)) {
                       System.out.println("Found unvisited neighbor : " + word);
                    pre.put(word, e);
                    visited.add(word);
                    
                    if (word.equals(to)) {
                        System.out.println("Found target: " + word);
                        
                        // print path
                        int len = 0;
                        while (!pre.get(word).equals("")) {
                            len++;
                            System.out.print(pre.get(word) + " - ");
                            word = pre.get(word);
                        }
                        System.out.println("\nchanin len " + len);
                        
                        return;
                    }
                    q.add(word);
                 }
            }
        }
        
    }
    
    static boolean isEdge(String word, String dictWord) {
        if (word.length() != dictWord.length()) {
            System.out.println(" word and dict are diff lens" + word + " " + dictWord);
        }
        
        int diffs = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != dictWord.charAt(i)) {
                diffs++;
                if (diffs > 1) {
                    return false;
                }
            }    
        }
        
        return true;
    }
}
