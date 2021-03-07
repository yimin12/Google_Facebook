package Algorithm.Graph.PlayGraph.Traverse.DirectedGraphAlgorithm;

import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/12 12:41
 *   @Description :
 *
 */
public class StrongConnectedComponent {

    private Graph G;
    private int[] visited;
    private int scccount = 0;

    public StrongConnectedComponent(Graph g) {
        if(!g.isDirected()) throw new IllegalArgumentException("SCC only works in directed graph");
        G = g;
        visited = new int[G.V()];
        Arrays.fill(visited, -1);
        GraphDFS dfs = new GraphDFS(G.reverseGraph());
        ArrayList<Integer> order = new ArrayList<Integer>();

        for(int v : dfs.post()) order.add(v);

        Collections.reverse(order);
        for(int v : order){
            if(visited[v] == -1){
                dfs(v, scccount);
                scccount ++;
            }
        }
    }

    private void dfs(int v, int id){
        visited[v] = id;
        for(int nei : G.adj(v)){
            if(visited[nei] == -1) dfs(nei, id);
        }
    }

    public int count(){
        return scccount;
    }

    public boolean isStronglyConnected(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[scccount];
        for(int i = 0; i < scccount; i ++)
            res[i] = new ArrayList<Integer>();
        for(int v = 0; v < G.V(); v ++)
            res[visited[v]].add(v);
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\DirectedGraphAlgorithm\\ug.txt", true);
        StrongConnectedComponent scc = new StrongConnectedComponent(g);
        System.out.println(scc.count());

        ArrayList<Integer>[] comp = scc.components();
        for(int sccid = 0; sccid < comp.length; sccid ++){
            System.out.print(sccid + " : ");
            for(int w: comp[sccid])
                System.out.print(w + " ");
            System.out.println();
        }

//        Graph g2 = new Graph("ug2.txt", true);
//        SCC scc2 = new SCC(g2);
//        System.out.println(scc2.count());
//        // 3
//
//        Graph g3 = new Graph("ug3.txt", true);
//        SCC scc3 = new SCC(g3);
//        System.out.println(scc3.count());
//        // 1
    }
}
