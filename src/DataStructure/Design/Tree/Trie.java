package DataStructure.Design.Tree;

import java.util.HashMap;
import java.util.Map;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/12 14:36
 *   @Description :
 *
 */
public class Trie {

    // The root character is an arbitrarily picked character chosen root node
    private final char rootCharacter = '\0'; // default message for root
    private Node root = new Node(rootCharacter);

    // Return true if the string being inserted contains a prefix already in the trie
    public boolean insert(String key){
        return insert(key, 1);
    }
    // Return true if the string being inserted contains a prefix already in the trie, support insert same word multiple times
    public boolean insert(String key, int num){
        if(key == null) throw new IllegalArgumentException("Null key is not permitted in trie");
        if(num < 0){
            throw new IllegalArgumentException("num of inserting string should greater than 0");
        }
        Node node = root;
        boolean create_new_node = false;
        boolean is_prefix = false;
        // Process one character a time
        for(int i = 0; i < key.length(); i++){
            char ch = key.charAt(i);
            Node nextNode = node.children.get(ch);
            if(nextNode == null){
                nextNode = new Node(ch);
                node.addChild(nextNode, ch);
                create_new_node = true; // there is no such string before
            } else {
                // already exist prefix
                if(nextNode.isWord) is_prefix = true; // These prefix is a word
            }
            node = nextNode;
            node.count += num;
        }
        if(node != root) node.isWord = true;
        return is_prefix || !create_new_node;
    }

    // Delete function allows us to delete keys from the trie (even those which were not previously inserted into the trie)
    // Notice: we may delete the prefix which cuts off the access to numerous other strings starting that prefix
    public boolean delete(String key, int num){
        if(!contains(key)) return false;
        if(num < 0) throw new IllegalArgumentException("num of deletions has to be positive");
        Node node = root;
        for(int i = 0; i < key.length(); i++){
            char ch = key.charAt(i);
            Node curNode = node.children.get(ch);
            curNode.count -= num;
            // Cut this edge if the current node has a count <= 0, it also means that all the prefixes below this point are inaccessible
            if(curNode.count <= 0){
                node.children.remove(ch);
                curNode.children = null;
                curNode = null;
                return true;
            }
            node = curNode;
        }
        return true;
    }

    public boolean delete(String key){
        return delete(key, 1);
    }

    public boolean contains(String key){
        return count(key) != 0;
    }

    public int count(String key){
        if(key == null) {
            throw new IllegalArgumentException("Null key is not permitted in trie");
        }
        Node node = root;
        for(int i = 0; i < key.length(); i++){
            char ch = key.charAt(i);
            if(node == null) return 0;
            node = node.children.get(ch);
        }
        if(node != null) return node.count;
        return 0;
    }

    // Recursively clear the trie and free the memory to help Garbage Collect
    private void clear(Node node){
        if(node == null) return;
        for(Character ch : node.children.keySet()){
            Node next = node.children.get(ch);
            clear(next);
            next = null;
        }
        node.children.clear();
        node.children = null;
    }

    // Close the trie service and reset the root;
    public void clear(){
        root.children = null;
        root = new Node(rootCharacter);
    }
    private static class Node{
        char ch;
        int count = 0; // number of ch
        boolean isWord = false;
        Map<Character, Node> children = new HashMap<>(); // use adjacent map to construct graph

        public Node(char ch){
            this.ch = ch;
        }

        public void addChild(Node node, char ch){
            children.put(ch, node);
        }
    }
}
