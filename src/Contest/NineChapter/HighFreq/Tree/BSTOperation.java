package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

public class BSTOperation {

    /**
     * Description
     * Given a root of Binary Search Tree with unique value for each node. Remove the node with given value. If there is no such a node with given value in the binary search tree, do nothing. You should keep the tree still a binary search tree after removal.
     *
     * Example
     * Example 1
     *
     * Input:
     * Tree = {5,3,6,2,4}
     * k = 3
     * Output: {5,2,6,#,4} or {5,4,6,2}
     * Explanation:
     * Given binary search tree:
     *     5
     *    / \
     *   3   6
     *  / \
     * 2   4
     * Remove 3, you can either return:
     *     5
     *    / \
     *   2   6
     *    \
     *     4
     * or
     *     5
     *    / \
     *   4   6
     *  /
     * 2
     */
    public TreeNode removeNode(TreeNode root, int value) {
        if(root == null) return root;
        if(root.val == value){
            if(root.left == null && root.right == null){
                return null; // cut this node directly
            } else if(root.left == null){
                return root.right;
            } else if(root.right == null){
                return root.left;
            } else {
                int largest = findMax(root.left);
                root.val = largest;
                root.left = removeNode(root.left, largest);
            }
        } else if(root.val > value){
            root.left = removeNode(root.left, value);
        } else {
            root.right = removeNode(root.right, value);
        }
        return root;
    }

    private int findMax(TreeNode root){
        while(root.right != null){
            root = root.right;
        }
        return root.val;
    }

    /**
     * Description
     * Given a binary search tree and a new tree node, insert the node into the tree. You should keep the tree still be a valid binary search tree.
     *
     * You can assume there is no duplicate values in this tree + node.
     *
     * Example
     * Example 1:
     * 	Input:  tree = {}, node = 1
     * 	Output:  1
     *
     * 	Explanation:
     * 	Insert node 1 into the empty tree, so there is only one node on the tree.
     *
     * Example 2:
     * 	Input: tree = {2,1,4,3}, node = 6
     * 	Output: {2,1,4,3,6}
     *
     * 	Explanation:
     * 	Like this:
     *
     *
     *
     * 	  2             2
     * 	 / \           / \
     * 	1   4   -->   1   4
     * 	   /             / \
     * 	  3             3   6
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if(root == null) return node;
        if(root.val > node.val){
            root.left = insertNode(root.left, node);
        } else {
            root.right = insertNode(root.right, node);
        }
        return root;
    }
}
