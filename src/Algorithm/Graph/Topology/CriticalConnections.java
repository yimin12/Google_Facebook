package Algorithm.Graph.Topology;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/24 23:06
 *   @Description :
 *       There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b]
 *       represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.
 *       A critical connection is a connection that, if removed, will make some server unable to reach some other server. Return all critical connections in the network in any order.
 *
 */
public class CriticalConnections {
    // good question to remove inner cycle
    private int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // prepare and construct the graph
        for(List<Integer> conn : connections){
            int u = conn.get(0);
            int v = conn.get(1);
            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
            graph.get(n).add(v);
            graph.get(v).add(u);
        }
        int[] times = new int[n]; // discovery time of each node
        int[] entry = new int[n]; // earliest discovered node reachable from this node in DFS
        boolean[] visited = new boolean[n]; // whether this node has been visited in DFS
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, -1,  times, entry, visited, graph, res); // arbitrarily pick a node to start DFS
        return res;
    }
    private void dfs(int root, int parent, int[] times, int[] entry, boolean[] visited, Map<Integer, List<Integer>> graph, List<List<Integer>> res){
        visited[root] = true;
        times[root] = time++;
        entry[root] = times[root];
        List<Integer> neighbors = graph.get(root);
        // base case
        if(neighbors== null){
            return;
        }
        for(Integer nei:neighbors){
            // undirected edges
            if(nei == parent){
                continue;
            }
            if(!visited[nei]){
                dfs(nei, root, times, entry, visited, graph, res);
                entry[root] = Math.min(entry[root], entry[nei]);
                if(times[root] < entry[nei]){
                    res.add(Arrays.asList(root, nei));
                }
            } else {
                entry[root] = Math.min(entry[root], times[nei]);
            }
        }

    }

}
