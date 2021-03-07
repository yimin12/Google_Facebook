package Algorithm.Graph.PlayGraph.Traverse.DirectedGraphAlgorithm;

import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.ArrayList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/11 16:56
 *   @Description :
 *      Directed graph
 */
public class GraphDFS {

    private Graph graph;
    private boolean[] visited;
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();

    public GraphDFS(Graph graph){
        this.graph = graph;
        visited = new boolean[graph.V()];
        for(int v = 0; v < graph.V(); v ++){
            if(!visited[v]) dfs(v);
        }
    }

    private void dfs(int v){
        visited[v] = true;
        pre.add(v);
        for(int nei : graph.adj(v)){
            if(!visited[nei]) dfs(nei);
        }
        post.add(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public static void main(String[] args) {
        Graph g = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\DirectedGraphAlgorithm\\ug.txt", true);
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println("DFS preOrder : " + graphDFS.pre());
        System.out.println("DFS postOrder : " + graphDFS.post());
    }
}
