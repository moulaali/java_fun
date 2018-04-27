import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class IslandCount {
    
    private class Element {
        int i;
        int j;
        
        voidElement(int i, int j) {
            this.i = i;
            this.j = j
        }
        
    }
    
    public static void main(String args[] ) throws Exception {
       int[][] matrix = {
           {1, 0, 0, 1, 0},
           {1, 1, 0, 1, 0},
           {0, 0, 0, 0, 1},
           {0, 1, 0, 0, 0},
           {0, 1, 0, 0, 0}
       };
       
       System.out.println(getIslandCount(matrix, size));
    }  
    
    /**
     * Counts number of islands of 1s
     */
    static int getIslandCount(int [][] matrix, int size) {
       
       boolean visited[][] = new int[size][size];
       islandCount = 0;
       Queue<Element> queue = new LinkedList<>();
       
       for (int i = 0; i < size; i++) {
           for (int j = 0; j < size; j++) {
               // Ignore non 1s
               if (matrix[i][j] == 0) {
                   continue;
               }
               
               // If this is not seen before, start a new island and mark all reachable nodes as visited
               if (!visited[i][j]) {
                   islandCount++;
                   queue.add(new Element(i, j));
                   while (!queue.isEmpty()) {
                       Element e = queue.remove();
                       visited[e.i][e.j] = true;
                       Element[] neighbors = e.getValidNeighbors(size, visited);
                       queue.addAll(neighbors);
                   }
               }
               
           }
       } 
    }
}
