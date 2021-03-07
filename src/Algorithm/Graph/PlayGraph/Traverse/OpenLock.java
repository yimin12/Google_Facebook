package Algorithm.Graph.PlayGraph.Traverse;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/9 17:35
 *   @Description :
 *       Leetcode 752
 */
public class OpenLock {

    public int openLock(String[] deadends, String target){
        if(deadends == null || target == null){
            return -1;
        }
        HashSet<String> deadset = new HashSet<>();
        for(String deadend : deadends){
            deadset.add(deadend);
        }
        if(deadset.contains(target) || deadset.contains("0000")){
            return -1;
        }
        if("0000".equals(target)) return 0;

        // BFS every state
        Queue<String> queue = new LinkedList<String>();
        HashMap<String, Integer> visited = new HashMap<>();
        queue.add("0000");
        visited.put("0000", 0); // init
        while(!queue.isEmpty()){
            String c = queue.poll();
            char[] cur = c.toCharArray();
            ArrayList<String> nexts = new ArrayList<>();
            for(int i = 0; i < 4; i++){
                char o = cur[i];
                // roll forward
                cur[i] = Character.forDigit((cur[i] - '0' + 1) % 10, 10);
                nexts.add(new String(cur));
                cur[i] = o;
                // roll back
                cur[i] = Character.forDigit((cur[i] - '0' + 9) % 10, 10);
                nexts.add(new String(cur));
                cur[i] = o;
            }
            for(String str : nexts){
                if(!deadset.contains(str) && !visited.containsKey(str)){
                    queue.add(str);
                    visited.put(str, visited.get(c) + 1);
                    if(str.equals(target)){
                        return visited.get(str);
                    }
                }
            }
        }
        return -1;
    }
}
