package Algorithm.Graph.Tree.Basic;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/21 23:38
 * Description:
 * 	Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 * 	You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 * Example:
 * 	Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * 	Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 * Depth First Search Facebook Zenefits Union Find Breadth First Search Google
 *
 */
public class IsValidTree {

    // dfs1: do not recommend
    // Method 1: DFS Solution and convert matrix to adjacent list
    // Let E be the number of edges and N be the number of nodes
    // Time : O(N + E) Creating the adjacency list requires initialising a list of length NN, with a cost of O(N)O(N), and then iterating over and inserting EE edges, for a cost of O(E)O(E). This gives us O(E) + O(N) = O(N + E)O(E)+O(N)=O(N+E)
    // Each node is added to the data structure once. This means that the outer loop will run NN times. For each of the NN nodes, its adjacent edges is iterated over once. In total, this means that all EE edges are iterated over once by the inner loop. This, therefore, gives a total time complexity of O(N + E)O(N+E)
    // Space Complexity : O(N + E)
    public boolean validTreeDFS(int n, int[][] edges){
        List<List<Integer>> adjList = new ArrayList<>();
        // construct map
        for(int i = 0; i < n; i++){
            adjList.add(i, new ArrayList<>());
        }
        // add the edge of undirected vertices
        for(int i = 0; i < edges.length; i++){
            int u = edges[i][0], v = edges[i][1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        // visited of bfs
        boolean[] visited = new boolean[n];
        if(hasCycle(adjList, 0, visited, -1)) return false;
        // do not need to check every entry
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                return false; // can not have isolate island
            }
        }
        return true;
    }
    // dfs to check hasCycle
    private boolean hasCycle(List<List<Integer>> adj, int u, boolean[] visited, int parent){
        visited[u] = true;
        // recursion rule
        for(int i = 0; i < adj.get(u).size(); i++){
            int v = adj.get(u).get(i);
            // condition 1: undirected edges, it can go a-b and b-a
            if((!visited[v] && parent != v) || (!visited[v] && hasCycle(adj, v, visited, u))){
                return true;
            };
        }
        return false;
    }
    // dfs2 to check hasCycle
    private boolean hasCycleI(List<List<Integer>> adj, int u, boolean[] visited, int parent){
        // base case
        if(visited[u]){
            return true;
        }
        // recursion rule
        for(int v : adj.get(u)){
            if(v != parent && hasCycleI(adj, v, visited, u)){
                return true;
            }
        }
        return false;
    }

    // Method 2: BFS (Complexity are the same as dfs)
    public boolean validTreeBFS(int n, int[][] edges){
        List<Set<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new HashSet<>());
        }
        // construct the map (undirected)
        for(int i = 0; i < edges.length; i++){
            int u = edges[i][0], v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        while(!queue.isEmpty()){
            int node = queue.poll();
            if(visited[node]){
                return false;
            }
            for(int nei : graph.get(node)){
                queue.offer(nei);
                graph.get(nei).remove((Integer)node); // undirected graph, no need to traverse twice
            }
        }
        // find every entry
        for(boolean result : visited){
            if(!result){
                return false;
            }
        }
        return true;
    }

    // Method 3: Union Find
    // Time : O(N + E), Space: O(N)
    public boolean validTreeUnionFind(int n, int[][] edges){
        if(edges.length != n-1) return false;
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        for(int[] edge : edges){
            int x = find(edge[0], parent);
            int y = find(edge[1], parent);
            if(x == y) return false;
            parent[x] = y;
        }
        return true;
    }
    // compress path, amortized O(1)
    private int find(int node, int[] parent){
        if(parent[node] == -1){
            return node;
        }
        // the ancestor must by parent[0] (root)
        return parent[node] = find(parent[0], parent);
    }

}
