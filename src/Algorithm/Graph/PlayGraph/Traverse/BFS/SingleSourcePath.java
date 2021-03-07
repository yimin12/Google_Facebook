package Algorithm.Graph.PlayGraph.Traverse.BFS;

import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/9 16:16
 *   @Description :
 *
 */
public class SingleSourcePath {

    private Graph graph;
    private int source;
    private boolean[] visited;
    private int[] pre;

    public SingleSourcePath(Graph graph, int source){
        this.graph = graph;
        this.source = source;
        visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        for(int i = 0; i < pre.length; i++){
            pre[i] = -1;
        }
        bfs(source);
    }

    private void bfs(int source){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(source);
        visited[source] = true;
        pre[source] = source;
        while(!queue.isEmpty()){
            int cur = queue.remove();
            for(int nei: graph.adj(cur)){
                if(!visited[nei]){
                    queue.add(nei);
                    visited[nei] = true;
                    pre[nei] = cur;
                }
            }
        }
    }

    public boolean isConnectedTo(int to){
        graph.validateVertex(to);
        return visited[to];
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

    public static void main(String[] args){

        Graph graph = new Graph("resources\\g.txt");
        SingleSourcePath sspath = new SingleSourcePath(graph, 0);
        System.out.println("0 -> 6 : " + sspath.path(6));
    }
}
