/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

    You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Example
    
    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8
    Explanation: 342 + 465 = 807.
    
    https://leetcode.com/problems/add-two-numbers/description/
 */
 
class AddNumbersAsLinkedList
{
   
    static void printMiinWind(String S, String T) {

    }
 
    public static void main(String[] args) 
    {
        // 100
        Node head = new Node(0);
        Node first = new Node(0);
        Node second = new Node(1);
        head.next = first;
        first.next = second;
        head.printChain();
        
        // 111
        Node head2 = new Node(1);
        Node first2 = new Node(1);
        Node second2 = new Node(1);
        head2.next = first2;
        first2.next = second2;
        head2.printChain();
        
        add(head, head2).printChain();
    }
    
    static Node add(Node head, Node head2) {
        Node curr1 = head;
        Node curr2 = head2;
        
        Node sum = null;
        
        boolean first = true;
        int carry = 0;
        Node prev = null;
        
        while((curr1 != null) || (curr2 != null) || (carry != 0)) {
            int curr1data = (curr1 != null ? curr1.data : 0);
            int curr2data = (curr2 != null ? curr2.data : 0);
            
            Node n = new Node(((curr1data + curr2data + carry) % 10));
            carry = (curr1data + curr2data + carry) / 10;
            
            if (first) {
                sum = n;
                first = false;
            } else {
                prev.next = n;      
            }
            
            prev = n;
            if (curr1 != null) {
                curr1 = curr1.next;
            }
            if (curr2 != null) {
                curr2 = curr2.next;
            }
        }
        
        return sum;
    }
    

    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
        
        void printChain() {
            Node curr = this;
            
            for (;curr != null; curr= curr.next) {
                System.out.print(curr.data + "->");
            }
            
            System.out.println("null");
        }
    }
}
