package Algorithm.Graph.PlayGraph.Traverse.DFS;

import Algorithm.Graph.PlayGraph.Represent.Graph;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/8 19:17
 *   @Description :
 *      How many isolated connected component
 */
public class ConnectedComponent {

    private Graph graph;
    private boolean[] visited;
    private int[] memory;
    private int count;

    public ConnectedComponent(Graph graph){
        this.graph = graph;
        visited = new boolean[graph.V()];
        memory = new int[graph.V()];
        for(int i = 0; i < memory.length; i++){
            memory[i] = -1;
        }
        for(int v = 0; v < graph.V(); v++){
            if(!visited[v]){
                dfs(v, count);
                count++;
            }
        }
    }

    private void dfs(int v, int count){
        visited[v] = true;
        memory[v] = count;
        for(int nei : graph.adj(v)){
            if(!visited[nei]){
                dfs(nei, count);
            }
        }
    }

    public int count(){
        for(int e: memory)
            System.out.print(e + " ");
        System.out.println();
        return count;
    }

    public static void main(String[] args) {
        Graph g = new Graph("resources\\g.txt");
        ConnectedComponent cc = new ConnectedComponent(g);
        System.out.println(cc.count());
    }
}
