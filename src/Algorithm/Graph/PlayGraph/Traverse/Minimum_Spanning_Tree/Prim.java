package Algorithm.Graph.PlayGraph.Traverse.Minimum_Spanning_Tree;

import Algorithm.Graph.PlayGraph.Represent.WeightedCC;
import Algorithm.Graph.PlayGraph.Represent.WeightedEdge;
import Algorithm.Graph.PlayGraph.Represent.WeightedGraph;

import java.util.ArrayList;
import java.util.PriorityQueue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/11 11:44
 *   @Description :
 *      Also called best first search
 *      Time: O(ElogE)  Space(V + E)
 */
public class Prim {

    private WeightedGraph graph;
    private ArrayList<WeightedEdge> mst;

    public Prim(WeightedGraph graph){
        this.graph = graph;
        mst = new ArrayList<>();
        WeightedCC cc = new WeightedCC(graph);
        if(cc.count() > 1) return;

        boolean[] visited = new boolean[graph.V()];
        visited[0] = true;
        PriorityQueue<WeightedEdge> queue = new PriorityQueue<>();
        for(int nei : graph.adj(0)){ // always goes from same node
            queue.add(new WeightedEdge(0, nei, graph.getWeight(0, nei)));
        }

        while(!queue.isEmpty()) {
            // Every consuming edge will visit new node
            WeightedEdge minEdge = (WeightedEdge) queue.poll();
            if(visited[minEdge.V()] && visited[minEdge.W()]){
                continue;
            }
            mst.add(minEdge);
            int newV = visited[minEdge.V()] ? minEdge.W() : minEdge.V();
            visited[newV] = true;
            for(int nei : graph.adj(newV)){
                if(!visited[nei]){
                    queue.add(new WeightedEdge(newV, nei, graph.getWeight(newV, nei)));
                }
            }
        }
    }

    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\Minimum_Spanning_Tree\\g.txt");
        Prim prim = new Prim(graph);
        System.out.println(prim.result());
    }
}
