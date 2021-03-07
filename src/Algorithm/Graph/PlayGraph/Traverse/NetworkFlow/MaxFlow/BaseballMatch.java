package Algorithm.Graph.PlayGraph.Traverse.NetworkFlow.MaxFlow;

import Algorithm.Graph.PlayGraph.Represent.WeightedGraph;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/12 15:47
 *   @Description :
 *
 */
public class BaseballMatch {

    private WeightedGraph network;
    private int source, sink;

    private WeightedGraph rG;
    private int maxFlow = 0;

    public BaseballMatch(WeightedGraph network, int source, int sink){
        if(!network.isDirected())
            throw new IllegalArgumentException("MaxFlow only works in directed graph.");

        if(network.V() < 2)
            throw new IllegalArgumentException("The network should hs at least 2 vertices.");

        network.validateVertex(source);
        network.validateVertex(sink);
        if(source == sink)  throw new IllegalArgumentException("s and t should be differrent.");

        this.network = network;
        this.source = source;
        this.sink = sink;
        this.rG = new WeightedGraph(network.V(), true);

        for(int v = 0; v < network.V(); v ++){
            for(int nei : network.adj(v)){
                int weight = network.getWeight(v, nei);
                rG.addEdge(v, nei, weight);
                rG.addEdge(nei, v, 0);
            }
        }

        while(true){
            ArrayList<Integer> augPath = getAugmentingPath();
            if(augPath.size() == 0) break;
            int f = Integer.MAX_VALUE;
            for(int i = 1; i < augPath.size(); i ++){
                int v = augPath.get(i - 1);
                int w = augPath.get(i);
                f = Math.min(f, rG.getWeight(v, w));
            }
            maxFlow += f;
            for(int i = 1; i < augPath.size(); i ++){
                int v = augPath.get(i - 1);
                int w = augPath.get(i);
                rG.setWeight(v, w, rG.getWeight(v, w) - f);
                rG.setWeight(w, v, rG.getWeight(w, v) + f);
            }
        }
    }

    private ArrayList<Integer> getAugmentingPath(){
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] pre = new int[network.V()];
        Arrays.fill(pre, -1);

        queue.add(source);
        pre[source] = source;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            if(cur == sink) break;
            for(int next:rG.adj(cur)){
                if(pre[next] == -1 && rG.getWeight(cur, next) > 0){
                    pre[next] = cur;
                    queue.add(next);
                }
            }
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(pre[sink] == -1) return res;
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
        if(!network.hasEdge(v, w)){
            throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
        }
        return rG.getWeight(w, v); // return the backward edge
    }

    public static void main(String[] args){

        WeightedGraph network = new WeightedGraph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\MaxFlow\\baseball.txt", true);
        MaxFlow maxflow = new MaxFlow(network, 0, 10);
        System.out.println(maxflow.result());

    }
}
