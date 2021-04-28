package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

/**
 * Description
 * Flatten a binary tree to a fake "linked list" in pre-order traversal.
 *
 * Here we use the right pointer in TreeNode as the next pointer in ListNode.
 *
 * Don't forget to mark the left child of each node to null. Or you will get Time Limit Exceeded or Memory Limit Exceeded.
 *
 * Example
 * Example 1:
 *
 * Input:{1,2,5,3,4,#,6}
 * Output：{1,#,2,#,3,#,4,#,5,#,6}
 * Explanation：
 *      1
 *     / \
 *    2   5
 *   / \   \
 *  3   4   6
 *
 * 1
 * \
 *  2
 *   \
 *    3
 *     \
 *      4
 *       \
 *        5
 *         \
 *          6
 */
public class FlattenBinaryTreeToLinkedList {

    private TreeNode last = null;
    public void flatten(TreeNode root) {
        if(root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = last;
        root.left = null;
        last = root;
    }
}
