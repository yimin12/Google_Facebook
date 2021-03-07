package Algorithm.Graph.PlayGraph.Traverse.ShortestPath;

import Algorithm.Graph.PlayGraph.Represent.WeightedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/11 12:34
 *   @Description :
 *
 */
public class Dijkstra {

    private WeightedGraph graph;
    private int source;
    private int[] dis;
    private boolean[] visited;
    private int[] pre; // record the shortest path

    private class Node implements Comparable<Node>{

        public int v, dis;

        public Node(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return dis - o.dis;
        }

    }

    public Dijkstra(WeightedGraph graph, int source) {
        this.graph = graph;
        graph.validateVertex(source);
        this.source = source;

        dis = new int[graph.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);

        pre = new int[graph.V()];
        Arrays.fill(pre, -1);

        dis[source] = 0;
        pre[source] = source;
        visited = new boolean[graph.V()];

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(source, 0));
        while(!pq.isEmpty()){
            int cur = pq.poll().v;
            if(visited[cur]) continue;
            visited[cur] = true;
            for(int nei : graph.adj(cur)){
                if(!visited[nei]){
                    if(dis[cur] + graph.getWeight(cur, nei) < dis[nei]){
                        dis[nei] = dis[cur] + graph.getWeight(cur, nei);
                        pq.add(new Node(nei, dis[nei])); // put the shortest distance back to the priority Queue
                        pre[nei] = cur;
                    }
                }
            }
        }
    }

    public boolean isConnectedTo(int v){
        graph.validateVertex(v);
        return visited[v];
    }

    public int distance(int v){
        graph.validateVertex(v);
        return dis[v];
    }

    /**
     *  path from source to t
     * @param t
     * @return
     */
    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(!isConnectedTo(t)) return res;
        int cur = t;
        while(cur != source){
            res.add(source);
            cur = pre[cur];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\ShortestPath\\g.txt");
        Dijkstra dij = new Dijkstra(graph, 0);
        for(int v = 0; v < graph.V(); v ++){
            System.out.print(dij.distance(v) + " ");
        }
        System.out.println();
    }
}
