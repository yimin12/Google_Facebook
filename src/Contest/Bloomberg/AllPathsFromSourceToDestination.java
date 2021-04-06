package Contest.Bloomberg;

import java.util.ArrayList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/3 16:11
 *   @Description :
 *
 */
public class AllPathsFromSourceToDestination {

    enum State{PROCESSING, PROCESSED};

    // one time traverse
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        List<Integer>[] graph = build(n, edges);
        return lead(graph, source, destination, new State[n]);
    }

    private boolean lead(List<Integer>[] graph, int s, int t, State[] states){
        if(states[s] != null) return states[s] == State.PROCESSED;
        if(graph[s].isEmpty()) return s == t;
        states[s] = State.PROCESSING;
        for(int next : graph[s]){
            if(!lead(graph, next, t, states)) return false;
        }
        states[s] = State.PROCESSED;
        return true;
    }


    private List<Integer>[] build(int n, int[][] edges){
        List<Integer>[] graph = new List[n];
        for(int i = 0; i < n; i ++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < edges.length; i ++){
            // directed graph
            graph[edges[i][0]].add(edges[i][1]);
        }
        return graph;
    }

    // All paths from source to target
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        if(graph == null || graph.length == 0 || graph[0].length == 0){
            return res;
        }
        List<Integer> cur = new ArrayList<>();
        dfs(graph, res, cur, 0, graph.length - 1);
        return res;
    }

    private void dfs(int[][] graph, List<List<Integer>> res, List<Integer> cur, int from, int to){
        if(from == to){
            cur.add(from);
            res.add(new ArrayList<>(cur));
            cur.remove(cur.size() - 1);
            return;
        }
        cur.add(from);
        for(int nei : graph[from]){
            dfs(graph, res, cur, nei, to);
        }
        cur.remove(cur.size() - 1);
    }
}
