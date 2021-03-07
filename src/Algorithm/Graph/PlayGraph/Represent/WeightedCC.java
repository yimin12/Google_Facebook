package Algorithm.Graph.PlayGraph.Represent;

import java.util.ArrayList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/10 22:39
 *   @Description :
 *
 */
public class WeightedCC {

    private WeightedGraph graph;
    private int[] visited;
    private int count;

    public WeightedCC(WeightedGraph graph) {
        this.graph = graph;
        visited = new int[graph.V()];
        for(int i = 0; i < graph.V(); i++){
            visited[i] = -1;
        }
        for(int v = 0; v < graph.V(); v ++){
            if(visited[v] == -1){
                dfs(v, count);
                count++;
            }
        }
    }

    private void dfs(int v, int id){
        visited[v] = id;
        for(int nei : graph.adj(v)){
            if(visited[nei] == -1){
                dfs(nei, id);
            }
        }
    }

    public int count(){
        return count;
    }

    public boolean isConnected(int v, int w){
        graph.validateVertex(v);
        graph.validateVertex(w);
        return visited[v] == visited[w];
    }

    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[count];
        for(int i = 0; i < count; i++){
            res[i] = new ArrayList<Integer>();
        }
        for(int v = 0; v < graph.V(); v ++){
            res[visited[v]].add(v);
        }
        return res;
    }

    public static void main(String[] args){
        WeightedGraph g = new WeightedGraph("resources\\g3.txt");
        WeightedCC cc = new WeightedCC(g);
        System.out.println(cc.count());

        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(5, 6));

        ArrayList<Integer>[] comp = cc.components();
        for(int ccid = 0; ccid < comp.length; ccid ++){
            System.out.print(ccid + " : ");
            for(int w: comp[ccid])
                System.out.print(w + " ");
            System.out.println();
        }
    }
}
