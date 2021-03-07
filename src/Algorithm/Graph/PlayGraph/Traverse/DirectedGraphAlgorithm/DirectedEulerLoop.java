package Algorithm.Graph.PlayGraph.Traverse.DirectedGraphAlgorithm;

import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/11 18:34
 *   @Description :
 *
 */
public class DirectedEulerLoop {

    private Graph graph;

    public DirectedEulerLoop(Graph graph){
        if(!graph.isDirected()){
            throw new IllegalArgumentException("DirectedEulerLopp only works in directed graph");
        }
        this.graph = graph;
    }

    private boolean hasEulerLoop(){
        for(int v = 0 ; v < graph.V(); v ++){
            if(graph.indegree(v) != graph.outdegree(v)){
                return false;
            }
        }
        return true;
    }

    // print the euler path
    public ArrayList<Integer> path(){
        ArrayList<Integer> res = new ArrayList<>();
        if(!hasEulerLoop()) return res;
        Graph g = (Graph) graph.clone();

        Deque<Integer> stack = new LinkedList<>();
        int v = 0;
        stack.push(v);
        while(!stack.isEmpty()){
            if(g.outdegree(v) != 0){
                stack.push(v);
                int w = g.adj(v).iterator().next(); // the cursor will remember where you are
                g.removeEdge(v, w);
                v = w;
            } else {
                res.add(v);
                v = stack.pop();
            }
        }
        Collections.reverse(res);
        return res;
    }

}
