/**
 *
 * Small game:
 * 
 * Take two largest stones in list of stones. smash them. if they are equal, result is 0.
 * if not, a smaller stone is formed of w1-w2 size. repeat and print final stone weight 
 * or 0
 */
 
import java.util.*;

class StoneGame {
    
    public static void main(String[] args)  {
        Integer[] a = {13, 12, 1};
        
        PriorityQueue<Integer> h = new PriorityQueue<Integer>(Collections.reverseOrder());
        h.addAll(Arrays.asList(a));
        
        while(true) {
            if (h.isEmpty()) {
                System.out.println("0");
                return;
            }
            
            Integer large = h.poll();
            
            if (h.isEmpty()) {
                System.out.println("One remaining " + large);
                return;
            }
            
            Integer next = h.poll();
            
            Integer result = (large - next);
            System.out.println("Smashed " + large + " and " + next + " result " + result);
            
            if (result > 0) {
                h.offer(result);
            }
        }
      
    }
} 
