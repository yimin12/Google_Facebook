package Algorithm.Graph.PlayGraph.Traverse.DFS;

import Algorithm.Graph.PlayGraph.Represent.Graph;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/9 13:42
 *   @Description :
 *
 */
public class BipartitionDetection {

    private Graph graph;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite; // two group : 0 and 1

    public BipartitionDetection(Graph graph){
        this.graph = graph;
        visited = new boolean[graph.V()];
        colors = new int[graph.V()];
        isBipartite = true;
        for(int i = 0; i < graph.V(); i++) colors[i] = -1;
        for(int v = 0; v < graph.V(); v++){
            if(!visited[v]){
                if(!dfs(v, 0)){
                    isBipartite = false;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int color){
        visited[v] = true;
        colors[v] = color;
        for(int nei : graph.adj(v)){
            if(!visited[nei]){
                if(!dfs(nei, 1 - color)) return false;
            } else if(colors[nei] == colors[v]) return false;
        }
        return true;
    }

    public boolean isBipartite(){
        return isBipartite;
    }

    public int[] colors(){ return colors;}

    public static void main(String[] args) {

        Graph g = new Graph("resources\\g.txt");
        BipartitionDetection bipartitionDetection  = new BipartitionDetection(g);
        System.out.println(bipartitionDetection .isBipartite());

        Graph g2 = new Graph("resources\\g2.txt");
        BipartitionDetection bipartitionDetection2 = new BipartitionDetection(g2);
        System.out.println(bipartitionDetection2.isBipartite());

        Graph g3 = new Graph("resources\\bipartition.txt");
        BipartitionDetection bipartitionDetection3 = new BipartitionDetection(g3);
        System.out.println(bipartitionDetection3.isBipartite());
    }
}
