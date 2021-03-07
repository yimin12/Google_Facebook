package Algorithm.Graph.PlayGraph.Traverse.Hamilton;

import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.ArrayList;
import java.util.Collections;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/10 18:12
 *   @Description :
 *
 */
public class HamiltonLoopStateCompress {

    private Graph graph;
    private int[] pre;
    private int end;

    public HamiltonLoopStateCompress(Graph graph){
        this.graph = graph;
        pre = new int[graph.V()];
        end = -1;

        int visited = 0;
        dfs(visited, 0, 0, graph.V()); // as for NP problem, 32 digits is enough
    }

    // state compress
    private boolean dfs(int visited, int v, int parent, int left){
        visited += (1 << v);
        pre[v] = parent;
        left--;
        // base case
        if(left == 0 && graph.hasEdge(v, 0)){
            end = v;
            return true;
        }
        for(int nei : graph.adj(v)){
            if((visited & ( 1 << nei )) == 0){
                if(dfs(visited, nei, v, left)) return true;
            }
        }
        visited -= (1 << v);
        return false;
    }

    public ArrayList<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        if(end == -1) return res;
        int cur = end;
        while(cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\Hamilton\\g.txt");
        HamiltonLoopStateCompress hl = new HamiltonLoopStateCompress(g);
        System.out.println(hl.result());

        Graph g2 = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\Hamilton\\g2.txt");
        HamiltonLoopStateCompress hl1 = new HamiltonLoopStateCompress(g2);
        System.out.println(hl1.result());
    }
}
