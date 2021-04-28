package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
 *
 * Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.
 *
 * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.
 *
 * The right-most node is also defined by the same way with left and right exchanged.
 *
 * Example
 * Example 1:
 *
 * Input: {1,#,2,3,4}
 * Output: [1,3,4,2]
 * Explanation:
 *   1
 *    \
 *     2
 *    / \
 *   3   4
 *   The root doesn't have left subtree, so the root itself is left boundary.
 *   The leaves are node 3 and 4.
 *   The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
 *   So order them in anti-clockwise without duplicates and we have [1,3,4,2].
 * Example 2:
 *
 * Input: {1,2,3,4,5,6,#,#,#,7,8,9,10}
 * Output: [1,2,4,7,8,9,10,6,3]
 * Explanation:
 *           1
 *      /          \
 *     2            3
 *    / \          /
 *   4   5        6
 *      / \      / \
 *     7   8    9  10
 *   The left boundary are node 1,2,4. (4 is the left-most node according to definition)
 *   The leaves are node 4,7,8,9,10.
 *   The right boundary are node 1,3,6,10. (10 is the right-most node).
 *   So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 */
public class BoundaryBinaryTree {

    List<Integer> res = new ArrayList<>();
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if(root == null) return res;
        res.add(root.val);
        dfs(root.left, true, false);
        dfs(root.right, false, true);
        return res;
    }

    private void dfs(TreeNode root, boolean leftBoundary, boolean rightBoundary){
        if(root == null) return;
        // leaf node should be boundary, Case 1
        if(root.left == null && root.right == null){
            res.add(root.val);
            return;
        }
        if(leftBoundary) {
            res.add(root.val);
        }
        // the left node of left boundary node must be left boundary node as well, Case 2
        /**
         *     a
         *    /
         *   b
         *  /
         * c
         */
        dfs(root.left, leftBoundary, rightBoundary && root.right == null);
        // the right node of right boundary node must be right boundary node as well, Case 3
        /**  leftBoundary && root.left == null is for following
         *     a
         *    /
         *   b
         *    \
         *     c
         */
        dfs(root.right, leftBoundary && root.left == null, rightBoundary);
        if(rightBoundary){
            res.add(root.val);
        }
    }
}
