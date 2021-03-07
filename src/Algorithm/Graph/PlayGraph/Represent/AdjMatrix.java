package Algorithm.Graph.PlayGraph.Represent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/8 17:00
 *   @Description :
 *
 */
public class AdjMatrix {

    private int V;
    private int E;
    private int[][] adj;

    public AdjMatrix(String path){

        File file = new File(path);
        try(Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if(V < 0) throw new IllegalArgumentException("V must be non-negative");
            adj = new int[V][V];

            E = scanner.nextInt();
            if(E < 0) throw new IllegalArgumentException("E must be non-negative");

            // construct the graph
            for(int i = 0; i < E; i++){
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);

                if(a == b) throw new IllegalArgumentException("Self Loop is Detected!");
                if(adj[a][b] == 1) throw new IllegalArgumentException("Parallel Edges are Detected!");

                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void validateVertex(int vertex){
        if(vertex < 0 || vertex >= V)
            throw new IllegalArgumentException("vertex " + vertex + "is invalid");
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public boolean hasEdge(int from, int to){
        validateVertex(from);
        validateVertex(to);
        return adj[from][to] == 1;
    }

    public ArrayList<Integer> adj(int from){
        validateVertex(from);
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < V; i++){
            if(adj[from][i] == 1){
                res.add(i);
            }
        }
        return res;
    }

    public int degree(int from){
        return adj(from).size();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for(int i = 0; i < V; i ++){
            for(int j = 0; j < V; j ++)
                sb.append(String.format("%d ", adj[i][j]));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args){
        AdjMatrix adjMatrix = new AdjMatrix("resources\\g.txt");
        System.out.print(adjMatrix);
    }

}
