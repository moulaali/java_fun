import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


/**
 * Prints number of islands of 1s in a matrix.
 *
  * <p> Solution involves creating a forest of BFS trees. Each tree is a connected component
  * and counted as 1 island. We start at every vertex and navigate the matrix in BFS manner.
  * Every valid neighbor whose value is 1 and unvisited is explored.
  */
public class IslandCount {
    
    private static class Element {
        
        int i;
        int j;
        
        private static final int[][] NEIGHBOR_OFFSETS = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1}
        };
        
        Element(int i, int j) {
            this.i = i;
            this.j = j;
        }
        
        List<Element> getValidNeighbors(int size, int[][] matrix, boolean[][] visited) {
            List<Element> neighbors = new ArrayList<>();
            
            for (int c = 0; c < NEIGHBOR_OFFSETS.length; c++) {
                int neighbor_i = i + NEIGHBOR_OFFSETS[c][0];
                int neighbor_j = j + NEIGHBOR_OFFSETS[c][1];
                
                if (neighbor_i < 0
                    || neighbor_i >= size
                    || neighbor_j < 0
                    || neighbor_j >= size
                    || (matrix[neighbor_i][neighbor_j] == 0)
                    || (visited[neighbor_i][neighbor_j] == true)) {
                        continue;
                }
                
                neighbors.add(new Element(neighbor_i, neighbor_j));
            }
            
            return neighbors;
        }
        
    }
    
    public static void main(String args[] ) throws Exception {
       // Returns 4
       int[][] matrix = {
           {1, 0, 0, 1, 0},
           {1, 1, 0, 1, 0},
           {0, 0, 0, 0, 1},
           {0, 1, 1, 0, 0},
           {0, 1, 0, 0, 1}
       };
       
       System.out.println("Number of islands: " + getIslandCount(matrix, 5));
    }
    
    
    /**
     * Counts number of islands of 1s
     */
    static int getIslandCount(int [][] matrix, int size) {
       
       boolean visited[][] = new boolean[size][size];
       int islandCount = 0;
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
                       List<Element> neighbors = e.getValidNeighbors(size, matrix, visited);
                       queue.addAll(neighbors);
                   }
               }
               
           }
       }
       
       return islandCount;
    }
}