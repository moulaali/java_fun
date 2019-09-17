import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import com.google.common.collect.ImmutableList; 

/**
*
* https://leetcode.com/problems/lfu-cache/

Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.

 

Follow up:
Could you do both operations in O(1) time complexity?

 

Example:

LFUCache cache = new LFUCache(2);

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

Approach : 
* Option 1: Maintain a simple map with a value object (value, freq). for lfu, do a O(n) lookup for LF
* Option 2: In addition to key value map. have another map that stores key->node_in_list. The list is a ascending ordered
*           on frequency and list of keys with same frequency. the tail has LFU keys. when key value is incremented :
            1/ If new key, add a new node with freq 1 
            2/ if a node exists, remove the key and add it to next node (if next node freq is freq + 1) or create a new node
*
*  based on : https://leetcode.com/problems/lfu-cache/discuss/94515/Java-O(1)-Accept-Solution-Using-HashMap-DoubleLinkedList-and-LinkedHashSet          
*
*/
class Solution {
  public static void main(String[] args)  {
    LfuCache cache = new LfuCache(10);
    
    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);       // returns 1
    cache.put(3, 3);    
    cache.get(2);      
  }
  
  static class LfuCache {
    
    int capacity;
    private Map<Integer, Integer> kvMap;
    private Map<Integer, Node> kNodeMap;
    
    LfuCache(int capacity) {
      this.kvMap = new HashMap<>();
      this.capacity = capacity;
    }
    
    int get(int key) {
      Integer value = kvMap.get(key);
      
      if (value == null) {
        return -1;
      }
      
      incrementKey(key);
      
      System.out.println("get() response: " + value);
      return (value);
    }
    
    void put(int key, int newValue) {
      Integer currentValue = kvMap.get(key);
      
      // Update
      if (currentValue != null) {
        kvMap.put(key, newValue);
      }
      
      // is cache out of space
      if (kvMap.size() == capacity) {
        evictLfu();
      }
      
      if (currentValue == null) {
        kvMap.put(key, newValue);
        kNodeMap.put(key, new Node(key, 1));
      }
      
      incrementKey(key);
    }
    
    void incrementKey(int key) {
      Node node = kNodeMap.get(key);
      
      // if there is a next node 
      if (node.next != null) {
        // with freq + 1, move the key there
        if (node.next.frequency == node.frequency + 1) {
          node.next.keys.add(key);  
        } else {
          // insert after the current node
          Node newNode = new Node(key, frequency + 1);
          newNode.prev = node;
          newNode.next = node.next;
          node.next = newNode;
          node.next.prev = newNode;
        }
      } else {
        // there is no next node. i.e. it is tail 
        Node newNode = new Node(key, frequency + 1);
        newNode.prev = node;
        newNode.next = node.next;
        node.next = newNode;
        
        tail = newNode;
      }  
        
      
      // cleanup current node
      node.keys.remove(key);
      if (node.keys.isEmpty()) {
        removeNode(node);
      }
      
      
      System.out.println("Frequency List status after incrementing key " + key + ":" + )
      
      return;
    }
    
    void evictLfu() {
      // TODO
      return;
    }
  }
  
  static class Node {
    int frequency;
    List<Integer> keys;
    Node next;
    Node prev;
    
    Node(int key, int frequency) {
      this.frequency = frequency;
      this.keys = new ArrayList<>();
      keys.add(key);
    }
  }
  
  static class DLList {
    Node head;
    Node tail;
    
    void removeNode(Node n) {
      if (head == n) {
        head = n.next;
        n.next.prev = null;
      } else if (tail == n) {
        tail = n.prev;
        n.prev.next = null;
      } 
      else {
        n.prev.next = n.next;
        n.next.prev = n.prev;
      }
    }
    
    void print() {
      Node n = head;
      
      while (n != null) {
        System.out.print(n + ":" + "->");
        n = n.next;
      }
      
      System.out.println("");
    }
  }
  
}