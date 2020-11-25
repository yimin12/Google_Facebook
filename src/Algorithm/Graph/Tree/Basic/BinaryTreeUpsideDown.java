package Algorithm.Graph.Tree.Basic;

import DataStructure.AlgoUtils.TreeNode;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/16 23:12
 *   @Description :
 *   	Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node)
 *	    or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 *  	Given a binary tree {1,2,3,4,5},
 *		   1
	      / \
	     2   3
	    / \
	   4   5
	   	return the root of the binary tree [4,5,2,#,#,3,1].
	   	4
  	   / \
      5   2
         / \
        3   1
 *
 */
public class BinaryTreeUpsideDown {

    // Iterative way
    public TreeNode upsideDown(TreeNode root){
        if(root == null) return null;
        // all leaf nodes with sibling means the left sub-child will become root
        TreeNode next, right, prev = null, preRight = null;
        while(root != null){
            next = root.left;
            right = root.right;
            root.right = preRight;
            root.left = prev;
            prev = root;
            preRight = right;
            root = next;
        }
        return prev;
    }
    // Recursive way
    public TreeNode upsideDownI(TreeNode root){
        // base case and corner case
        if(root == null || root.left == null){
            return root;
        }
        // recursion rule
        TreeNode newRoot = upsideDownI(root.left);
        root.left.right = root;
        root.left.left = root.right;
        root.left = null;
        root.right = null;
        return newRoot;
    }

}
