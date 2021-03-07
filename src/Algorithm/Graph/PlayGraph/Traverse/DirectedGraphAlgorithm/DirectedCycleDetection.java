package Algorithm.Graph.PlayGraph.Traverse.DirectedGraphAlgorithm;

import Algorithm.Graph.PlayGraph.Represent.Graph;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/11 18:09
 *   @Description :
 *
 */
public class DirectedCycleDetection {

    private Graph graph;
    private boolean[] visited;
    private boolean[] onPath;
    private boolean hasCycle = false;

    public DirectedCycleDetection(Graph graph){
        if(!graph.isDirected()){
            throw new IllegalArgumentException("DirectedCycleDetection only works in directed graph.");
        }
        this.graph = graph;
        visited = new boolean[graph.V()];
        onPath = new boolean[graph.V()];
        for(int v = 0; v < graph.V(); v ++){
            if(!visited[v]){
                if(dfs(v)){
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    // detect the cycle startfrom v
    private boolean dfs(int v){
        visited[v] = true;
        onPath[v] = true;
        for(int nei : graph.adj(v)){
            if(!visited[nei]){
                if(dfs(nei)) return true;
            } else if(onPath[nei]) return true;
        }
        onPath[v] = false;
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args){
        Graph g = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\DirectedGraphAlgorithm\\ug.txt", true);
        DirectedCycleDetection cycleDetection = new DirectedCycleDetection(g);
        System.out.println(cycleDetection.hasCycle());

        Graph g2 = new Graph("ug2.txt", true);
        DirectedCycleDetection cycleDetection2 = new DirectedCycleDetection(g2);
        System.out.println(cycleDetection2.hasCycle());
    }
}
