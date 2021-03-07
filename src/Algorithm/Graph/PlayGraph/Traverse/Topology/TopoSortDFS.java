package Algorithm.Graph.PlayGraph.Traverse.Topology;

import Algorithm.Graph.PlayGraph.Represent.Graph;
import Algorithm.Graph.PlayGraph.Traverse.DirectedGraphAlgorithm.DirectedCycleDetection;
import Algorithm.Graph.PlayGraph.Traverse.DirectedGraphAlgorithm.GraphDFS;

import java.util.ArrayList;
import java.util.Collections;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/11 20:20
 *   @Description :
 *      dfs gives one of multiple valid path
 *      bfd traverse it with greedy thoughts
 */
public class TopoSortDFS {

    private Graph G;
    private ArrayList<Integer> res;
    private boolean hasCycle = false;

    public TopoSortDFS(Graph G){
        if(!G.isDirected())
            throw new IllegalArgumentException("DirectedCycleDetection only works in directed graph.");
        this.G = G;
        res = new ArrayList<>();
        hasCycle = (new DirectedCycleDetection(G)).hasCycle();
        if(hasCycle) return;
        GraphDFS dfs = new GraphDFS(G);
        for(int v : dfs.post()){
            res.add(v);
        }
        Collections.reverse(res);
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public ArrayList<Integer> result(){
        return res;
    }

    public static void main(String[] args){

        Graph ug = new Graph("ug.txt", true);
        TopoSortDFS topoSort = new TopoSortDFS(ug);
        System.out.println(topoSort.result());
    }
}
