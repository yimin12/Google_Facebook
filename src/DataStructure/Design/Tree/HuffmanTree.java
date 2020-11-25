package DataStructure.Design.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/11 18:12
 *   @Description :
 *
 */
public class HuffmanTree {


    public HuffmanTree(){
        prefix = new HashMap<>();
    }

    // We store the data in object rather than array (Node)
    // internal static class
    private class Node implements Comparable<Node>{

        int weight;
        char data;
        Node left, right;

        Node(char data, int weight, Node left, Node right){
            this.weight = weight;
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public int compareTo(Node node){
            return weight - node.weight;
        }

        private boolean isLeaf(){
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }
    }

    // Sort these weight with priorityQueue
    private static Map<Character, String> prefix;
    private Node root;

    // encode the word frequency
    public String encode(String input){
        Map<Character, Integer> freq = new HashMap<>();
        String[] strings = input.split(" ");
        for(String string : strings){
            for(int i = 0; i < string.length(); i++){
                freq.put(input.charAt(i), freq.getOrDefault(input.charAt(i), 0) + 1);
            }
        }
        root = buildTree(freq);
        setPrefixCodes(root, new StringBuilder());
        StringBuilder s = new StringBuilder();
        for(String string : strings){
            for(int i = 0; i < string.length(); i++){
                char c = string.charAt(i);
                s.append(prefix.get(c));
            }
        }
        return s.toString();
    }

    // recursively find add the leaf to prefix map;
    private void setPrefixCodes(Node node, StringBuilder sb){
        if(node != null){
            if(node.isLeaf()){
                prefix.put(node.data, sb.toString());
            } else {
                // recursion rule
                // case 1;
                sb.append('0');
                setPrefixCodes(node.left, sb); // add '0' if go left
                sb.deleteCharAt(sb.length() - 1); // backtracking

                sb.append('1');
                setPrefixCodes(node.right, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public void decode(String input){
        StringBuilder sb = new StringBuilder();
        Node temp = root;
        for(int i = 0; i < input.length(); i++){
            int j = Integer.parseInt(String.valueOf(sb.charAt(i)));
            if(j == 0){
                temp = temp.left;
                // base case
                if(temp.isLeaf()){
                    sb.append(temp.data);
                    temp = root; // reset
                }
            }
            if(j == 1){
                temp = temp.right;
                if(temp.isLeaf()){
                    sb.append(temp.data);
                    temp = root;
                }
            }
        }

    }

    // sort the weight with priorityQueue
    public Node buildTree(Map<Character, Integer> freq){

        PriorityQueue<Node> sort = new PriorityQueue<>(); // minHeap
        Set<Character> keySet = freq.keySet();
        for(Character c : keySet){
            Node cur = new Node(c, freq.get(c), null, null);
            sort.offer(cur);
        }

        assert sort.size() > 0;
        // best first search to construct tree
        while(sort.size() > 1){
            // Assume that no concurrency problem
            Node x = sort.poll();
            Node y = sort.poll();
            Node sum = new Node('-', x.weight + y.weight, x, y);
            root = sum;
            sort.offer(sum);
        }
        // last largest node is the root
        return sort.poll();
    }

}
