import java.util.HashMap;
import java.util.Map;

/**
 You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Example:

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 Explanation: 342 + 465 = 807.
 */
public class AddNumsAsList {

  static class Node {
    int data;
    Node next;

    Node(int data) {
      this.data = data;
      this.next = null;
    }

    void print() {
      System.out.print(this.data + "->");
      if (this.next == null) {
        System.out.print("null");
      } else {
        this.next.print();
      }
    }

    Node append(int data) {
      Node n = new Node(data);
      this.next = n;
      return n;
    }

    Node append(Node n) {
      this.next = n;
      return n;
    }
  }

  static Node add(Node first, Node second) {
    Node sum = null;
    Node sumTail = null;
    int carry = 0;

    Node temp1 = first;
    Node temp2 = second;

    while (temp1 != null && temp2 != null) {
      int sumInt = (temp1.data + temp2.data + carry) % 10;
      carry = (temp1.data + temp2.data + carry) / 10;

      if (sum == null) {
        System.out.println("Adding new (first) node to sum " + sumInt);
        sum = new Node (sumInt);
        sumTail = sum;
      } else {
        System.out.println("Adding new node to sum " + sumInt);
        Node n = new Node(sumInt);
        sumTail.append(n);
        sumTail = n;
      }

      temp1 = temp1.next;
      temp2 = temp2.next;
    }

    // left over digits
    Node leftOver = null;
    if (temp1 != null) {
      leftOver = temp1;
    }

    if (temp2 != null) {
      leftOver = temp2;
    }

    if (leftOver == null) {
      return sum;
    }

    while (leftOver != null) {
      int sumInt = (leftOver.data + carry) % 10;
      carry = (leftOver.data + carry) / 10;
      System.out.println("Adding new node (leftover) to sum " + sumInt);

      Node n = new Node(sumInt);
      sumTail.append(n);
      sumTail = n;

      leftOver = leftOver.next;
    }

    // add carry over
    if (carry > 0) {
      Node n = new Node(carry);
      sumTail.append(n);
    }

    return sum;
  }

  public static void main(String[] args) {
    Node first = new Node(2);
    first.append(4).append(3).append(4);
    first.print();
    System.out.println("\n");

    Node second = new Node(5);
    second.append(6).append(4).append(9).append(9);
    second.print();
    System.out.println("\n");

    add(first, second).print();
  }
}
