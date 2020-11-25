package Algorithm.Graph.Tree.Basic;

import DataStructure.AlgoUtils.TreeNode;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/19 0:19
 *   @Description :
 *  	In a binary search tree, find the node containing the largest number smaller than the
 *  	given target number. If there is no such number, return -2^31
 *      Assumption:
 * 	    The given root is not null
 * 	    There are no duplicate keys in the binary search tree
 *      Examples:
 * 	        5
  	     /     \
         2     11
             /    \
            6     14
        largest number smaller than 1 is Integer.MIN_VALUE(Java) or INT_MIN(c++)
        largest number smaller than 10 is 6
        largest number smaller than 6 is 5
        How is the binary tree represented?
        We use the level order traversal sequence with a special symbol "#" denoting the null node.
 */
public class LargestNumberSmallerBST {

    // iterative way
    public int findLargestSmaller(TreeNode root, int target){
        int res = Integer.MIN_VALUE;
        while(root != null){
            if(root.val >= target){
                root = root.left;
            } else {
                res = root.val;
                root = root.right;
            }
        }
        return res;
    }

    // recursive way
    public int findLargestSmallerI(TreeNode root, int target){
        if(root == null){
            return Integer.MIN_VALUE;
        }
        int res = Integer.MIN_VALUE;
        if(root.val >= target){
            res = Math.max(findLargestSmallerI(root.left, target), res);
        } else {
            int left = findLargestSmallerI(root.left, target);
            int right = findLargestSmallerI(root.right, target);
            res = Math.max(left, Math.max(res, right));
        }
        return root.val < target ? Math.max(root.val, res) : res;
    }

    public static void main(String[] args) {
        LargestNumberSmallerBST solution = new LargestNumberSmallerBST();

        TreeNode root = new TreeNode(5);
        TreeNode one = new TreeNode(2);
        TreeNode two = new TreeNode(11);
        TreeNode three = new TreeNode(6);
        TreeNode four = new TreeNode(14);
        root.left = one;
        root.right = two;
        two.left = three;
        two.right = four;

        int res = solution.findLargestSmallerI(root, 1);
        System.out.println(res);
    }

}
