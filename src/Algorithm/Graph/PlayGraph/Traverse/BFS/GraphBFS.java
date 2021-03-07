package Algorithm.Graph.PlayGraph.Traverse.BFS;

import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/9 16:00
 *   @Description :
 *
 */
public class GraphBFS {

    private Graph graph;
    private boolean[] visited;
    private ArrayList<Integer> order = new ArrayList<Integer>();

    public GraphBFS(Graph graph){
        this.graph = graph;
        visited = new boolean[graph.V()];
        for(int v = 0; v < graph.V(); v++){
            if(!visited[v]){
                bfs(v);
            }
        }
    }

    private void bfs(int source){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(source);
        visited[source] = true;
        while(!queue.isEmpty()){
            int cur = queue.remove();
            order.add(cur);
            for(int nei : graph.adj(cur)){
                if(!visited[nei]){
                    queue.add(nei);
                    visited[nei] = true; // mark true when you put it into queue
                }
            }
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("resources\\g.txt");
        GraphBFS graphBFS = new GraphBFS(graph);
        System.out.println("BFS Order : " + graphBFS.order());
    }
}
