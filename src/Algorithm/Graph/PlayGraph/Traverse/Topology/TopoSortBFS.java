package Algorithm.Graph.PlayGraph.Traverse.Topology;

import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/11 19:16
 *   @Description :
 *
 */
public class TopoSortBFS {

    private Graph graph;
    private ArrayList<Integer> res;
    private boolean hasCycle = false;

    public TopoSortBFS(Graph graph){
        if(!graph.isDirected()){
            throw new IllegalArgumentException("TopoSort only works in directed graph.");
        }
        this.graph = graph;
        res = new ArrayList<Integer>();
        int[] indegrees = new int[graph.V()];
        Queue<Integer> queue = new LinkedList<>();
        for(int v = 0; v < graph.V(); v ++){
            indegrees[v] = graph.indegree(v);
            if(indegrees[v] == 0) queue.add(v); // source ==> indegree[source] = 0;
        }

        while(!queue.isEmpty()){
            int cur = queue.poll();
            res.add(cur);
            for(int next : graph.adj(cur)){
                indegrees[next]--;
                if(indegrees[next] == 0) queue.add(next);
            }
        }

        if(res.size() != graph.V()){
            hasCycle = true;
            res.clear();
        }
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public ArrayList<Integer> result(){
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\Topology\\TopoSort.java", true);
        TopoSortBFS topoSort = new TopoSortBFS(g);
        System.out.println(topoSort.result());
    }
}
