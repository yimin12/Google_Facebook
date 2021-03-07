package Algorithm.Graph.PlayGraph.Traverse.DFS;

import Algorithm.Graph.PlayGraph.Represent.Edge;
import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.ArrayList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/10 14:32
 *   @Description :
 *
 */
public class FindBridges {

    private Graph graph;
    private boolean[] visited;
    private int[] ord; // record the sequence you do the dfs traverse
    private int[] low; // lowest parent get by backtracking
    private int count; // record the current sequence

    private ArrayList<Edge> res;

    public FindBridges(Graph graph){
        this.graph = graph;
        visited = new boolean[graph.V()];
        res = new ArrayList<>();
        ord = new int[graph.V()];
        low = new int[graph.V()];
        count = 0;
        for(int v = 0; v < graph.V(); v ++){
            if(!visited[v]){
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent){
        visited[v] = true;
        ord[v] = count;
        low[v] = ord[v]; // diving
        count++;

        for(int nei : graph.adj(v)){
            if(!visited[nei]){
                dfs(nei, v);
                // maintain ord, low, count, in backtracking step
                low[v] = Math.min(low[v], low[nei]);
                if(low[nei] > ord[v]){
                    res.add(new Edge(v,nei));
                }
            } else if(nei != parent){
                low[v] = Math.min(low[v], low[nei]); // if there is a cycle, just return and set the low
            }
        }
    }

    public ArrayList<Edge> result(){
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("resources\\g.txt");
        FindBridges fb = new FindBridges(g);
        System.out.println("Bridges in g : " + fb.result());

        Graph g2 = new Graph("resources\\g2.txt");
        FindBridges fb2 = new FindBridges(g2);
        System.out.println("Bridges in g2 : " + fb2.result());

        Graph g3 = new Graph("resources\\bipartition.txt");
        FindBridges fb3 = new FindBridges(g3);
        System.out.println("Bridges in g3 : " + fb3.result());

        Graph tree = new Graph("resources\\tree.txt");
        FindBridges fb_tree = new FindBridges(tree);
        System.out.println("Bridges in tree : " + fb_tree.result());
    }
}
