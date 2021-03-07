package Algorithm.Graph.PlayGraph.Traverse.Euler;

import Algorithm.Graph.PlayGraph.Represent.Graph;
import Algorithm.Graph.PlayGraph.Represent.CC;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/10 21:09
 *   @Description :
 *
 */
public class EulerLoop {

    private Graph graph;

    public EulerLoop(Graph graph) {
        this.graph = graph;
    }

    // if degree of each vertex is even, and the number of connected component == 1, it must contain euler loop
    private boolean hasEulerLoop(){
        CC component = new CC(graph);
        if(component.count() > 1) return false;
        for(int v = 0; v < graph.V(); v ++){
            if(graph.degree(v) % 2 == 1) return false;
        }
        return true;
    }

}
