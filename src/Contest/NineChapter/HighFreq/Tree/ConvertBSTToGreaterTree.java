package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

/**
 * 661 · Convert BST to Greater Tree
 * Algorithms
 * Easy
 * Accepted Rate
 * 44%
 *
 * DescriptionSolutionNotesDiscussLeaderboard
 * Description
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
 *
 * Example
 * Example 1:
 *
 * Input : {5,2,13}
 *               5
 *             /   \
 *            2     13
 * Output : {18,20,13}
 *              18
 *             /   \
 *           20     13
 * Example 2:
 *
 * Input : {5,3,15}
 *               5
 *             /   \
 *            3     15
 * Output : {20,23,15}
 *              20
 *             /   \
 *           23     15
 */
public class ConvertBSTToGreaterTree {

    public TreeNode convertBST(TreeNode root) {
        if(root == null) return null;
        dfs(root, 0);
        return root;
    }

    // go right first will result in descending order
    private int dfs(TreeNode root, int sum){
        if(root == null) return sum;
        sum = dfs(root.right, sum);
        sum += root.val;
        root.val = sum;
        sum = dfs(root.left, sum);
        return sum;
    }
}
