import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Divide the set into two partitions so that the difference in the sums is minimum
 */
public class KnapSack {

    public static void main(String[] args) {
    
        Item item1 = new Item(60, 10);
        Item item2 = new Item(100, 20);
        Item item3 = new Item(120, 30);
        
        Item[] items = {item1, item2, item3};
        int maxWeight = 50;
        
        System.out.println(knapsack(items, 220));  
    }
    
    private static int knapsack(Item[] items, int maxWeight) {
        int[][] maxValue = new int[maxWeight + 1][items.length];
        for (int w = 1; w < maxWeight; w++) {
            for (int j = 1; j < items.length; j++) {
                if (items[j].weight > maxWeight) {
                    continue;  // Too heavy
                }
                
                maxValue[w][j] = Math.max(maxValue[w][j-1], maxValue[(w-items[j].weight][j - 1] + items[j].value);
            }
        }
        
        return maxValue[maxWeight][items.length - 1];
    }
    
    private static class Item {
        int value;
        int weight;
        
        public Item(int value, int item) {
            this.value = value;
            this.weight = item;
        }
    }
}
