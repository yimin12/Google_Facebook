package Algorithm.Graph.Tree.Trie;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/26 12:21
 *   @Description :
 *      Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, output sequence in dictionary order.
        Transformation rule such that:
        Only one letter can be changed at a time
        Each intermediate word must exist in the dictionary
     Example 1:
        Input：start = "a"，end = "c"，dict =["a","b","c"]
        Output：[["a","c"]]
        Explanation：
        "a"->"c"
        Example 2:

        Input：start ="hit"，end = "cog"，dict =["hot","dot","dog","lot","log"]
        Output：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
        Explanation：
        1."hit"->"hot"->"dot"->"dog"->"cog"
        2."hit"->"hot"->"lot"->"log"->"cog"
        The dictionary order of the first sequence is less than that of the second.
 */
public class WordLadderII {

    // 本题基于搜索完成。
    // 首先使用bfs，调用getnext()方法寻找当前单词的下一步单词，如果单词在dict中，就存入ret。
    // 然后使用dfs，枚举当前字符串的下一步方案，方案存入path,然后枚举搜索，搜索完成后，再将其删除。
    public List<List<String>> findLadders(String start, String end, Set<String> dict){
        List<List<String>> ladders = new ArrayList<List<String>>();
        if(start == null || end == null || dict == null){
            return ladders;
        }
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();

        dict.add(start);
        dict.add(end);
        // find what is the number of shortest length, mark the sequence for each word in dict
        bfs(map, distance, end, start, dict);
        List<String> path = new ArrayList<>();
        // traverse all words based on the sequence listed on the distance. e.g hit -> cog : distance->{hot:1, dot:2, lot:2, dog:3, log:3, cog:4}, this will be completed by bfs
        dfs(ladders, path, start, end, distance, map);
        return ladders;
    }
    private void bfs(Map<String, List<String>> map, Map<String, Integer> distance, String start, String end, Set<String> dict){
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 0);
        for(String s : dict){
            map.put(s, new ArrayList<>());
        }
        while(!queue.isEmpty()){
            String cur = queue.poll();
            List<String> nextList = expand(cur, dict);
            for(String next:nextList){
                map.get(next).add(cur);
                if(!distance.containsKey(next)){
                    distance.put(next, distance.get(cur) + 1);
                    queue.offer(next);
                }
            }
        }
    }
    private void dfs(List<List<String>> ladders, List<String> path, String cur, String end, Map<String, Integer> distance, Map<String, List<String>> map){
        path.add(cur);
        if(cur.equals(end)){
            ladders.add(new ArrayList<>(path));
        }
        for(String next:map.get(cur)){
            if(distance.containsKey(next) && distance.get(cur) == distance.get(next) + 1){
                dfs(ladders, path, next, end, distance, map);
            }
        }
        // handle the backtracking problem
        path.remove(path.size() - 1);
    }
    private List<String> expand(String cur, Set<String> dict){
        List<String> nextList = new ArrayList<>();
        for(int i = 0; i < cur.length(); i++){
            for(char c = 'a'; c < 'z'; c++){
                if(c != cur.charAt(i)){
                    String concat = cur.substring(0, i) + c + cur.substring(i+1);
                    if(dict.contains(concat)){
                        nextList.add(concat);
                    }
                }
            }
        }
        return nextList;
    }
}
