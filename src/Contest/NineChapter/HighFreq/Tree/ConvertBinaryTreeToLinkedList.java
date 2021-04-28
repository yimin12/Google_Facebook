package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.ListNode;
import DataStructure.AlgoUtils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth (e.g., if you have a tree with depth D, you'll have D linked lists).
 *
 * Example
 * Example 1:
 *
 * Input: {1,2,3,4}
 * Output: [1->null,2->3->null,4->null]
 * Explanation:
 *         1
 *        / \
 *       2   3
 *      /
 *     4
 * Example 2:
 *
 * Input: {1,#,2,3}
 * Output: [1->null,2->null,3->null]
 * Explanation:
 *     1
 *      \
 *       2
 *      /
 *     3
 */
public class ConvertBinaryTreeToLinkedList {

    /**
     * Convert binary to single linked list
     * @param root
     * @return
     */
    public List<ListNode> binaryTreeToLists(TreeNode root) {
        List<ListNode> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        ListNode dummy = new ListNode(-1);
        ListNode c = null;
        while(!queue.isEmpty()){
            dummy.next = null;
            c = dummy;
            int size = queue.size();
            for(int i = 0; i < size; i ++){
                TreeNode cur = queue.poll();
                c.next = new ListNode(cur.val);
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
                c = c.next;
            }
            res.add(dummy.next);
        }
        return res;
    }

    /**
     * Description
     * Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
     *
     * Let's take the following BST as an example, it may help you understand the problem better:
     *
     * bstdlloriginalbst
     *
     * We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
     *
     * The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.
     *
     * bstdllreturndll
     *
     * Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.
     *
     * The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
     *
     * bstdllreturnbst
     *
     * Example
     * Example 1:
     *
     * Input: {4,2,5,1,3}
     *         4
     *        /  \
     *       2   5
     *      / \
     *     1   3
     * Output: "left:1->5->4->3->2  right:1->2->3->4->5"
     * Explanation:
     * Left: reverse output
     * Right: positive sequence output
     */
    TreeNode dummy = new TreeNode(0);
    TreeNode pre = dummy;
    public TreeNode treeToDoublyList(TreeNode root) {
        if(root == null) return null;
        connect(root);
        pre.right = dummy.right;
        dummy.right.left = pre;
        return pre.right;
    }

    private void connect(TreeNode root){
        if (root == null) return;
        connect(root.left);
        root.left = pre;
        pre.right = root;
        pre = root;
        connect(root.right);
    }
}
