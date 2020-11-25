package Algorithm.Graph.Maxflow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/28 13:01
 *   @Description :
 *
 */
public class Maxflow_Mincut {

    private static final int MAX_V = 40;
    private static final int INF = 1000000000;
    private static final int[][] res = new int[MAX_V][];
    private static int mf, f, s, t;
    private static ArrayList<Integer> p = new ArrayList<>();

    private static void augment(int v, int minEdge){
        if(v == s){
            f = minEdge;
            return;
        } else if(p.get(v) != -1){
            augment(p.get(v), Math.min(minEdge, res[p.get(v)][v]));
            res[p.get(v)][v] -= f;
            res[v][p.get(v)] += f; // alter residual capacities
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File ff = new File("maxflow_in.txt");
        Scanner sc = new Scanner(ff);

        int V = sc.nextInt();
        s = sc.nextInt();
        t = sc.nextInt();

        for (int u = 0; u < V; u++) {
            res[u] = new int[MAX_V];
            int k = sc.nextInt();
            while (k-- > 0) {
                int v = sc.nextInt();
                int w = sc.nextInt();
                res[u][v] = w;
            }
        }

        mf = 0;
        while (true) { // run O(VE^2) Edmonds Karp to solve the Max Flow problem
            f = 0;

            // run BFS, please examine parts of the BFS code that is different than in Section 4.3
            Queue< Integer > q = new LinkedList< Integer >();
            ArrayList < Integer > dist = new ArrayList < Integer > ();
            dist.addAll(Collections.nCopies(V, INF)); // #define INF 2000000000
            q.offer(s);
            dist.set(s, 0);
            p.clear();
            p.addAll(Collections.nCopies(V, -1)); // (we have to record the BFS spanning tree)
            while (!q.isEmpty()) {                // (we need the shortest path from s to t!)
                int u = q.poll();
                if (u == t) break; // immediately stop BFS if we already reach sink t
                for (int v = 0; v < MAX_V; v++) // note: enumerating neighbors with AdjMatrix is `slow'
                    if (res[u][v] > 0 && dist.get(v) == INF) { // res[u][v] can change!
                        dist.set(v, dist.get(u) + 1);
                        q.offer(v);
                        p.set(v, u); // parent of vertex v is vertex u
                    }
            }

            augment(t, INF); // find the min edge weight `f' along this path, if any
            if (f == 0) break; // if we cannot send any more flow (`f' = 0), terminate the loop
            mf += f; // we can still send a flow, increase the max flow!
        }

        System.out.printf("%d\n", mf); // this is the max flow value of this flow graph
    }
}
