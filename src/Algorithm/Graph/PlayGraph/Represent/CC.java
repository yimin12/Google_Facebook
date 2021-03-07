package Algorithm.Graph.PlayGraph.Represent;

import java.util.ArrayList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/8 19:37
 *   @Description :
 *
 */
public class CC {

    private Graph graph;
    private int[] visited;
    private int count = 0;

    public CC(Graph graph){
        this.graph = graph;
        visited = new int[graph.V()];
        for(int i = 0; i < visited.length; i++){
            visited[i] = -1;
        }
        for(int v = 0; v < graph.V(); v++){
            if(visited[v] == -1){
                dfs(v, count);
                count++;
            }
        }
    }

    private void dfs(int v, int ccid){
        visited[v] = ccid;
        for(int nei : graph.adj(v)){
            if(visited[nei] == -1){
                dfs(nei, ccid);
            }
        }
    }

    public int count(){
        return count;
    }

    public boolean isConnected(int from, int to){
        graph.validateVertex(from);
        graph.validateVertex(to);
        return visited[from] == visited[to];
    }

    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[count];
        for(int i = 0; i < count; i++){
            res[i] = new ArrayList<Integer>();
        }
        for(int v = 0; v < graph.V(); v++){
            res[visited[v]].add(v);
        }
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("resources\\g.txt");
        CC cc = new CC(graph);
        System.out.println(cc.count());

        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(5, 6));

        ArrayList<Integer>[] comp = cc.components();
        for(int i = 0; i < comp.length; i++){
            System.out.print(i + " : ");
            for(int w : comp[i]){
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }
}

