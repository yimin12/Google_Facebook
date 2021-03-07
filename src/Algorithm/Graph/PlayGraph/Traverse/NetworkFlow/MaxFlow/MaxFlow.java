package Algorithm.Graph.PlayGraph.Traverse.NetworkFlow.MaxFlow;

import Algorithm.Graph.PlayGraph.Represent.WeightedGraph;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/12 13:22
 *   @Description :
 *      Edmonds-Karp-Algorithm: Time: O(V * E * E)
 */
public class MaxFlow {

    private WeightedGraph network;
    private int source, sink;
    private WeightedGraph residualGraph;
    private int maxFlow = 0;

    public MaxFlow(WeightedGraph network, int source, int sink){
        if(!network.isDirected()) throw new IllegalArgumentException("MaxFlow only works in directed graph.");
        if(network.V() < 2) throw new IllegalArgumentException("The network should be different");
        network.validateVertex(source);
        network.validateVertex(sink);
        if(source == sink) throw new IllegalArgumentException("source and sink should be different.");
        
        this.network = network;
        this.source = source;
        this.sink = sink;
        
        this.residualGraph = new WeightedGraph(network.V(), true); 
        for(int v = 0; v < network.V(); v ++)
            for(int nei:network.adj(v)){
                int c = network.getWeight(v, nei);
                // forward edge
                residualGraph.addEdge(v, nei, c);
                // backward edge
                residualGraph.addEdge(nei, v, 0);
            }
        
        // Edmonds-Karp-Algorithm: Time: O(V * E * E)
        while(true){
            ArrayList<Integer> augPath = getAugmentingPath();
            if(augPath.size() == 0) break;
            int f = Integer.MAX_VALUE;
            for(int i = 1; i < augPath.size(); i ++){
                int v = augPath.get(i - 1);
                int w = augPath.get(i);
                f = Math.min(f, residualGraph.getWeight(v, w));
            }
            maxFlow += f;
            // update the residual graph
            for(int i = 1; i < augPath.size(); i ++){
                int v = augPath.get(i - 1);
                int w = augPath.get(i);
                // forward edge
                residualGraph.setWeight(v, w, residualGraph.getWeight(v, w) - f);
                // backward edge
                residualGraph.setWeight(w, v, residualGraph.getWeight(w, v) + f);
            }
        }
    }

    // bfs to get augmentation path
    private ArrayList<Integer> getAugmentingPath(){
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] pre = new int[network.V()];
        Arrays.fill(pre, -1);

        queue.add(source);
        pre[source] = source;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            if(cur == sink) break; // find one augmentation
            for(int nei : residualGraph.adj(cur)){
                if(pre[nei] == -1 && residualGraph.getWeight(cur, nei) > 0){
                    pre[nei] = cur;
                    queue.add(nei);
                }
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        if(pre[sink] == -1) return res; // no augmentation path

        int cur = sink;
        while(cur != source){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }

    public int result(){
        return maxFlow;
    }

    public int flow(int v, int w){
        if(!network.hasEdge(v, w)) throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
        return residualGraph.getWeight(w, v); // return the backward edge
    }

    public static void main(String[] args){
        WeightedGraph network = new WeightedGraph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\MaxFlow\\network.txt", true);
        MaxFlow maxflow = new MaxFlow(network, 0, 3);
        System.out.println(maxflow.result());
        for(int v = 0; v < network.V(); v ++)
            for(int w: network.adj(v))
                System.out.println(String.format("%d-%d: %d / %d", v, w, maxflow.flow(v, w), network.getWeight(v, w)));

        WeightedGraph network2 = new WeightedGraph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\MaxFlow\\network2.txt", true);
        MaxFlow maxflow2 = new MaxFlow(network2, 0, 5);
        System.out.println(maxflow2.result());
        for(int v = 0; v < network2.V(); v ++)
            for(int w: network2.adj(v))
                System.out.println(String.format("%d-%d: %d / %d", v, w, maxflow2.flow(v, w), network2.getWeight(v, w)));
    }
}
