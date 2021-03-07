package Algorithm.Graph.PlayGraph.Traverse.ShortestPath;

import Algorithm.Graph.PlayGraph.Represent.WeightedGraph;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/11 15:59
 *   @Description :
 *      Time(V * V * V), similar as 2D dynamic programming
 */
public class Floyed {

    private WeightedGraph graph;
    private int[][] dis; // record all shortest path of point to point
    private boolean hasNegCycle = false;

    public Floyed(WeightedGraph graph){
        this.graph = graph;
        dis = new int[graph.V()][graph.V()];

        for(int v = 0; v < graph.V(); v ++){
            Arrays.fill(dis[v], Integer.MAX_VALUE);
        }

        for(int v = 0; v < graph.V(); v ++){
            dis[v][v] = 0;
            for(int nei : graph.adj(v)){
                dis[v][nei] = graph.getWeight(v, nei);
            }
        }

        for(int t = 0; t < graph.V(); t ++){
            for(int v = 0; v < graph.V(); v ++){
                for(int w = 0; w < graph.V(); w++){
                    if(dis[v][t] != Integer.MAX_VALUE && dis[t][w] != Integer.MAX_VALUE && dis[v][t] + dis[t][w] < dis[v][w]){
                        dis[v][w] = dis[v][t] + dis[t][w];
                    }
                }
            }
        }

        for(int v = 0; v < graph.V(); v ++){
            if(dis[v][v] < 0) hasNegCycle = true;
        }
    }

    public boolean hasNegativeCycle(){
        return hasNegCycle;
    }

    public boolean isConnectedTo(int v, int w){
        graph.validateVertex(v);
        graph.validateVertex(w);
        return dis[v][w] != Integer.MAX_VALUE;
    }

    public int distance(int v,int w){
        graph.validateVertex(v);
        graph.validateVertex(w);
        return dis[v][w];
    }

    static public void main(String[] args){

        WeightedGraph g = new WeightedGraph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\ShortestPath\\g.txt");
        Floyed floyed = new Floyed(g);
        if(!floyed.hasNegativeCycle()){
            for(int v = 0; v < g.V(); v ++){
                for(int w = 0; w < g.V(); w ++)
                    System.out.print(floyed.distance(v, w) + " ");
                System.out.println();
            }
        }
        else
            System.out.println("exist negative cycle.");

        WeightedGraph g2 = new WeightedGraph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\ShortestPath\\g2.txt");
        Floyed floyed2 = new Floyed(g2);
        if(!floyed2.hasNegativeCycle()){
            for(int v = 0; v < g.V(); v ++){
                for(int w = 0; w < g.V(); w ++)
                    System.out.print(floyed2.distance(v, w) + " ");
                System.out.println();
            }
        }
        else
            System.out.println("exist negative cycle.");
    }
}
