package Algorithm.Graph.Tree.Basic;

import DataStructure.AlgoUtils.TreeNode;

import java.util.HashSet;
import java.util.Set;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/15 23:28
 *   @Description :
* 	    Given a binary tree and a target sum, determine if the tree has a root-to-leaf path
* 	such that adding up all the values along the path equals the given target.
* Examples:
* 	Given the below binary tree and target = 16,
              5		return true, as there exist a root-to-leaf path 5-8-3 which sum is 16.
             / \
            4   8
           /   / \
          1    3  4
         /  \      \
        7    2      1
 */
public class BinaryTreePathSum {

    public boolean isExist(TreeNode root, int target){
        if(root == null) return false;
        Set<Integer> prefixSum = new HashSet<>();
        prefixSum.add(0);
        return dfs(root, prefixSum, 0, target);
    }
    private boolean dfs(TreeNode root, Set<Integer> prefix, int preSum, int target){
        preSum += root.val;
        if(prefix.contains(preSum - target)){
            return true;
        }
        boolean needRemove = prefix.add(preSum);
        if(root.left != null && dfs(root.left, prefix, preSum, target)){
            return true;
        }
        if (root.right != null && dfs(root.right, prefix, preSum, target)) {
            return true;
        }
        if(needRemove){
            prefix.remove(preSum);
        }
        return false;
    }

}
