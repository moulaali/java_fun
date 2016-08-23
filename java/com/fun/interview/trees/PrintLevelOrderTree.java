package fun;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A demo class to print a tree in level order.
 * 
 * <P>The BFS travel is managed using a {@link Queue}. The completion of level
 * is tracked using a marker node.
 * 
 * @author Moulaali Shaik
 */
public class PrintLevelOrderTree {
  
  private static class Node {
    private final int data;
    List<Node> children;
    
    Node(int data) {
      this.data = data;
      this.children = new ArrayList<>();
    }
    
    Node addChild(Node childNode) {
      children.add(childNode);
      return this;
    }
    
    static Node newMarkerNode() {
      return new Node(Integer.MIN_VALUE);
    }
    
    boolean isMarkerNode() {
      return (data == Integer.MIN_VALUE);
    }
    
    @Override
    public String toString() {
      return String.valueOf(data);
    }

    String toLevelOrder() {
      StringBuilder outputBuilder = new StringBuilder();
      LinkedList<Node> queue = new LinkedList<>();

      // Eneque the root
      queue.add(this);
      queue.add(newMarkerNode());
      
      while (!queue.isEmpty()) {
        Node node = queue.removeFirst();
        
        // Handle level completion
        if (node.data == Integer.MIN_VALUE) {
          outputBuilder.append("\n");
          if (!queue.isEmpty()) {
            queue.add(newMarkerNode());
          }
          continue;
        }
        
        // Print this node and enequeue all the children
        boolean lastNodeInLevel = (queue.peek() != null) && (queue.peek().isMarkerNode());
        outputBuilder.append(node.toString() + (lastNodeInLevel ? "" : ", "));
        queue.addAll(node.children);
      }
      
      return outputBuilder.toString();
    }
  }
  
  public static void main(String[] args) {
    
    // Build a Tree manually
    Node root = new Node(1);
    
    Node level1Child1 = new Node(2);
    Node level1Child2 = new Node(3);
    Node level1Child3 = new Node(4);
    
    root
      .addChild(level1Child1)
      .addChild(level1Child2)
      .addChild(level1Child3);
    
    Node level2Child1 = new Node(5);
    Node level2Child2 = new Node(6);
    Node level2Child3 = new Node(7);

    level1Child1
      .addChild(level2Child1);
    level1Child2
      .addChild(level2Child2);
    level1Child3
      .addChild(level2Child3);
    
    
    // Print breadth-wise
    // This should print :
    // 1
    // 2, 3, 4
    // 5, 6, 7
    System.out.println(root.toLevelOrder());
  }
}
