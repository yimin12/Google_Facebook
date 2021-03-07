package Algorithm.Graph.PlayGraph.Traverse.Minimum_Spanning_Tree;

import Algorithm.Graph.PlayGraph.Represent.UF;
import Algorithm.Graph.PlayGraph.Represent.WeightedCC;
import Algorithm.Graph.PlayGraph.Represent.WeightedEdge;
import Algorithm.Graph.PlayGraph.Represent.WeightedGraph;

import java.util.ArrayList;
import java.util.Collections;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/10 22:36
 *   @Description :
 *
 */
public class Kruskal {

    private WeightedGraph graph;
    private ArrayList<WeightedEdge> mst; // minimum spanning tree

    public Kruskal(WeightedGraph graph){
        this.graph = graph;
        mst = new ArrayList<>();
        WeightedCC cc = new WeightedCC(graph);
        if(cc.count() > 1) return; // mst should not contain isolation

        ArrayList<WeightedEdge> edges = new ArrayList<>();
        for(int v = 0; v < graph.V(); v ++){
            for(int nei : graph.adj(v)){
                if(v < nei){
                    // get every edge, v < nei avoids adding the same edge twice
                    edges.add(new WeightedEdge(v, nei, graph.getWeight(v, nei)));
                }
            }
        }
        Collections.sort(edges); // can use priority_queue(min heap) here as well
        // kruskal
        UF uf = new UF(graph.V());
        for(WeightedEdge edge:edges){
            int v = edge.V();
            int w = edge.W();
            if(!uf.isConnected(v, w)){
                mst.add(edge);
                uf.union(v, w);
            }
        }
    }

    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\Minimum_Spanning_Tree\\g.txt");
        Kruskal kruskal = new Kruskal(graph);
        System.out.println(kruskal.result());
    }
}
