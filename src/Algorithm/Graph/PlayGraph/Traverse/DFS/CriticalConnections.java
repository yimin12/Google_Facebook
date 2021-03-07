package Algorithm.Graph.PlayGraph.Traverse.DFS;

import java.util.ArrayList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/10 15:39
 *   @Description :
 *      Leetcode 1192. Critical Connections in a Network
 */
public class CriticalConnections {

    private class Graph{
        private int V;
        private int E;
        private ArrayList<Integer>[] adj; // or use adjacent set

        public Graph(int V){
            this.V = V;
            if(V < 0) throw new IllegalArgumentException("V must be non-negative");
            adj = new ArrayList[V];
            for(int i = 0; i < V; i ++)
                adj[i] = new ArrayList<Integer>();
            E = 0; // init edge from 0
        }

        public void addEdge(int v, int w){
            validateVertex(v);
            validateVertex(w);
            if(adj[v].contains(w)) return;
            adj[v].add(w);
            adj[w].add(v);
            E++; // label the edge between v and w
        }

        public void validateVertex(int v){
            if(v < 0 || v >= V)
                throw new IllegalArgumentException("vertex " + v + "is invalid");
        }

        public int V(){
            return V;
        }

        public int E(){
            return E;
        }

        public Iterable<Integer> adj(int v){
            validateVertex(v);
            return adj[v];
        }
    }

    private class FindBridges{
        private Graph G;
        private boolean visited[];
        private int ord[];
        private int low[];
        private int cnt;
        private List<List<Integer>> res;

        public FindBridges(Graph graph){
            G = graph;
            visited = new boolean[graph.V()];
            res = new ArrayList<>();
            ord = new int[graph.V()];
            low = new int[graph.V()];
            cnt = 0;
            for(int v = 0; v < graph.V(); v ++){
                if(!visited[v]){
                    dfs(v, v);
                }
            }
        }

        private void dfs(int v, int parent){
            visited[v] = true;
            ord[v] = cnt;
            low[v] = ord[v];
            cnt++;

            for(int nei: G.adj(v)){
                if(!visited[nei]){
                    dfs(nei, v);
                    low[v] = Math.min(low[v], low[nei]);
                    if(low[nei] > ord[v]){
                        ArrayList<Integer> edge = new ArrayList<>();
                        edge.add(v);
                        edge.add(nei);
                        res.add(edge);
                    }
                } else if(nei != parent){
                    low[v] = Math.min(low[v], low[nei]);
                }
            }
        }

        public List<List<Integer>> result(){
            return res;
        }
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections){
        Graph g = new Graph(n);
        for(List<Integer> edge:connections){
            g.addEdge(edge.get(0), edge.get(1));
        }
        FindBridges fb = new FindBridges(g);
        return fb.result();
    }

}
