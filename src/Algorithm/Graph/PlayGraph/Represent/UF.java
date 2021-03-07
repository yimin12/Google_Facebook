package Algorithm.Graph.PlayGraph.Represent;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/10 22:29
 *   @Description :
 *      Union Find
 */
public class UF {

    private int[] parent;

    public UF(int n){
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
    }

    // compressed
    public int find(int p){
        if(p != parent[p]){
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    public boolean isConnected(int a, int b){
        return find(a) == find(b);
    }

    public void union(int a, int b){
        int f_a = find(a);
        int f_b = find(b);
        if(f_a != f_b){
            parent[f_a] = f_b;
        }
    }
}
