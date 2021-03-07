package Algorithm.Graph.PlayGraph.Represent;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/8 12:31
 *   @Description :
 *
 */
public class AdjList {

    private int V;
    private int E;
    private LinkedList<Integer>[] adj;

    public AdjList(String path){
        File file = new File(path);
        try(Scanner scanner = new Scanner(file)){
           V = scanner.nextInt();
           if(V < 0) throw new IllegalArgumentException("V must be non-negative");
           adj = new LinkedList[V];
           for(int i = 0; i < V; i++){
               adj[i] = new LinkedList<Integer>();
           }
           E = scanner.nextInt();
           if(E < 0) throw new IllegalArgumentException("E must be non-negative");
           for(int i = 0; i < E; i++){
               int a = scanner.nextInt();
               validateVertex(a);
               int b = scanner.nextInt();
               validateVertex(b);
               if(a == b) throw new IllegalArgumentException("Self loop detected!");
               if(adj[a].contains(b)) throw new IllegalArgumentException("Parallel Edges are detected!");
               adj[a].add(b);
               adj[b].add(a);
           }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void validateVertex(int v){
        if(v < 0 || v >= V){
            throw new IllegalArgumentException("vertex" + v + "is invalid");
        }
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
        return adj[from].contains(to);
    }

    public LinkedList<Integer> adj(int from){
        validateVertex(from);
        return adj[from];
    }

    public int degree(int from){
        validateVertex(from);
        return adj[from].size();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for(int v = 0; v < V; v ++){
            sb.append(String.format("%d : ", v));
            for(int w : adj[v])
                sb.append(String.format("%d ", w));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args){
        AdjList adjList = new AdjList("resources\\g.txt");
        System.out.print(adjList);
    }
}
