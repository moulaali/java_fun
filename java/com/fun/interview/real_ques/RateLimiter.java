/**
* Implement a simple throttler that allows only x qps on per-client basis
*/
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

class RateLimiter
{
    Map<String, LinkedList<Integer>> map = new HashMap<>();
    int max;
    
    public static void main(String[] args) {
        Throttler t = new Throttler(2);
        t.allow("c1", 100);
        t.allow("c1", 200);
        t.allow("c1", 300);
        t.allow("c2", 310);
    }
    
    Throttler(int max) {
        this. max = max;
        map.put("c1", new LinkedList<>());
        map.put("c2", new LinkedList<>());
    }
    
    boolean allow(String c, Integer tsMillis) {
        
        LinkedList<Integer> l = map.get(c);
        
        System.out.print("tsMillis: " + tsMillis + " : " + ", client: " + c + " ");
        
        if (l.isEmpty() || (tsMillis - l.getLast() > 1000)) {
            // > 1s; reset the list
            System.out.println("More than 1s passed since last request. Clearing the active requests list. Do not throttle");
            l.clear();
            l.addFirst(tsMillis);
            return true;
        }
        
        if (l.size() >= max) {
            System.out.println("Too many requests. Try later. Throttle");
            return false;
        } 
        
        System.out.println("Active requests in limit. Do not throttle");
        l.addFirst(tsMillis);
        return true;
    }
}
