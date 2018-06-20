/**
 * Find the substring with repeated chars
 */
 
class LongestRepeatedCharacter
{
 
    public static void main(String[] args) 
    {
        System.out.println(getLongestRepeatedChar("abbcddddefff")); //d
        System.out.println(getLongestRepeatedChar("baaaaaa"));  // a
        System.out.println(getLongestRepeatedChar("aaaaaab"));  // a
    }
    
    static char getLongestRepeatedChar(String input) {
        
        Character ans = input.charAt(0);
        int max = 1;
        int currentMax = 1;
        Character prev = input.charAt(0);
        
        for (int i = 1; i < input.length(); i++) {
            if (!((Character) input.charAt(i)).equals(prev)) {
                // char switch
                currentMax = 1;
            } else {
                currentMax++;
            }
            
            if (max < currentMax) {
                System.out.println("Setting new max to " + prev + " with count " + currentMax);
                max = currentMax;
                ans = prev;
            }
            
            prev = input.charAt(i);
        }
        
        return ans;
    }
}
