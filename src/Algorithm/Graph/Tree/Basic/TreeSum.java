package Algorithm.Graph.Tree.Basic;

import java.util.ArrayList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/14 16:38
 *   @Description :
 *
 */
public class TreeSum {

    public static class TreeNode{
        int value;
        List<TreeNode> children = new ArrayList<>();
        public TreeNode(int val){
            this.value = val;
        }
        public int getValue(){
            return value;
        }
        public List<TreeNode> getChildren(){
            return children;
        }
        public void addChild(TreeNode... nodes){
            for(TreeNode node:nodes){
                children.add(node);
            }
        }
    }

    public static int treeSum(TreeNode node){
        if(node == null) return 0;
        int total = 0;
        for(TreeNode child : node.getChildren()) total += treeSum(child);
        total += node.getValue();
        return total;
    }

}
