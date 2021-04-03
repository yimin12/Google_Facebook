package Contest.Bloomberg;

import DataStructure.AlgoUtils.TreeNode;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/3 13:43
 *   @Description :
 *
 */
public class CountCompleteBinaryTreeNodes {

    public int countNodes(TreeNode root) {
        int height = getHeight(root);
        return height < 0 ? 0 : getHeight(root.right) == height - 1 ? (1 << height) + countNodes(root.right) : (1 << height - 1) + countNodes(root.left);
    }

    public int getHeight(TreeNode root){
        return root == null ? -1 : 1 + getHeight(root.left); // O(logn time)
    }
}
