package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

/**
 * Description
 * For the given binary tree, return a deep copy of it.
 *
 * Example
 * Example 1:
 *
 * Input:
 *
 * {1,2,3}
 * Output:
 *
 * {1,2,3}
 * Explanation:
 *
 * The binary tree is look like this:
 *
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 */
public class CloneTree {

    public TreeNode cloneTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode clone = new TreeNode(root.val);
        clone.left = cloneTree(root.left);
        clone.right = cloneTree(root.right);
        return clone;
    }
}
