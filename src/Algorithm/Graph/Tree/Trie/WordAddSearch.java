package Algorithm.Graph.Tree.Trie;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/26 20:16
 *   @Description :
 *      Design a data structure that supports the following two operations:
        void addWord(word)
        boolean search(word)
        search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
        For example:
        addWord("bad")
        addWord("dad")
        addWord("mad")
        search("pad") -> false
        search("bad") -> true
        search(".ad") -> true
        search("b..") -> true
        Note:
        You may assume that all words are consist of lowercase letters a-z.
 */
public class WordAddSearch {

    // Use Trie to increase efficiency
    class TrieNode{
        public boolean isWord;
        public TrieNode[] children;
        public TrieNode(){
            this.children = new TrieNode[26];
        }
    }
    private TrieNode root;
    public WordAddSearch(){
        this.root = new TrieNode();
    }
    public void addWord(String word){
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++){
            int index = word.charAt(i) - 'a';
            if(cur.children[index] == null){
                cur.children[index] = new TrieNode();
            }
            cur = cur.children[index];
        }
        cur.isWord = true;
    }
    public boolean search(String word){
        return match(this.root, word, 0);
    }
    // recursive traverse
    private boolean match(TrieNode cur, String word, int level){
        if(level == word.length()) {
            return cur.isWord;
        }
        // case 1: "."
        if(word.charAt(level) == '.'){
            for(int i = 0; i < cur.children.length; i++){
                // traverse all possible child node
                if(cur.children[i] != null){
                    if(match(cur.children[i], word, level + 1)){
                        return true;
                    }
                }
            }
        } else {
            int index = word.charAt(level) - 'a';
            return cur.children[index] != null && match(cur.children[index], word, level+1);
        }
        return false;
    }
}
