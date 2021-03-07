package Algorithm.Graph.PlayGraph.Traverse.DFS;

import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.ArrayList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/8 17:33
 *   @Description :
 *
 */
public class GraphDFS {

    private Graph graph;
    private boolean[] visited;
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();

    public GraphDFS(Graph graph){
        this.graph = graph;
        visited = new boolean[graph.V()];
        for(int i = 0; i < graph.V() ; i++){
            if(!visited[i]){
                dfs(i);
            }
        }
    }

    private void dfs(int node){
        visited[node] = true;
        pre.add(node);
        for(int nei: graph.adj(node)){
            if(!visited[nei]){
                dfs(nei);
            }
        }
        post.add(node);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public static void main(String[] args) {
        Graph g = new Graph("resources\\g.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println(graphDFS.pre());
        System.out.println(graphDFS.post());

    }
}
