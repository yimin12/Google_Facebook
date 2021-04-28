package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * You may assume that duplicates do not exist in the tree.
 *
 * Example
 * Example 1:
 *
 * Input：[],[]
 * Output：{}
 * Explanation:
 * The binary tree is null
 * Example 2:
 *
 * Input：[1,2,3],[1,3,2]
 * Output：{2,1,3}
 * Explanation:
 * The binary tree is as follows
 *   2
 *  / \
 * 1   3
 */
public class ConstructTreeWithGivenOrder {

    /**
     * Build tree with postOrder and inOrder
     */
    public TreeNode buildTreeInPost(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null || inorder.length != postorder.length) return null;
        int n = inorder.length;
        Map<Integer, Integer> inOrderIndexes = new HashMap<>();
        for(int i = 0; i < n; i ++){
            inOrderIndexes.put(inorder[i], i);
        }
        return buildTreeInPost(inorder, 0, n - 1, postorder, 0, n - 1, inOrderIndexes);
    }

    private TreeNode buildTreeInPost(int[] in, int inLeft, int inRight, int[] post, int postLeft, int postRight, Map<Integer, Integer> map){
        if(postLeft > postRight || inLeft > inRight){
            return null;
        }
        TreeNode root = new TreeNode(post[postRight]);
        int inIndex = map.get(post[postRight]);
        root.left = buildTreeInPost(in, inLeft, inIndex - 1, post, postLeft, postLeft + inIndex - inLeft - 1, map);
        root.right = buildTreeInPost(in, inIndex + 1, inRight, post, postLeft + inIndex - inLeft, postRight - 1, map);
        return root;
    }

    /**
     * Build tree with preOrder and inOrder
     */
    public TreeNode buildTreeInPre(int[] inorder, int[] preorder){
        if(inorder == null || preorder == null || inorder.length != preorder.length) return null;
        int n = inorder.length;
        Map<Integer, Integer> inOrderIndexes = new HashMap<>();
        for(int i = 0; i < n; i ++){
            inOrderIndexes.put(inorder[i], i);
        }
        return buildTreeInPre(inorder, 0, n - 1, preorder, 0, n - 1, inOrderIndexes);
    }

    private TreeNode buildTreeInPre(int[] in, int inLeft, int inRight, int[] pre, int preLeft, int preRight, Map<Integer, Integer> map){
        if(preLeft > preRight || inLeft > inRight){
            return null;
        }
        TreeNode root = new TreeNode(pre[preLeft]);
        int inIndex = map.get(pre[preLeft]);
        root.left = buildTreeInPre(in, inLeft, inIndex - 1, pre, preLeft + 1, preLeft + inIndex - inLeft, map);
        root.right = buildTreeInPre(in, inIndex + 1, inRight, pre, preLeft + inIndex - inLeft + 1, preRight, map);
        return root;
    }

    /**
     * Build tree with preOrder and postOrder, Return any binary tree that matches the given preorder and postorder traversals
     */
    int preIndex = 0, postIndex = 0;
    public TreeNode buildTreePrePost(int[] pre, int[] post){
        TreeNode root = new TreeNode(pre[preIndex ++]);
        if(root.val != post[postIndex]){
            root.left = buildTreePrePost(pre, post);
        }
        if(root.val != post[postIndex]){
            root.right = buildTreePrePost(pre, post);
        }
        postIndex ++;
        return root;
    }
}
