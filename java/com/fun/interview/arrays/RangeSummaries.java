/**
Input:  [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
*/

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class RangeSummaries
{
    
    public static void main(String[] args) {
        int[] a = new int[] {0,1,2,4,5,7};
        List<String> r = new ArrayList<>();
        int start = a[0];
        int prev = a[0];
        
        for (int i = 1; i < a.length; i++) {
            if (a[i] != (prev + 1)) {
                System.out.println("Added sumamry: " + (start + "-" + prev));
                add(r, start, prev);
                start = a[i];
            }
            prev = a[i];
        }
        add(r, start, prev);
        System.out.println("Added sumamry: " + (start + "-" + prev));
        System.out.println("All summaries: " + r);
    }
    
    static void add(List<String> l, int start, int prev) {
        if (start == prev) {
            l.add("" + start);
        } else {
            l.add(start + "-" + prev);
        }
    }
   }
  
