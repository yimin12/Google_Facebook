package Algorithm.Graph.PlayGraph.Represent;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/8 17:16
 *   @Description :
 *
 */
public class AdjSet {

    // if you want it in order
    private int V;
    private int E;
    private TreeSet<Integer>[] adj;

    public AdjSet(String path){
        File file = new File(path);
        try(Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if(V < 0) throw new IllegalArgumentException("V must be non-negative");
            adj = new TreeSet[V];
            for(int i = 0; i < V; i++){
                adj[i] = new TreeSet<Integer>();
            }

            E = scanner.nextInt();
            if(E < 0) throw new IllegalArgumentException("E must be non-negative");

            for(int i = 0; i < E; i++){
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);

                if(a == b) throw new IllegalArgumentException("Self Loop is Detected!");
                if(adj[a].contains(b)) throw new IllegalArgumentException("Parallel Edges are Detected!");

                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void validateVertex(int from){
        if(from < 0 || from >= V){
            throw new IllegalArgumentException("vertex " + from + "is invalid");
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public boolean hasEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    public Iterable<Integer> adj(int from){
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
        AdjSet adjSet = new AdjSet("resources\\g.txt");
        System.out.print(adjSet);
    }
}
