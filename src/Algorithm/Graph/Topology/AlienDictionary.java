package Algorithm.Graph.Topology;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/25 0:02
 *   @Description :
 *      There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of
 *      non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters
 *      in this language.
 * Example 1:
        Input:
        [
          "wrt",
          "wrf",
          "er",
          "ett",
          "rftt"]
        Output: "wertf"
 */
public class AlienDictionary {

//    1. Convert characters to a graph: Adjacency lists
//    2. Topological sorting: keep adding elements whose in-degree is 0
    public String alienOrder(String[] words){
       if(words == null || words.length == 0){
           return "";
       }
       StringBuilder sb = new StringBuilder();
       Map<Character, List<Character>> graph = new HashMap<>();
       Map<Character, Integer> indegree = new HashMap<>();
       // Construct and prepare the map
       for(String word : words){
           for(char c : word.toCharArray()){
               indegree.put(c, 0);
               graph.put(c, new ArrayList<>());
           }
       }
       for(int i = 0; i < words.length - 1; i++){
           String a = words[i];
           String b = words[i+1];
           // corner case: abc ab, c can not be compared with empty
           if(a.length() > b.length() && a.startsWith(b)){
               return "";
           }
           for(int j = 0; j < Math.min(a.length(), b.length()); j++){
               if(a.charAt(j) != b.charAt(j)){
                   graph.get(a.charAt(j)).add(b.charAt(j));
                   indegree.put(b.charAt(j), indegree.get(b.charAt(j)) + 1);
                   break;
               }
           }
       }
       // Start topological sort
       Queue<Character> queue = new LinkedList<>();
       // offer all node that it's indegree is 0
       for(Map.Entry<Character, Integer> map : indegree.entrySet()){
           if(map.getValue() == 0){
               queue.offer(map.getKey());
           }
       }
       // start bfs
        while(!queue.isEmpty()){
            Character ch = queue.poll();
            sb.append(ch);
            // connect all its neighbor
            for(Character c : graph.get(ch)){
                indegree.put(c, indegree.get(c) - 1);
                if(indegree.get(c) == 0){
                    queue.offer(c);
                }
            }
        }
        // if the topological sort is invalid
        if(sb.length() < indegree.size()){
            return "";
        }
        return sb.toString();
    }

    // dfs with purning
    private Map<Character, List<Character>> reverseAdList = new HashMap<>();
    private Map<Character, Boolean> visited = new HashMap<>();
    private StringBuilder res = new StringBuilder();
    public String alienOrderdfs(String[] words){
        if(words == null || words.length == 0){
            return "";
        }
        // prepare and construct the graph
        for(String word:words){
            for(char c : word.toCharArray()){
                reverseAdList.putIfAbsent(c, new ArrayList<>());
            }
        }
        for(int i = 0; i < words.length + 1; i++){
            String a = words[i];
            String b = words[i+1];
            if(a.length() > b.length() && a.startsWith(b)){
                return "";
            }
            for(int j = 0; j < Math.min(a.length(), b.length()); j++){
                if(a.charAt(j) != b.charAt(j)){
                    reverseAdList.get(b.charAt(j)).add(a.charAt(j)); // notice this is the reverse order
                    break;
                }
            }
        }
        // find all possible graph entry
        for(Character c : reverseAdList.keySet()){
            boolean result = dfs(c);
            if(!result) return ""; // if return false, mean there are no alien order
        }
        if(res.length() < reverseAdList.size()){
            return "";
        }
        return res.toString();
    }
    private boolean dfs(Character c){
        // case 1: do not visited before
        if(visited.containsKey(c)){
            return visited.get(c);
        }
        // case 2: first time visited
        visited.put(c, false);
        for(Character next:reverseAdList.get(c)){
            boolean result = dfs(next);
            if(!result) return false;
        }
        // case 3: mark as true when backtracking, avoid traverse this branch again
        visited.put(c, true);
        res.append(c);
        return true;
    }

    // Topological Sort with DFS
    /*
    visited[i] = -1. Not even exist.
    visited[i] = 0. Exist. Non-visited.
    visited[i] = 1. Visiting.
    visited[i] = 2. Visited.
     */
    // Assume it only contains 26 character
    private final int N = 26;
    public String alienOrderTopoDFS(String[] words){
        // use adjacent matrix here, this is undirected an unweighted graph
        boolean[][] graph = new boolean[N][N];
        int[] visited = new int[N];
        buildGraph(graph, words, visited);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            if(visited[i] == 0){
                if(!dfs(graph, visited, sb, i)) return "";
            }
        }
        return sb.reverse().toString();
    }
    private boolean dfs(boolean[][] graph, int[] visited, StringBuilder sb, int level){
        visited[level] = 1;
        for(int j = 0; j < N; j++){
            if(graph[level][j]){
                if(visited[j] == 1){
                    return false; // 1 => 1, it is a cycle
                } else if(visited[j] == 0){
                    // means unvisited before and keep traverse
                    if(!dfs(graph, visited, sb, j)) return false;
                }
            }
        }
        // backtracking
        visited[level] = 2;
        sb.append((char)(level + 'a'));
        return true;
    }
    private void buildGraph(boolean[][] graph, String[] words, int[] visited){
        Arrays.fill(visited, -1);
        for(int i = 0; i < words.length; i++){
            for(char c : words[i].toCharArray()) visited[c - 'a'] = 0; // mark as exist
            if( i > 0){
                String a = words[i-1], b = words[i];
                for(int j = 0; j <Math.min(a.length(), b.length()); j++){
                    if(a.charAt(j) != b.charAt(j)){
                        graph[a.charAt(j) - 'a'][b.charAt(j) - 'a'] = true;
                        break;
                    }
                }
            }
        }
    }
}
