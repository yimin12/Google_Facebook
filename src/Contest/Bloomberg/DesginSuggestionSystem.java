package Contest.Bloomberg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/3 20:22
 *   @Description :
 *
 */
public class DesginSuggestionSystem {

    private class TrieNode{
        TrieNode[] children;
        List<String> words;
        public TrieNode(){
            words = new ArrayList<>();
            children = new TrieNode[26]; // assume only lower case
        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // sort it lexicographically
        Arrays.sort(products);
        TrieNode root = new TrieNode();
        for(String p : products){
            TrieNode n = root;
            for(char c : p.toCharArray()){
                int i = c - 'a';
                if(n.children[i] == null){
                    n.children[i] = new TrieNode();
                }
                n = n.children[i];
                if(n.words.size() < 3){
                    n.words.add(p);
                }
            }
        }
        List<List<String>> res = new ArrayList<>();
        // traverse the trie tree for search words
        TrieNode n = root;
        for(int i = 0; i < searchWord.length(); i ++){
            n = n.children[searchWord.charAt(i) - 'a'];
            if(n == null){
                for(int j = i; j < searchWord.length(); j ++){
                    res.add(Collections.EMPTY_LIST);
                }
                break;
            }
            res.add(n.words);

        }
        return res;
    }
}
