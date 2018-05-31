import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Find inorder successor and predecessor
 */
public class BstSuccessorAndPredecessor {

    public static void main(String[] args) {
        Node root = buildTestTree();
        
        root.print();
        
        System.out.println(Node.predecessor(root, 10).data);
    }
    
    private static class Node {
        int data;
        Node left;
        Node right;
        Node parent;
        
        Node(int data) {
            this.data = data;
            left = null;
            right = null;
            parent = null;
        }
        
        Node addLeft(Node child) {
            child.parent = this;
            this.left = child;
            return child;
        }
        
        Node addRight(Node child) {
            child.parent = this;
            this.right = child;
            return child;
        }
        
        static Node newMarkerNode() {
            return new Node(Integer.MIN_VALUE);
        }
        
         boolean isMarkerNode() {
            return (data == Integer.MIN_VALUE);
        }
        
        static Node predecessor(Node start, int data) {
            Node current = start;
            
            if (start == null) {
                return null;
            }
            
            if (current.data == data) {
                System.out.println("Found target");
                if (current.left != null) {
                    // Right most child of LST
                    Node rmc = current.left;
                    while(rmc.right != null) {
                        rmc = rmc.right;
                    }
                    return rmc;
                } else {  
                    // No left child. Lowest ancestor whose Right child is ancestor
                }
            } else if (current.data > data) {
                System.out.println("Not found. going left at current" + current.data);
                return predecessor(current.left, data);
            } else {
                System.out.println("Not found. going right at current" + current.data);
                return predecessor(current.right, data);
            }
            
            return null;
        }
        
        void print() {
            
            System.out.println("Tree in Level-Order");
            
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
                outputBuilder.append(node.data + (lastNodeInLevel ? "" : ", "));
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
      
            System.out.println(outputBuilder.toString());
        }
    }
    
    
    static Node buildTestTree() {
        
        Node root_25 = new Node(25);
        
        Node node_20 = new Node(20);
        Node node_36 = new Node(36);
        root_25.addLeft(node_20);
        root_25.addRight(node_36);
        
        
        Node node_10 = new Node(10);
        Node node_22 = new Node(22);
        node_20.addLeft(node_10);
        node_20.addRight(node_22);
        
        Node node_5 = new Node(5);
        Node node_12 = new Node(12);
        node_10.addLeft(node_5);
        node_10.addRight(node_12);
        
        
        Node node_1 = new Node(1);
        Node node_8 = new Node(8);
        node_5.addLeft(node_1);
        node_5.addRight(node_8);
        
        
        Node node_15 = new Node(15);
        node_12.addRight(node_15);
        
        return root_25;
    }
}
