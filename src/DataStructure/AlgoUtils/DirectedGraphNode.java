package DataStructure.AlgoUtils;

import java.util.ArrayList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/5 19:47
 *   @Description :
 *
 */
public class DirectedGraphNode {
    public int value;
    public ArrayList<DirectedGraphNode> neighbors;
    public DirectedGraphNode(int value){
        this.value = value;
        this.neighbors = new ArrayList<DirectedGraphNode>();
    }
}
