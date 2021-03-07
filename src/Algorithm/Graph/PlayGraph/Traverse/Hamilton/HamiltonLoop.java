package Algorithm.Graph.PlayGraph.Traverse.Hamilton;

import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.ArrayList;
import java.util.Collections;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/10 16:54
 *   @Description :
 *
 */
public class HamiltonLoop {

    private Graph graph;
    private boolean[] visited;
    private int[] pre; // record the path
    private int end;

    public HamiltonLoop(Graph graph){
        this.graph = graph;
        visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        end = -1;
        //dfs(0, 0);
        dfsI(0, 0, graph.V()); // optimized, no waste for linear check
    }

    private boolean dfsI(int v, int parent, int left){
        // left : how many node need to traverse
        visited[v] = true;
        pre[v] = parent;
        left--; // finish check current node
        if(left == 0 && graph.hasEdge(v, 0)){
            end = v;
            return true;
        }
        for(int nei : graph.adj(v)){
            if(!visited[nei]){
                if(dfsI(nei, v, left)) return true;
            }
        }
        visited[v] = false;
        return false;
    }

    private boolean dfs(int v, int parent){
        visited[v] = true;
        pre[v] = parent;
        for(int nei : graph.adj(v)){
            if(!visited[nei]){
                if(dfs(nei, v)) return true;
            } else if(nei == 0 && allVisited()){
                end = v;
                return true;
            }
        }
        visited[v] = false;
        return false;
    }

    // use int to record how many node need to finish the check
    private boolean allVisited(){
        for(int v = 0; v < graph.V(); v ++){
            if(!visited[v]) return false;
        }
        return true;
    }

    public ArrayList<Integer> result(){
        ArrayList<Integer> res = new ArrayList<Integer>();
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
        HamiltonLoop hl = new HamiltonLoop(g);
        System.out.println(hl.result());

        Graph g2 = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\Hamilton\\g2.txt");
        HamiltonLoop hl1 = new HamiltonLoop(g2);
        System.out.println(hl1.result());
    }
}
