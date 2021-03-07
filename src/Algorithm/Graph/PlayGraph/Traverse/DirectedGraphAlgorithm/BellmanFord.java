package Algorithm.Graph.PlayGraph.Traverse.DirectedGraphAlgorithm;

import Algorithm.Graph.PlayGraph.Represent.WeightedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/11 17:51
 *   @Description :
 *
 */
public class BellmanFord {

    private WeightedGraph graph;
    private int source;
    private int[] dis;
    private int[] pre;
    private boolean hasNegCycle = false;

    public BellmanFord(WeightedGraph graph, int source){
        this.graph = graph;
        graph.validateVertex(source);
        this.source = source;

        dis = new int[graph.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[source] = 0;

        pre = new int[graph.V()];
        Arrays.fill(pre, -1);

        for(int pass = 1; pass < graph.V(); pass ++){
            for(int v = 0; v < graph.V(); v ++){
                for(int nei : graph.adj(v)){
                    if(dis[nei] != Integer.MAX_VALUE && dis[v] + graph.getWeight(nei, v) < dis[nei]){
                        dis[nei] = dis[v] + graph.getWeight(nei, v);
                        pre[nei] = v;
                    }
                }
            }
        }
        // check negative loop, last time of relaxation
        for(int v = 0; v < graph.V(); v ++){
            for(int nei : graph.adj(v)){
                if(dis[v] != Integer.MAX_VALUE && dis[v] + graph.getWeight(v, nei) < dis[nei]){
                    hasNegCycle = true;
                }
            }
        }
    }

    public boolean hasNegativeCycle(){
        return hasNegCycle;
    }

    public boolean isConnectedTo(int v){
        graph.validateVertex(v);
        return dis[v] != Integer.MAX_VALUE;
    }

    public int distance(int v){
        graph.validateVertex(v);
        if(hasNegCycle) throw new RuntimeException("exist negative cycle.");
        return dis[v];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(!isConnectedTo(t)) return res;
        int cur = t;
        while(cur != source){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\DirectedGraphAlgorithm\\wg.txt", true);
        BellmanFord bf = new BellmanFord(g, 0);
        if(!bf.hasNegativeCycle()){
            for(int v = 0; v < g.V(); v ++)
                System.out.print(bf.distance(v) + " ");
            System.out.println();

            System.out.println(bf.path(1));
        }
        else
            System.out.println("exist negative cycle.");

        WeightedGraph g2 = new WeightedGraph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\DirectedGraphAlgorithm\\wg2.txt", true);
        BellmanFord bf2 = new BellmanFord(g2, 0);
        if(!bf2.hasNegativeCycle()){
            for(int v = 0; v < g2.V(); v ++)
                System.out.print(bf2.distance(v) + " ");
            System.out.println();
        }
        else
            System.out.println("exist negative cycle.");
    }
}
