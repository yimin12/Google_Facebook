package Algorithm.Graph.Tree.Trie;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/27 12:29
 *   @Description :
 *      Description
 *      Build tries from a list of <word, freq> pairs. Save top 10 for each node.
        Example1
        Input:
         <"abc", 2>
         <"ac", 4>
         <"ab", 9>
        Output:<a[9,4,2]<b[9,2]<c[2]<>>c[4]<>>>
        Explanation:
                    Root
                     /
                   a(9,4,2)
                  /    \
                b(9,2) c(4)
               /
             c(2)
        Example2
        Input:
        <"a", 10>
        <"c", 41>
        <"b", 50>
        <"abc", 5>
        Output: <a[10,5]<b[5]<c[5]<>>>b[50]<>c[41]<>>
 */
public class TrieService {
    class TrieNode{
        public NavigableMap<Character, TrieNode> children;
        public List<Integer> topTen;
        public TrieNode(){
            children = new TreeMap<Character, TrieNode>();
            List<String> topTen = new ArrayList<>();
        }
    }

    private TrieNode root;
    public TrieService(){
        root = new TrieNode();
    }
    public void insert(String word, int frequency){
        TrieNode cur = root;
        int n = word.length();
        // no mater what happen, we will insert directly. addFrequency will check its validation itself
        for(int i = 0; i < n; i++){
            Character c = word.charAt(i);
            if(!cur.children.containsKey(c)){
                cur.children.put(c, new TrieNode());
            }
            cur = cur.children.get(c);
            addFrequency(cur.topTen, frequency);
        }
    }
    public void addFrequency(List<Integer> topTen, int frequency){
        topTen.add(frequency);
        int n = topTen.size();
        int index = n - 1;
        while(index > 0){
            if(topTen.get(index) > topTen.get(index - 1)){
                int temp1 = topTen.get(index);
                int temp2 = topTen.get(index - 1);
                topTen.set(index, temp2);
                topTen.set(index - 1, temp1);
                index -= 1;
            } else {
                break;
            }
        }
        if(n > 10){
            topTen.remove(n-1);
        }
    }

}
