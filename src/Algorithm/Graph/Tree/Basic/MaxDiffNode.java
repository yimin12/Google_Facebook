package Algorithm.Graph.Tree.Basic;

import DataStructure.AlgoUtils.TreeNode;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/15 23:21
 *   @Description :
 *      Given a binary tree, find the node with the max difference in the total number
 *  	descendents in it left sub-tree and right sub-tree
 */
public class MaxDiffNode {

    // Find
    public TreeNode maxDiffNode(TreeNode root){
        if(root == null) return null;
        TreeNode[] res = new TreeNode[1];
        int[] diff = new int[1];
        diff[0] = -1;
        dfs(root, res, diff);
        return res[0];
    }
    private int dfs(TreeNode root, TreeNode[] res, int[] diff){
        if(root == null){
            return 0;
        }
        int leftSubs = dfs(root.left, res, diff);
        int rightSubs = dfs(root.right, res, diff);
        if(Math.abs(leftSubs - rightSubs) > diff[0]){
            res[0] = root;
            diff[0] = Math.abs(leftSubs - rightSubs);
        }
        return leftSubs + rightSubs + 1;
    }
}
