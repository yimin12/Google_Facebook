package Algorithm.Graph.PlayGraph.Traverse.DFS;

import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.ArrayList;
import java.util.Collections;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/9 12:02
 *   @Description :
 *
 */
public class SingleSourcePath {

    private Graph graph;
    private int s;
    private boolean[] visited;
    private int[] pre;
    private int[] post;

    public SingleSourcePath(Graph graph, int s){
        graph.validateVertex(s);
        this.graph = graph;
        this.s = s;
        visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        post = new int[graph.V()];
        dfs(s, s); // from source to source
    }

    private void dfs(int node , int parent){
        visited[node] = true;
        pre[node] = parent;
        for(int nei : graph.adj(node)){
            if(!visited[nei]){
                dfs(nei, node);
            }
        }
        post[node] = parent;
    }

    /**
     * start from source, whether it is connected to node t
     * @param t
     * @return
     */
    public boolean isConnectedTo(int t){
        graph.validateVertex(t);
        return visited[t];
    }

    /**
     * find the path start from source to t
     * @param t
     * @return
     */
    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(!isConnectedTo(t)){
            return res;
        }
        int cur = t;
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("resources\\g.txt");
        SingleSourcePath source = new SingleSourcePath(graph, 0); // start from 0
        System.out.println("0 -> 6 : " + source.path(6));
        System.out.println("0 -> 5 : " + source.path(5));
    }
}
