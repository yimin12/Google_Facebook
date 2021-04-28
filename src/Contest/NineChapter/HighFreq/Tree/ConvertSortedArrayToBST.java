package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.ListNode;
import DataStructure.AlgoUtils.TreeNode;

/**
 * Description
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example
 * Example 1:
 *
 * Input: [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,#,5]
 * Explanation:
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * For this tree, you function need to return a tree node which value equals 0.
 */
public class ConvertSortedArrayToBST {


    /**
     * This is also the way of creating bst with minimum height
     * @param nums
     * @return
     */
    public TreeNode convertSortedArraytoBinarySearchTree(int[] nums) {
        if(nums == null || nums.length == 0) return null;
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right){
        if(left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }

    /**
     * Convert Sorted List to Binary Search Tree
     * Description
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     *
     * Example
     * Example 1:
     *
     * Input:
     * 1->2->3
     * Output:
     *   2
     *  / \
     * 1   3
     * Example 2:
     *
     * Input:
     * 2->3->6->7
     * Output:
     *    3
     *   / \
     *  2   6
     *       \
     *        7
     */

    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        TreeNode root = dfs(head);
        return root;
    }

    private TreeNode dfs(ListNode head){
        if(head == null) return null;
        if(head.next == null) return new TreeNode(head.value);
        ListNode pre = findMid(head);
        ListNode mid = pre.next;

        TreeNode root = new TreeNode(mid.value);
        root.right = dfs(mid.next);
        pre.next = null; // de-link
        root.left = dfs(head);
        return root;
    }

    private ListNode findMid(ListNode head){
        ListNode slow = head, fast = head.next;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
