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
        // Reference Tree : http://btechsmartclass.com/DS/U5_T1.html
        Node root = buildTestTree();
        
        root.print();
        
        System.out.println("Predecessor of 10: " + Node.predecessor(root, 10));
        System.out.println("Predecessor of 28: " + Node.predecessor(root, 28));
        System.out.println("Predecessor of 40: " + Node.predecessor(root, 40));
        System.out.println("Predecessor of 50: " + Node.predecessor(root, 50));
        
        System.out.println("Successor of 25: " + Node.successor(root, 25));
        System.out.println("Successor of 12: " + Node.successor(root, 12));
        System.out.println("Successor of 1: " + Node.successor(root, 1));
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
        
        @Override
        public String toString() {
            return String.valueOf(this.data);
        }
        
        static Node predecessor(Node start, int data) {
            Node current = start;
            
            if (start == null) {
                return null;
            }
            
            if (current.data == data) {
                if (current.left != null) {
                    // Right most child of LST
                    Node rmc = current.left;
                    while(rmc.right != null) {
                        rmc = rmc.right;
                    }
                    return rmc;
                } else {  
                    // No left child. Lowest ancestor whose Right child is an ancestor
                    while (current.parent != null) {
                        Node parent = current.parent;
                        if (parent.right == current) {
                            return parent;
                        }
                        current = current.parent;
                    } 
                    return null;
                }
            } else if (current.data > data) {
                return predecessor(current.left, data);
            } else {
                return predecessor(current.right, data);
            }
        }
        
        static Node successor(Node start, int data) {
            // Mirror image code of predecessor
            Node current = start;
            
            if (start == null) {
                return null;
            }
            
            if (current.data == data) {
                if (current.right != null) {
                    // Left most child of RST
                    Node rmc = current.right;
                    while(rmc.left != null) {
                        rmc = rmc.left;
                    }
                    return rmc;
                } else {  
                    // No right child. Lowest ancestor whose left child is an ancestor
                    while (current.parent != null) {
                        Node parent = current.parent;
                        if (parent.left == current) {
                            return parent;
                        }
                        current = current.parent;
                    } 
                    return null;
                }
            } else if (current.data > data) {
                return successor(current.left, data);
            } else {
                return successor(current.right, data);
            }
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
        
        // Reference : http://btechsmartclass.com/DS/U5_T1.html
        
        Node root_25 = new Node(25);
        
        // Left subtree
        Node node_20 = new Node(20);
        root_25.addLeft(node_20);
        
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
        
        
        // Right subtree
        Node node_36 = new Node(36);
        root_25.addRight(node_36);
        
        Node node_30 = new Node(30);
        Node node_40 = new Node(40);
        node_36.addLeft(node_30);
        node_36.addRight(node_40);
        
        Node node_28 = new Node(28);
        node_30.addLeft(node_28);
        
        Node node_38 = new Node(38);
        Node node_48 = new Node(48);
        node_40.addLeft(node_38);
        node_40.addRight(node_48);
        
        Node node_45 = new Node(45);
        Node node_50 = new Node(50);
        node_48.addLeft(node_45);
        node_48.addRight(node_50);
        
        return root_25;
    }
}
