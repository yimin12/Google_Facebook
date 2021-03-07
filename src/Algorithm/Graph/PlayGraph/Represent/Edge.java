package Algorithm.Graph.PlayGraph.Represent;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/10 12:37
 *   @Description :
 *
 */
public class Edge {

    private int v, w;

    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public String toString(){
        return String.format("%d-%d", v, w);
    }
}

