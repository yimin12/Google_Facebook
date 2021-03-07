package Algorithm.Graph.PlayGraph.Traverse.DFS;

import Algorithm.Graph.PlayGraph.Represent.Graph;

import java.util.HashSet;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/10 15:52
 *   @Description :
 *
 */
public class FindCutPoints {

    private Graph graph;
    private boolean[] visited;
    private int[] ord;
    private int[] low;
    private int cnt;

    private HashSet<Integer> res;

    public FindCutPoints(Graph graph){
        this.graph = graph;
        visited = new boolean[graph.V()];
        res = new HashSet<>();
        ord = new int[graph.V()];
        low = new int[graph.V()];
        cnt = 0;

        for(int v = 0; v < graph.V(); v ++){
            if(!visited[v]){
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent){
        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];
        cnt++;
        int child = 0; // handle the corner case of root node
        for(int nei: graph.adj(v)){
            if(!visited[nei]){
                dfs(nei, v);
                low[v] = Math.min(low[v], low[nei]);
                if(v != parent && low[nei] >= ord[v]){
                    res.add(v);  // v != parent && !visited[v] -> the node is root(entrance)
                }
                child++; // reuse the dfs traverse's tree
                if(v == parent && child > 1) res.add(v);
            } else if(nei != parent){
                low[v] = Math.min(low[v], low[nei]);
                // low[v] = Math.min(low[v], ord[nei]); is the same, for the first entrance point, low[nei] == ord[nei];
            }
        }
    }

    public HashSet<Integer> result(){
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("resources\\g.txt");
        FindCutPoints fb = new FindCutPoints(g);
        System.out.println("Bridges in g : " + fb.result());

        Graph g2 = new Graph("resources\\g2.txt");
        FindCutPoints fb2 = new FindCutPoints(g2);
        System.out.println("Bridges in g2 : " + fb2.result());

//        Graph g3 = new Graph("resources\\bipartition.txt");
//        FindCutPoints fb3 = new FindCutPoints(g3);
//        System.out.println("Bridges in g3 : " + fb3.result());

        Graph tree = new Graph("resources\\tree.txt");
        FindCutPoints fb_tree = new FindCutPoints(tree);
        System.out.println("Bridges in tree : " + fb_tree.result());
    }
}
