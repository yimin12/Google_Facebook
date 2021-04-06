package Contest.Bloomberg;

import DataStructure.AlgoUtils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/2 22:02
 *   @Description :
 *
 */
public class ConstructBinaryTree {

    // build with postOrder and inOrder array
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null || inorder.length != postorder.length){
            return null;
        }
        int n = inorder.length;
        Map<Integer, Integer> inorderIndex = new HashMap<>();
        for(int i = 0; i < n; i ++){
            inorderIndex.put(inorder[i], i); // map the value to index
        }
        return build(inorder, 0, n - 1, postorder, 0, n - 1, inorderIndex);
    }

    private TreeNode build(int[] in, int inLeft, int inRight, int[] post, int postLeft, int postRight, Map<Integer, Integer> map){
        if(postLeft > postRight || inLeft > inRight){
            return null;
        }
        TreeNode root = new TreeNode(post[postRight]); // root start from right in post order
        int inIndex = map.get(post[postRight]);
        root.left = build(in, inLeft, inIndex - 1, post, postLeft , postLeft + inIndex - inLeft - 1, map);
        root.right = build(in, inIndex + 1, inRight, post, postLeft + inIndex - inLeft , postRight - 1, map);
        return root;
    }

    // build with postOrder and preOrder
    int preIndex = 0, postIndex = 0;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        TreeNode root = new TreeNode(pre[preIndex ++]);

        // if root.val == post[postIndex] -> leaf node, time to build right subtree
        if(root.val != post[postIndex]){
            root.left = constructFromPrePost(pre, post);
        }
        if(root.val != post[postIndex]){
            root.right = constructFromPrePost(pre, post);
        }
        postIndex ++;
        return root;
    }


    private Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode constructFromPreInorder(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length != inorder.length){
            return null;
        }
        int n = preorder.length;
        for(int i = 0; i < n; i ++){
            inorderMap.put(inorder[i], i); // the index of root in inorder array
        }
        return construct(preorder, 0, 0, n-1);
    }

    private TreeNode construct(int[] pre, int cur, int start, int end){
        TreeNode root = new TreeNode(pre[cur]);
        if(start < end){
            int rootIndex = inorderMap.get(pre[cur]);
            if(rootIndex > start){
                root.left = construct(pre, cur + 1, start, rootIndex - 1);
            }
            if(rootIndex < end){
                root.right = construct(pre, cur + rootIndex - start + 1, rootIndex + 1, end);
            }
        }
        return root;
    }

}
