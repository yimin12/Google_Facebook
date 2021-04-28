package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

public class ManipulateTree {

    /**
     * Description
     * Invert a binary tree.Left and right subtrees exchange.
     *
     * Example
     * Example 1:
     *
     * Input: {1,3,#}
     * Output: {1,#,3}
     * Explanation:
     * 	  1    1
     * 	 /  =>  \
     * 	3        3
     * Example 2:
     *
     * Input: {1,2,3,#,#,4}
     * Output: {1,3,2,#,4}
     * Explanation:
     *
     *       1         1
     *      / \       / \
     *     2   3  => 3   2
     *        /       \
     *       4         4
     */
    public void invertBinaryTree(TreeNode root) {
        if(root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertBinaryTree(root.left);
        invertBinaryTree(root.right);
    }
}
