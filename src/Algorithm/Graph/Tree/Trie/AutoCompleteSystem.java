package Algorithm.Graph.Tree.Trie;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/26 22:28
 *   @DescriDesign
        Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character'#'). Foreach characterthey typeexcept '#', you need to return thetop 3historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:
        The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
        The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
        If less than 3 hot sentences exist, then just return as many as you can.
        When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
        Your job is to implement the following functions:
        The constructor function:
        AutocompleteSystem(String[] sentences, int[] times):This is the constructor. The input ishistorical data.Sentencesis a string array consists of previously typed sentences.Timesis the corresponding times a sentence has been typed. Your system should record these historical data.
        Now, the user wants to input a new sentence. The following function will provide the next character the user types:
        List<String> input(char c):The inputcis the next character typed by the user. The character will only be lower-case letters ('a'to'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be thetop 3historical hot sentences that have prefix the same as the part of sentence already typed.
        Example:
        Operation:AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
        The system have already tracked down the following sentences and their corresponding times:
        "i love you":5times
        "island":3times
        "ironman":2times
        "i love leetcode":2times
        Now, the user begins another search:
        Operation:input('i')
        Output:["i love you", "island","i love leetcode"]
        Explanation:
        There are four sentences that have prefix"i". Among them, "ironman" and "i love leetcode" have same hot degree. Since' 'has ASCII code 32 and'r'has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
        Operation:input(' ')
        Output:["i love you","i love leetcode"]
        Explanation:
        There are only two sentences that have prefix"i ".
        Operation:input('a')
        Output:[]
        Explanation:
        There are no sentences that have prefix"i a".
        Operation:input('#')
        Output:[]
        Explanation:
        The user finished the input, the sentence"i a"should be saved as a historical sentence in system. And the following input will be counted as a new search.
        Note:
        The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
        The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
        Please use double-quote instead of single-quote when you write test cases even for a character input.
        Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are
        persisted across multiple test cases . Please see here for more details.
   */
public class AutoCompleteSystem {

    class TrieNode{
        Map<Character, TrieNode> children;
        Map<String, Integer> counts;
        boolean isWord;
        public TrieNode(){
            children = new HashMap<>();
            counts = new HashMap<>();
            isWord = false;
        }
    }
    // required fields for auto-complete system
    TrieNode root;
    String prefix;

    public AutoCompleteSystem(String[] sentences, int[] times){
        root = new TrieNode();
        prefix = "";
        for(int i = 0; i < sentences.length; i++){
            add(sentences[i], times[i]);
        }
    }
    private void add(String s, int count){
        TrieNode cur = root;
        for(char c : s.toCharArray()){
            cur.children.putIfAbsent(c, new TrieNode());
            cur = cur.children.get(c);
            cur.counts.put(s, cur.counts.getOrDefault(s, 0) + count);
        }
        cur.isWord = true;
    }

    public List<String> input(char c){
        if(c == '#'){
            add(prefix, 1);
            prefix = "";
            return new ArrayList<>();
        }
        prefix = prefix + c;
        TrieNode cur = root;
        for(char ch : prefix.toCharArray()){
            if(!cur.children.containsKey(ch)){
                return new ArrayList<String>();
            }
            cur = cur.children.get(ch);
        }
        // minHeap's comparator
        Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() == o2.getValue() ? o2.getKey().compareTo(o1.getKey()) : o1.getValue() - o2.getValue();
            }
        };
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(comparator);
        int k = 3;
        for(Map.Entry<String, Integer> entry: cur.counts.entrySet()){
            pq.offer(entry);
            while(!pq.isEmpty() && pq.size() > k){
                pq.poll();
            }
        }
        ArrayList<String> res = new ArrayList<>();
        while(!pq.isEmpty()){
            res.add(0, pq.poll().getKey());
        }
        return res;
    }


}
