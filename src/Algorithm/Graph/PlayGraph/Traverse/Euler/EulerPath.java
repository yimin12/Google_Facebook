package Algorithm.Graph.PlayGraph.Traverse.Euler;

import Algorithm.Graph.PlayGraph.Represent.CC;
import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/10 21:36
 *   @Description :
 *      Hierholzer - Alogrithm
 *      Time: O(E), Space: (E) -> linear complexity
 */
public class EulerPath {

    private Graph graph;

    public EulerPath(Graph graph){
        this.graph = graph;
    }

    private boolean hasEulerLoop(){
        CC cc = new CC(graph);
        if(cc.count() > 1) return false;
        for(int v = 0; v < graph.V(); v ++){
            if(graph.degree(v) % 2 == 1) return false;
        }
        return true;
    }

    public ArrayList<Integer> result() {
        ArrayList<Integer> res = new ArrayList<>();
        if(!hasEulerLoop()) return res;
        Graph g = (Graph)graph.clone();
        Deque<Integer> stack = new LinkedList<>();
        int curv = 0;
        stack.push(curv);
        while(!stack.isEmpty()){
            if(g.degree(curv) != 0){
                stack.push(curv);
                int w = g.adj(curv).iterator().next();
                g.removeEdge(curv, w);
                curv = w;
            }
            else{
                res.add(curv);
                curv = stack.pop();
            }
        }
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\Euler\\g.txt");
        EulerPath ep = new EulerPath(g);
        System.out.println(ep.result());

        Graph g2 = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\Euler\\g2.txt");
        EulerPath ep2 = new EulerPath(g2);
        System.out.println(ep2.result());

    }
}
