package Algorithm.Graph.PlayGraph.Traverse.Hamilton;

import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.ArrayList;
import java.util.Collections;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/10 18:38
 *   @Description :
 *
 */
public class HamiltonPath {

    private Graph graph;
    private int[] pre;
    private int end;
    private int source;

    public HamiltonPath(Graph graph, int source){
        this.graph = graph;
        this.source = source;
        pre = new int[graph.V()];
        end = -1;
        int visited = 0; // state compressed
        dfs(visited, source, source, graph.V());
    }

    private boolean dfs(int visited, int v, int parent, int left){
        visited += (1 << v);
        pre[v] = parent;
        left --;
        if(left == 0){
            end = v;
            return true;
        }
        for(int nei : graph.adj(v)){
            if((visited & (1 << nei)) == 0){
                if(dfs(visited, nei, v, left)) return true; // pass in middle
            }
        }
        visited -= (1 << v);
        return false;
    }

    public ArrayList<Integer> result(){

        ArrayList<Integer> res = new ArrayList<>();
        if(end == -1) return res;
        int cur = end;
        while(cur != source){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\Hamilton\\g.txt");
        HamiltonPath hl = new HamiltonPath(g, 0);
        System.out.println(hl.result());

        Graph g2 = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\Hamilton\\g2.txt");
        HamiltonPath hl1 = new HamiltonPath(g2, 1);
        System.out.println(hl1.result());
    }
}
