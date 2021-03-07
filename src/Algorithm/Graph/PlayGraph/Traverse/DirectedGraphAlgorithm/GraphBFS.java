package Algorithm.Graph.PlayGraph.Traverse.DirectedGraphAlgorithm;

import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/11 17:47
 *   @Description :
 *
 */
public class GraphBFS {

    private Graph graph;
    private boolean[] visited;
    private ArrayList<Integer> order = new ArrayList<>();

    public GraphBFS(Graph G){
        this.graph = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                bfs(v);
    }

    private void bfs(int v){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            order.add(v);
            for(int nei : graph.adj(cur)){
                if(!visited[nei]){
                    queue.add(nei);
                    visited[nei] = true;
                }
            }
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public static void main(String[] args){
        Graph g = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\DirectedGraphAlgorithm\\ug.txt", true);
        GraphBFS graphBFS = new GraphBFS(g);
        System.out.println("BFS Order : " + graphBFS.order());
    }
}
