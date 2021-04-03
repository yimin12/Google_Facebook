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
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if(start == null || end == null || wordList == null || wordList.size() == 0) return res;
        Set<String> dict = new HashSet<>(wordList);
        if(!dict.contains(end)) return res;
        // key : to, value : from -> strings that can go to key
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>(); // steps to current string from start
        dict.add(start);
        bfs(map, distance, end, start, dict); // bfs from end to start, its easier for dfs from start to end
        List<String> path = new ArrayList<>();
        dfs(res, path, start, end, distance, map);
        return res;
    }

    // calculate the least steps change from start to end
    private void bfs(Map<String, List<String>> map, Map<String, Integer> dis, String start, String end, Set<String> dict){
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        dis.put(start, 0);
        for(String s : dict){
            map.put(s, new ArrayList<>());
        }
        while(!queue.isEmpty()){
            String cur = queue.poll();
            List<String> nextList = expand(cur, dict);
            for(String next : nextList){
                map.get(next).add(cur);
                if(!dis.containsKey(next)){
                    dis.put(next, dis.get(cur) + 1);
                    queue.offer(next);
                }
            }
        }
    }

    // find the path
    private void dfs(List<List<String>> res, List<String> path, String start, String end, Map<String,Integer> dis, Map<String, List<String>> map){
        path.add(start);
        if(start.equals(end)){
            res.add(new ArrayList<>(path));
        }
        for(String next:map.get(start)){
            if(dis.containsKey(next) && dis.get(start) == dis.get(next) + 1){
                dfs(res, path, next, end, dis, map);
            }
        }
        path.remove(path.size() - 1);
    }

    private List<String> expand(String cur, Set<String> dict){
        List<String> nextList = new ArrayList<>();
        for(int i = 0; i < cur.length(); i ++){
            for(char c = 'a'; c <= 'z'; c ++){
                if(c != cur.charAt(i)){
                    String newStr = cur.substring(0, i) + c + cur.substring(i + 1);
                    if(dict.contains(newStr)){
                        nextList.add(newStr);
                    }
                }
            }
        }
        return nextList;
    }
}
