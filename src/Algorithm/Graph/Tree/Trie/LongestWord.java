package Algorithm.Graph.Tree.Trie;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/26 20:32
 *   @Description :
 *      Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.
        If there is no answer, return the empty string.
        Example 1:
        Input:
        words = ["w","wo","wor","worl", "world"]
        Output: "world"
        Explanation:
        The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
        Example 2:
        Input:
        words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
        Output: "apple"
        Explanation:
        Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
        Note:
        All the strings in the input will only contain lowercase letters.
        The length of words will be in the range [1, 1000].
        The length of words[i] will be in the range [1, 30].
 *
 */
public class LongestWord {

    public String longestWord(String[] words){
        Trie tree = new Trie();
        for(String word:words){
            tree.insert(word);
        }
        return dfs(tree.getRoot());
    }
    String dfs(TrieNode root){
        String longest = "";
        Deque<TrieNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        while(!stack.isEmpty()){
            TrieNode node = stack.pollFirst();
            if(node == null) continue;
            if(node.isWord || node == root){
                if(node != root){
                    if(node.word.length() > longest.length() || (node.word.length() == longest.length() && node.word.compareTo(longest) < 0)){
                        longest = node.word;
                    }
                }
                for(TrieNode child:node.children){
                    stack.push(child);
                }
            }
        }
        return longest;

    }
    // Use Trie
    class TrieNode{
        public TrieNode[] children;
        public boolean isWord;
        String word;
        public TrieNode(){
            children = new TrieNode[26];
        }
        boolean contains(char c){
            return children[c - 'a'] != null;
        }
        TrieNode get(char c){
            return children[c - 'a'];
        }
        void put(char c, TrieNode node){
            children[c - 'a'] = node;
        }
    }
    class Trie{
        TrieNode root;
        public Trie(){
            this.root = new TrieNode();
        }
        public void insert(String word){
            TrieNode node = root;
            char[] charArray = word.toCharArray();
            for(char ch : charArray){
                if(!node.contains(ch)){
                    node.put(ch, new TrieNode());
                }
                node = node.get(ch);
            }
            node.isWord = true;
            node.word = word;
        }
        public TrieNode getRoot(){
            return this.root;
        }
    }
}
