package Algorithm.Graph.PlayGraph.Traverse.BFS;

import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/9 16:25
 *   @Description :
 *
 */
public class UnweightedSingleSourcePath {

    private Graph graph;
    private int source;
    private boolean[] visited;
    private int[] pre;
    private int[] dis; // distance;

    public UnweightedSingleSourcePath(Graph graph, int source) {
        this.graph = graph;
        this.source = source;

        visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        dis = new int[graph.V()];

        for(int v = 0; v < graph.V(); v++){
            pre[v] = dis[v] = -1;
        }
        bfs(source);
        // print the result
        for(int i = 0; i < graph.V(); i++){
            System.out.print(dis[i] + " ");
        }
        System.out.println();
    }

    private void bfs(int source){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(source);
        visited[source] = true;
        pre[source] = source; // root : point to itself
        dis[source] = 0;
        while(!queue.isEmpty()){
            int cur = queue.remove();
            for(int nei:graph.adj(cur)){
                if(!visited[nei]){
                    queue.add(nei);
                    visited[nei] = true;
                    pre[nei] = cur;
                    dis[nei] = dis[cur] + 1;
                }
            }
        }
    }

    public boolean isConnectedTo(int to){
        graph.validateVertex(to);
        return visited[to];
    }

    public int distance(int to){
        graph.validateVertex(to);
        return dis[to];
    }

    public Iterable<Integer> path(int to){
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(!isConnectedTo(to)) return res;
        int cur = to;
        while(cur != source){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("resources\\g.txt");
        UnweightedSingleSourcePath us = new UnweightedSingleSourcePath(graph, 0);
        System.out.println("0 -> 6 : " + us.path(6));
        System.out.println("0 -> 6 : " + us.distance(6));
    }
}
