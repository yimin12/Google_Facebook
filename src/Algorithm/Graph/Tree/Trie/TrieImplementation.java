package Algorithm.Graph.Tree.Trie;

import java.util.HashMap;
import java.util.Map;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/26 19:16
 *   @Description :
 *      Implement a trie with insert, search, and startsWith methods.
        Note:
        You may assume that all inputs are consist of lowercase letters a-z.
 */
public class TrieImplementation {

    // hashMap version of trie
    class TrieNodeI {
        public boolean isWord;
        public Map<Character, TrieNodeI> children;
        public TrieNodeI(){
            this.isWord = false;
            this.children = new HashMap<>();
        }
    }
    class TrieI{
        private TrieNodeI root;
        public TrieI(){
            this.root = new TrieNodeI();
        }
        public void insert(String word){
            TrieNodeI cur = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(cur.children.get(c) == null){
                    cur.children.put(c, new TrieNodeI());
                }
                cur = cur.children.get(c);
            }
            cur.isWord = true;
        }
        public boolean search(String word){
            TrieNodeI cur = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(cur.children.get(c) == null){
                    return false;
                }
                cur = cur.children.get(c);
            }
            return cur.isWord;
        }
        public boolean startsWith(String prefix){
            TrieNodeI cur = root;
            for(int i = 0; i < prefix.length(); i++){
                char c = prefix.charAt(i);
                if(cur.children.get(c) == null){
                    return false;
                }
                cur = cur.children.get(c);
            }
            return true;
        }
    }
    // array version of trie, more efficiency
    class TrieNode{
        TrieNode[] array;
        boolean isWord;
        public TrieNode(){
            this.array = new TrieNode[26]; // assume there totally 26 character
        }
    }
    class Trie{
        private TrieNode root;
        public Trie(){
            root = new TrieNode();
        }
        public void insert(String word){
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                int index = c - 'a';
                if(cur.array[index] == null){
                    TrieNode temp = new TrieNode();
                    cur.array[index] = temp;
                    cur = temp;
                } else {
                    cur = cur.array[index];
                }
            }
            cur.isWord = true;
        }
        public boolean search(String word){
            TrieNode cur = searchNode(word);
            if(cur == null){
                return false;
            } else {
                return cur.isWord;
            }
        }
        public boolean startsWith(String prefix){
            TrieNode cur = searchNode(prefix);
            if(cur == null){
                return false;
            }
            return true;
        }
        private TrieNode searchNode(String word){
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                int index = c - 'a';
                if(cur.array[index] == null){
                    return null;
                } else {
                    cur = cur.array[index];
                }
            }
            if(cur == root){
                return null;
            }
            return cur;
        }
    }

}
