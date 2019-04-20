import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * 
 * Deep copy a linked list with random pointer
 * 
 * Approach:
 *  - copy the nodes and next as usual with a map of originalNode->newNode
 *  - Iterate both lists again and update the random pointer 
 *    - copyNode.random = map.get(original.random); // i.e we get the equivalent index node in new list
 */
class CloneLinkedListWithRandomPointer {
    
    static class Node {
        int data;
        Node next;
        Node random;
        
        public Node(int data) {
            this.data = data;
            this.next = null;
            this.random = null;
        }
        
        Node append(int data) {
            Node current = this;
            
            while (current.next != null) {
                current = current.next;
            }
            
            Node newNode = new Node(data);
            current.next = newNode;
            
            return newNode;
        }
        
        Node cloneMe() {
            Node current = this;
            Node copy;
            
            // copy nodes and next with a map of node to new
            Map<Node, Node> nodeMap = new HashMap<>();
            copy = new Node(current.data);
            nodeMap.put(current, copy);
            current = current.next;

            
            while (current != null) {
                Node newNode = copy.append(current.data);
                nodeMap.put(current, newNode);
                
                current = current.next;
            }
            
            // Update random
            // Iterate original and copy and point copy.random to right node in copy.
            // The right node in copy will be based on the mapping in the original node's random.
            current = this;
            Node copyCurrent = copy;
            while (current != null) {
                copyCurrent.random = nodeMap.get(current.random);
                current = current.next;
                copyCurrent = copyCurrent.next;
            }
            
            return copy;
        }
        
        void print() {
            Node current  = this;
            
            while (current != null) {
                System.out.printf("%d(rnd:%d)->", current.data, current.random.data);
                current = current.next;
            }
            
            System.out.println("");
        }
    }
    
    
    public static void main(String[] args) {
        Node first = new Node(1);;
        Node second = first.append(2);
        Node third = second.append(3);
        Node four = third.append(4);
        Node five = third.append(5);
        
        first.random = third;
        second.random = first;
        third.random = five;
        four.random = five;
        five.random = second;
        
        first.print();    
        first.cloneMe().print();
    }
}
