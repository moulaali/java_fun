import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A demo class to print a tree in level order.
 * 
 * <P>The BFS travel is managed using a {@link Queue}.
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
    
    @Override
    public String toString() {
      return String.valueOf(data);
    }

    String toLevelOrder() {
      StringBuilder outputBuilder = new StringBuilder();
      LinkedList<Node> queue = new LinkedList<>();

      // Eneque the root
      queue.add(this);
      
      while (!queue.isEmpty()) {
        
        int levelSize = queue.size();
        
        for (int i = 0; i < levelSize; i++) {
            // Print this node and enequeue all the children
            Node node = queue.removeFirst();
            outputBuilder.append(node.toString());
            queue.addAll(node.children);
        }
        
        outputBuilder.append("\n");
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
