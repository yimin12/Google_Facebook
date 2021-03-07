package Algorithm.Graph.PlayGraph.Traverse.NetworkFlow.MatchProblem;

import Algorithm.Graph.PlayGraph.Represent.Graph;
import Algorithm.Graph.PlayGraph.Traverse.DFS.BipartitionDetection;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/12 20:34
 *   @Description :
 *
 */
public class HungarianDFS {

    private Graph graph;
    private int maxMatching;
    private int[] matching;
    private boolean visited[];

    public HungarianDFS(Graph g){
        BipartitionDetection bd = new BipartitionDetection(g);
        if(!bd.isBipartite())
            throw new IllegalArgumentException("BipartiteMatching only works for bipartite graph.");
        this.graph = g;
        int[] colors = bd.colors();
        matching = new int[graph.V()];
        Arrays.fill(matching, -1);
        visited = new boolean[graph.V()];
        for(int v = 0; v < graph.V(); v ++){
            if(colors[v] == 0 && matching[v] == -1){
                Arrays.fill(visited, false);
                if(dfs(v)) maxMatching ++;
            }
        }
    }

    private boolean dfs(int v){
        visited[v] = true;
        for(int nei : graph.adj(v)){
            if(!visited[nei]){
                visited[nei] = true;
                if(matching[nei] == -1 || dfs(matching[nei])){
                    matching[nei] = v;
                    matching[v] = nei;
                    return true;
                }
            }
        }
        return false;
    }

    public int maxMatching(){
        return maxMatching;
    }

    public boolean isPerfectMatching(){
        return maxMatching * 2 == graph.V();
    }

    public static void main(String[] args){
        Graph g = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\NetworkFlow\\MatchProblem\\g.txt");
        HungarianDFS hungarian = new HungarianDFS(g);
        System.out.println(hungarian.maxMatching());
        Graph g2 = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\NetworkFlow\\MatchProblem\\g2.txt");
        HungarianDFS hungarian2 = new HungarianDFS(g2);
        System.out.println(hungarian2.maxMatching());
    }
}
