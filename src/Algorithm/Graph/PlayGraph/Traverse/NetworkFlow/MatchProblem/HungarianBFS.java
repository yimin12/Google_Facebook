package Algorithm.Graph.PlayGraph.Traverse.NetworkFlow.MatchProblem;

import Algorithm.Graph.PlayGraph.Represent.Graph;
import Algorithm.Graph.PlayGraph.Traverse.DFS.BipartitionDetection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/12 19:51
 *   @Description :
 *
 */
public class HungarianBFS {

    private Graph graph;
    private int maxMatching = 0;
    private int[] matching;

    public HungarianBFS(Graph graph){
        BipartitionDetection bd = new BipartitionDetection(graph);
        if(!bd.isBipartite())
            throw new IllegalArgumentException("Hungarian only works for bipartite graph.");
        this.graph = graph;
        int[] colors = bd.colors();
        matching = new int[graph.V()];
        for(int v = 0; v < graph.V(); v ++){
            if(colors[v] == 0 && matching[v] == -1){
                if(bfs(v)) maxMatching ++;
            }
        }
    }

    private boolean bfs(int v){
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] pre = new int[graph.V()];
        Arrays.fill(pre, -1);

        queue.add(v);
        pre[v] = v;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int next:graph.adj(cur)){
                if(pre[next] == -1){
                    if(matching[next] != -1){
                        queue.add(matching[next]);
                        pre[next] = cur;
                        pre[matching[next]] = next;
                    }
                } else {
                    pre[next] = cur;
                    ArrayList<Integer> augPath = getAugPath(pre, v, next);
                    for(int i = 0 ; i < augPath.size(); i += 2){
                        matching[augPath.get(i)] = augPath.get(i + 1);
                        matching[augPath.get(i + 1)] = augPath.get(i);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<Integer> getAugPath(int[] pre, int start,  int end){
        ArrayList<Integer> res = new ArrayList<>();
        int cur = end;
        while(cur != start){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(start);
        return res; // no need to reverse
    }

    public int maxMatching(){
        return maxMatching;
    }

    public boolean isPerfectMatching(){
        return maxMatching * 2 == graph.V();
    }

    public static void main(String[] args){

        Graph g = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\NetworkFlow\\MatchProblem\\g.txt");
        HungarianBFS hungarian = new HungarianBFS(g);
        System.out.println(hungarian.maxMatching());

        Graph g2 = new Graph("src\\Algorithm\\Graph\\PlayGraph\\Traverse\\NetworkFlow\\MatchProblem\\g2.txt");
        HungarianBFS hungarian2 = new HungarianBFS(g2);
        System.out.println(hungarian2.maxMatching());
    }
}
