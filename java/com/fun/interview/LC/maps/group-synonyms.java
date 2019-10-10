import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Create a cluster of synonymns and serve lookup queries
 * 
 * <P>Approach involves creating a graph of words and connect via edge if they are synonymns
 * Then using DFS, create groups of words (i.e connected components). In next step, create
 * a word-to-componentIndex map to easily identify the group that a word belong to.
 */
public class SynonymChecker {

    public static void main(String[] args) {
        String[][] synonyms = {
            {"food", "dish"},
            {"movie", "film"},
            {"food", "bite"},
            {"film", "cinema"},
            {"food", "cuisine"},
            {"dish", "khana"}
        };
        
        Map<Node, Set<Node>> graph = createSynonymGraph(synonyms);
        System.out.println("Created Synonym graph : " + graph);
        
        Set<Set<Node>> synonymGroups = findConnectedComponents(graph); 
        System.out.println("Created Strongly connected components : " + synonymGroups);
        
        Map<String, Integer> wordToGroupIndex = createWordToGroupIndexMap(synonymGroups);
        System.out.println("Created word-to-groupIndex map : " + wordToGroupIndex);
        
        System.out.println("Checking for synonym (film, cinema) : " + areSynonyms("film", "cinema", wordToGroupIndex)); // true
        System.out.println("Checking for synonym (food, dish) : " + areSynonyms("food", "dish", wordToGroupIndex)); // true
    }
    
    static boolean areSynonyms(String word, String otherWord, Map<String, Integer> wordToGroupIndex) {
        return wordToGroupIndex.get(word) == wordToGroupIndex.get(otherWord);
    }
    
    static Set<Set<Node>> findConnectedComponents(Map<Node, Set<Node>> graph) {
        // Do DFS for each unvisited
        Map<Node, Boolean> visited = new HashMap<>();
        Set<Set<Node>> components = new HashSet<>();
        
        int groupIndex = 0;
        
        for (Node n : graph.keySet()) {
            Set<Node> members = new HashSet<>();
            
            doDfs(graph, n, visited, members);
            
            if (members.size() > 0) {
                components.add(members);
            }
        }
        
        return components;
    }
    
    static Map<String, Integer> createWordToGroupIndexMap(Set<Set<Node>> synonymGroups) {
        Map<String, Integer> wordToIndexGroup = new HashMap<>();
        
        int groupIndex = 0;
        for (Set<Node> group : synonymGroups) {
            for (Node wordNode : group) {
                wordToIndexGroup.put(wordNode.data, groupIndex);        
            }
            groupIndex++;
        }
        
        return wordToIndexGroup;
    }
    
    static void doDfs(Map<Node, Set<Node>> graph, Node n, Map<Node, Boolean> visited, Set<Node> members) {
        
        Boolean visitedCheck = visited.get(n);
        if (visitedCheck != null && visitedCheck) {
            return;
        }
        
        visited.put(n, true);
    
        members.add(n);
        
        for (Node neighbor : graph.get(n)) {
            doDfs(graph, neighbor, visited, members);
        }
    }
    
    static Map<Node, Set<Node>> createSynonymGraph(String[][] synonyms) {
        Map<Node, Set<Node>> graph = new HashMap<>();
        
        for (int i = 0; i < synonyms.length; i++) {
            String word = synonyms[i][0];
            String synonym = synonyms[i][1];
            graph.computeIfAbsent(new Node(word), k -> new HashSet<>()).add(new Node(synonym));
            graph.computeIfAbsent(new Node(synonym), k -> new HashSet<>()).add(new Node(word)); 
        }
        
        return graph;
    }
    
    static class Node {
        String data;
        Set<String> neighbors;
        
        Node(String data) {
            this.data = data;
            neighbors = new HashSet<>();
        }
        
        @Override
        public String toString() {
            return data;
        }
        
         @Override
        public boolean equals(Object otherNode) {

            if (otherNode == this) return true;
            
            if (!(otherNode instanceof Node)) {
                return false;
            }
            
            Node node = (Node) otherNode;
            return this.data.equals(node.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }
}
