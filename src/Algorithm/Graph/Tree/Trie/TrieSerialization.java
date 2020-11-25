package Algorithm.Graph.Tree.Trie;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/27 15:31
 *   @Description :
 *      Description
        Serialize and deserialize a trie (prefix tree, search on internet for more details).
        You can specify your own serialization algorithm, the online judge only cares about whether you can successfully deserialize the output from your own serialize function.
        You don't have to serialize like the test data, you can design your own format.
        Example
        str = serialize(old_trie)

        >
        >
         str can be anything to represent a trie
        new_trie = deserialize(str)

        >
        >
         new_trie should have the same structure and values with old_trie
        An example of test data: trie tree<a<b<e<>>c<>d<f<>>>>, denote the following structure:
             root
              /
             a
           / | \
          b  c  d
         /       \
        e         f
 */
public class TrieSerialization {

    class TrieNode{
        public NavigableMap<Character, TrieNode> children;
        public TrieNode(){
            children = new TreeMap<Character, TrieNode>();
        }
    }
    public String serialization(TrieNode root){
        if(root == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("<");
        Iterator iter = root.children.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            TrieNode child = (TrieNode) entry.getValue();
            sb.append(key);
            sb.append(serialization(child));
        }
        sb.append(">");
        return sb.toString();
    }

    public TrieNode deserialize(String data){
        if(data == null || data.length() == 0){
            return null;
        }
        TrieNode root = new TrieNode();
        TrieNode current = root;
        Deque<TrieNode> path = new LinkedList<>();
        for(Character c : data.toCharArray()){
            switch (c){
            case '<':
                path.push(current);
                break;
            case '>':
                path.pop();
                break;
            default:
                current = new TrieNode();
                path.peek().children.put(c, current);
            }
        }
        return root;
    }
}
