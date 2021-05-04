package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

import java.util.List;

/**
 * Description
 * Given a binary tree, find the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 *
 * Example
 * Example 1:
 *
 * Input:
 *    1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 * Output:3
 * Explanation:
 * Longest consecutive sequence path is 3-4-5, so return 3.
 * Example 2:
 *
 * Input:
 *    2
 *     \
 *      3
 *     /
 *    2
 *   /
 *  1
 * Output:2
 * Explanation:
 * Longest consecutive sequence path is 2-3,not 3-2-1, so return 2.
 */
public class BinaryTreeLongestConsecutiveSequence {

    public int longestConsecutive(TreeNode root) {
        if(root == null) return 0;
        return dfs(root, null, 0);
    }

    private int dfs(TreeNode root, TreeNode parent, int len){
        if(root == null) return 0;
        int length = (parent != null && parent.val + 1 == root.val) ? len + 1 : 1;
        int left = dfs(root.left, root, length);
        int right = dfs(root.right, root, length);
        return Math.max(length, Math.max(left, right));
    }

    /**
     * Description
     * Given a binary tree, find the length(number of nodes) of the longest consecutive sequence(Monotonic and adjacent node values differ by 1) path.
     * The path could be start and end at any node in the tree
     *
     * Example
     * Example 1:
     *
     * Input:
     * {1,2,0,3}
     * Output:
     * 4
     * Explanation:
     *     1
     *    / \
     *   2   0
     *  /
     * 3
     * 0-1-2-3
     * Example 2:
     *
     * Input:
     * {3,2,2}
     * Output:
     * 2
     * Explanation:
     *     3
     *    / \
     *   2   2
     * 2-3
     */

    public int longestConsecutive2(TreeNode root){
        if(root == null) return 0;
        return dfs(root).maxLen;
    }

    private Result dfs(TreeNode root){
        if(root == null) return new Result(0, 0, 0);
        Result left = dfs(root.left);
        Result right = dfs(root.right);
        int down = 0, up = 0;
        if(root.left != null && root.left.val + 1 == root.val){
            down = Math.max(down, left.maxDown + 1);
        }
        if(root.left != null && root.left.val - 1 == root.val){
            up = Math.max(up, left.maxUp + 1);
        }
        if(root.right != null && root.right.val + 1 == root.val){
            down = Math.max(down, right.maxDown + 1);
        }
        if(root.right != null && root.right.val - 1 == root.val){
            up = Math.max(up, right.maxUp + 1);
        }
        int len = down + 1 + up;
        len = Math.max(len, Math.max(left.maxLen, right.maxLen));
        return new Result(len, down, up);
    }

    /**
     * Description
     * It's follow up problem for Binary Tree Longest Consecutive Sequence II
     *
     * Given a k-ary tree, find the length of the longest consecutive sequence path.
     * The path could be start and end at any node in the tree
     *
     * Example
     * Example 1:
     *
     * Input:
     * 5<6<7<>,5<>,8<>>,4<3<>,5<>,31<>>>
     * Output:
     * 5
     * Explanation:
     *      5
     *    /   \
     *   6     4
     *  /|\   /|\
     * 7 5 8 3 5 31
     *
     * return 5, // 3-4-5-6-7
     */
    public class MultiTreeNode {
        int val;
        List<MultiTreeNode> children;
        MultiTreeNode(int val){
            this.val = val;
        }
    }

    // 为了能够优化时间复杂度，我们经常希望在一次遍历就得到答案。所以经常会构建一些能让我一次记录更多信息的数据结构。下面就是构建常用的构建手段
    class Result {
        public int maxLen, maxDown, maxUp;
        Result(int len, int down, int up){
            maxLen = len;
            maxDown = down;
            maxUp = up;
        }
    }

    public int longestConsecutive(MultiTreeNode root) {
        if(root == null) return 0;
        return dfs(root).maxLen;
    }

    private Result dfs(MultiTreeNode root){
        if(root == null) {
            return new Result(0, 0, 0);
        }
        int maxLen = 1, down = 0, up = 0;
        for(MultiTreeNode child : root.children){
            Result type = dfs(child);
            if(child.val + 1 == root.val){
                down = Math.max(down, type.maxLen + 1);
            }
            if(child.val - 1 == root.val){
                up = Math.max(up, type.maxLen + 1);
            }
            maxLen = Math.max(maxLen, type.maxLen);
        }
        maxLen = Math.max(down + 1 + up, maxLen);
        return new Result(maxLen, down, up);
    }
}
