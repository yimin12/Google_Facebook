package Algorithm.Graph.Tree.Trie;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/26 11:11
 *   @Description :
 *      Given two words (start and end), and a dictionary, find the shortest transformation sequence from start to end, output the length of the sequence.
        Transformation rule such that:
        Only one letter can be changed at a time
        Each intermediate word must exist in the dictionary. (Start and end words do not need to appear in the dictionary )
        Input：start = "a"，end = "c"，dict =["a","b","c"]
        Output：2
        Explanation：
        "a"->"c"
        Example 2:

        Input：start ="hit"，end = "cog"，dict =["hot","dot","dog","lot","log"]
        Output：5
        Explanation：
        "hit"->"hot"->"dot"->"dog"->"cog"
 */
public class WordLadder {

    // single direction bfs
    public int ladderLengthI(String start, String end, Set<String> dict){
        // handle two conner case
        if(dict == null){
            return 0;
        }
        if(start.equals(end)){
            return 0;
        }
        //  add the starting point and the destination
        dict.add(start);
        dict.add(end);

        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(start);
        queue.offer(start);
        // start bfs
        int length = 1;
        while(!queue.isEmpty()){
            length++;
            int size = queue.size();
            for(int i = 0; i < size; i++){
                String word = queue.poll();
                for(String nextWord:getNextWords(word, dict)){
                    if(visited.contains(nextWord)){
                        continue;
                    }
                    if(nextWord.equals(end)){
                        return length;
                    }
                    visited.add(nextWord);
                    queue.offer(nextWord);
                }
            }
        }
        return 0;
    }
    private ArrayList<String> getNextWords(String word, Set<String> dict){
        ArrayList<String> nextWords = new ArrayList<>();
        for(char c = 'a'; c < 'z'; c++){
            for(int i = 0; i < word.length(); i++){
                // case 1: match
                if(c == word.charAt(i)){
                    continue;
                }
                // replace
                String nextWord = replace(word, i, c);
                if(dict.contains(nextWord)){
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }
    private String replace(String word, int i, char c){
        char[] chars = word.toCharArray();
        chars[i] = c;
        return new String(chars);
    }

    // bi-direction BFS
    public int ladderLength(String start, String end, Set<String> dict){
        // handle two conner case
        if(dict == null){
            return 0;
        }
        if(start.equals(end)){
            return 0;
        }
        // prepare bi-direction bfs
        HashMap<String, Set<String>> graph;
        Queue<String> forward = new LinkedList<>();
        Queue<String> backward = new LinkedList<>();
        Set<String> forwardSet = new HashSet<>();
        Set<String> backwordSet = new HashSet<>();

        dict.add(start);
        dict.add(end);
        graph = buildGraph(dict);
        forward.offer(start);
        backward.offer(end);
        forwardSet.add(start);
        backwordSet.add(end);

        int length = 1;
        while(!forward.isEmpty() && !backward.isEmpty()){
            length++;
            if(extendQueue(graph, forward, forwardSet, backwordSet)){
                return length;
            }
            length++;
            if(extendQueue(graph, backward, forwardSet, backwordSet)){
                return length;
            }
        }
        return - 1;
    }
    private boolean extendQueue(HashMap<String, Set<String>> graph, Queue<String> queue, Set<String> visited, Set<String> oppositedVisited){
        int size = queue.size();
        for(int i = 0; i < size; i++){
            String word = queue.poll();
            Set<String> nextWordSet = graph.get(word);
            for(String nextWord: nextWordSet){
                if(visited.contains(nextWord)){
                    continue;
                }
                if(oppositedVisited.contains(nextWord)){
                    return true;
                }
                queue.offer(nextWord);
                visited.add(nextWord);
            }
        }
        return false;
    }
    private HashMap<String, Set<String>> buildGraph(Set<String> dict){
        HashMap<String, Set<String>> graph = new HashMap<String, Set<String>>();
        for(String word : dict){
            graph.put(word, getNextWordsI(word, dict));
        }
        return graph;
    }
    private Set<String> getNextWordsI(String word, Set<String> dict){
        Set<String> next = new HashSet<>();
        int wordLength = word.length();
        for(int i = 0; i < wordLength; i++){
            String prefix = word.substring(0, i);
            String suffix = word.substring(i+1);
            char[] chars = ("abcdefghijklmnopqrstuvwxyz").toCharArray();
            for(int j = 0; j < 26; j++){
                if(word.charAt(i) == chars[j]){
                    continue;
                }
                String nextWord = prefix + chars[j] + suffix;
                if(dict.contains(nextWord)){
                    next.add(nextWord);
                }
            }
        }
        return next;
    }

    // BFS: Time ~ O(26MN) where M is the min steps and N is the word length, Space ~ O(26MN)
    public int ladderLengthIII(String start, String end, Set<String> dict) {
        // Assume that the given dict and start and end are all not null, and the elements of dict are valid
        Set<String> map = new HashSet<String>(); // record visited words in dict so as not to modify dict
        Queue<String> word = new LinkedList<String>();
        Queue<Integer> depth = new LinkedList<Integer>();
        word.add(start);
        depth.add(1);
        // using bfs
        while(!word.isEmpty()) {
            String curWord = word.poll();
            int currDepth = depth.poll();
            // return the depth if a match is found
            if(curWord.equals(end)) return currDepth;
            // change each letter of current Word
            for(int i = 0; i < curWord.length(); i++) {
                char[] curWordArray = curWord.toCharArray();
                // try every possible char and check if there's a match in dict
                for(char c = 'a'; c <= 'z'; c++) {
                    curWordArray[i] = c;
                    String newWord = new String(curWordArray);
                    if(dict.contains(newWord) && !map.contains(newWord)) {
                        word.add(newWord);
                        depth.add(currDepth + 1);
                        map.add(newWord);
                    }
                }
            }
        }
        return 0; // no match is found in dict
    }

    // Simplify Bi-Direction BFS
    public int ladderLengthII(String start, String end, List<String> dict){
        if(dict == null){
            return 0;
        }
        if(start.equals(end)){
            return 0;
        }
        Set<String> set= new HashSet<>();
        for(String str:dict){
            set.add(str);
        }
        if(start == null || end == null || dict.size() == 0){
            return 0;
        }
        Set<String> visited = new HashSet<>();
        Set<String> oppovisited = new HashSet<>();
        visited.add(start);
        oppovisited.add(end);
        set.remove(end);
        int step = 1;
        while(!visited.isEmpty() && !oppovisited.isEmpty()){
            Set<String> next = new HashSet<>();
            if(visited.size() > oppovisited.size()){
                // swap
                Set<String> temp = visited;
                visited = oppovisited;
                oppovisited = temp;
            }
            for(String str:visited){
                char[] word = str.toCharArray();
                for(int i = 0; i < word.length; i++){
                    char old_char = word[i];
                    for(char c = 'a'; c < 'z'; c++){
                        word[i] = c;
                        String cur = new String(word);
                        if(oppovisited.contains(cur)) return step+1;
                        if(set.contains(cur)){
                            next.add(cur);
                            set.remove(cur);
                        }
                    }
                }
            }
            visited = next;
            step++;
        }
        return 0;
    }
}
