package Algorithm.Graph.PlayGraph.Traverse.NetworkFlow.MatchProblem;

import Algorithm.Graph.PlayGraph.Represent.Graph;
import Algorithm.Graph.PlayGraph.Represent.WeightedGraph;
import Algorithm.Graph.PlayGraph.Traverse.DFS.BipartitionDetection;
import Algorithm.Graph.PlayGraph.Traverse.NetworkFlow.MaxFlow.MaxFlow;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/12 16:09
 *   @Description :
 *
 */
public class BipartiteMatching {

    private Graph G; // originally, it is a undirected problem
    private int maxMatching;

    public BipartiteMatching(Graph G){
        BipartitionDetection bd = new BipartitionDetection(G);
        if(!bd.isBipartite()) throw new IllegalArgumentException("BipartiteMatching only works for bipartite graph.");
        this.G = G;

        int[] colors = bd.colors();
        // create source and sink, labeled them as V and V + 1
        WeightedGraph network = new WeightedGraph(G.V() + 2, true);
        for(int v = 0; v < G.V(); v ++){
            if(colors[v] == 0){
                network.addEdge(G.V(), v, 1); // connects color 0 to source and provide 1 volume
            } else {
                network.addEdge(v, G.V() + 1, 1); // connects color 1 to sink and provide 1 volume
            }
            for(int w : G.adj(v)){
                if(v < w){ // color 0 picks color 1, and color 1 picks color 0. Only add once
                    if(colors[v] == 0) network.addEdge(v, w, 1);
                    else network.addEdge(w, v, 1);
                }
            }
        }
        MaxFlow maxFlow = new MaxFlow(network, G.V(), G.V() + 1);
        maxMatching = maxFlow.result();
    }

    public int maxMatching(){
        return maxMatching;
    }

    public boolean isPerfectMatching(){
        return maxMatching * 2 == G.V();
    }

    public static void main(String[] args) {
        Graph g = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\NetworkFlow\\MatchProblem\\g.txt");
        BipartiteMatching bm = new BipartiteMatching(g);
        System.out.println(bm.maxMatching());

        Graph g2 = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\NetworkFlow\\MatchProblem\\g2.txt");
        BipartiteMatching bm2 = new BipartiteMatching(g2);
        System.out.println(bm2.maxMatching());
        System.out.println("Is it perfect matching : " + bm2.isPerfectMatching());
    }

}
