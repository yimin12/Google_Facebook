package Algorithm.Graph.PlayGraph.Represent;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/10 22:27
 *   @Description :
 *
 */
public class WeightedEdge implements Comparable<WeightedEdge>{

    private int v, w, weight;

    public WeightedEdge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int V() {
        return v;
    }

    public int W() {
        return w;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(WeightedEdge o) {
        return weight - o.weight; // make sure not overflow
    }

    @Override
    public String toString(){
        return String.format("(%d-%d: %d)", v, w, weight);
    }
}
