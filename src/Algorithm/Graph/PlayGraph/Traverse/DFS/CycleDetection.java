package Algorithm.Graph.PlayGraph.Traverse.DFS;

import Algorithm.Graph.PlayGraph.Represent.Graph;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/9 12:52
 *   @Description :
 *
 */
public class CycleDetection {

    private Graph graph;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph graph){
        this.graph = graph;
        visited = new boolean[graph.V()];
        for(int v = 0; v < graph.V(); v++){
            if(!visited[v]){
                if(dfs(v, v)){ // if it can start from source and end in the same node
                   hasCycle = true;
                   break;
                }
            }
        }
    }

    private boolean dfs(int v, int parent){
        visited[v] = true;
        for(int nei : graph.adj(v)){
            if(!visited[nei]){
                if(dfs(nei, v)) return true; // source to source
            } else if(nei != parent) return true; // inner cycle
        }
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new Graph("resources\\g.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle());

        Graph g2 = new Graph("resources\\g2.txt");
        CycleDetection cycleDetection2 = new CycleDetection(g2);
        System.out.println(cycleDetection2.hasCycle());
    }
}
