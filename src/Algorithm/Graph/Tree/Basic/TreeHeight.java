package Algorithm.Graph.Tree.Basic;

import DataStructure.AlgoUtils.TreeNode;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/14 16:36
 *   @Description :
 *      <p>Time Complexity: O(n)
 */
public class TreeHeight {

    public int getHeigth1(TreeNode node){
        if(node == null) return -1;
        return Math.max(getHeigth1(node.left), getHeigth1(node.right)) +1;
    }

    // Returns the height of the binary tree which is the number of edges from the
    // root to the deepest leaf node, or -1 if the input is an empty tree.
    public static int treeHeight2(TreeNode node) {
        // Handle empty tree edge case.
        if (node == null) return -1;
        if (isLeafNode(node)) return 0;
        return Math.max(treeHeight2(node.left), treeHeight2(node.right)) + 1;
    }

    private static boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
