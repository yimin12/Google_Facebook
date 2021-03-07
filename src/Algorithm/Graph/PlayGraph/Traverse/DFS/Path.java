package Algorithm.Graph.PlayGraph.Traverse.DFS;

import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.ArrayList;
import java.util.Collections;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/9 12:30
 *   @Description :
 *
 */
public class Path {

    private Graph graph;
    private int from, to;
    private int[] pre;
    private boolean[] visited;

    public Path(Graph graph, int from, int to){
        graph.validateVertex(from);
        graph.validateVertex(to);

        this.graph = graph;
        this.from = from;
        this.to = to;

        visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        for(int i = 0; i < pre.length; i++){
            pre[i] = -1;
        }
        dfs(from, from);
        for(boolean flag:visited){
            System.out.print(flag + " ");
        }
        System.out.println();
    }

    private boolean dfs(int node, int parent){
        visited[node] = true;
        pre[node] = parent;
        if(node == to){
            return true;
        }
        for(int nei : graph.adj(node)){
            if(!visited[nei]){
                if(dfs(nei, node)) return true;
            }
        }
        return false;
    }

    public boolean isConnected(){
        return visited[to];
    }

    public Iterable<Integer> path(){
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(!isConnected()) return res;
        int cur = to;
        while(cur != from){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(from);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("resources\\g.txt");
        Path path = new Path(graph, 0, 6);
        System.out.println("0 -> 6 : " + path.path());

        Path path2 = new Path(graph, 1, 5);
        System.out.println("1 -> 5 : " + path2.path());

        Path path3 = new Path(graph, 2, 4);
        System.out.println("2 -> 4 : " + path3.path());
    }
}
