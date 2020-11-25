package Algorithm.Graph.Tree.Basic;

import DataStructure.AlgoUtils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/18 22:54
 *   @Description :
 *      Description: Given a binary Tree, determine whether it is balanced

 */
public class CheckTrees {

    // Q1: is this balanced
    public boolean isBalance(TreeNode root){
        if(root == null){
            return true;
        }
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root){
        if(root == null)
            return 0;
        int left = getHeight(root.left);
        if(left == -1)
            return -1;
        int right = getHeight(root.right);
        if(right == -1)
            return -1;
        if(Math.abs(left - right) > 1){
            return -1;
        }
        return Math.abs(left - right) + 1;
    }


    // Q2: is this symmetric
    public boolean isSymmetric(TreeNode root){
        if(root == null){
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode one, TreeNode two){
        if(one == null && two == null){
            return true;
        } else if(one == null || two == null){
            return false;
        } else if(one.val != two.val){
            return false;
        } else {
            return isSymmetric(one.left, two.right) && isSymmetric(one.right, two.left);
        }
    }

    // Q3: is this tweakedIdentical
    public boolean isTweaked(TreeNode one, TreeNode two){
        if(one == null && two == null){
            return true;
        } else if(one == null || two == null){
            return false;
        } else if(one.val != two.val){
            return false;
        } else {
            return isTweaked(one.left, two.left) && isTweaked(one.right, two.right) || isTweaked(one.left, two.right) && isTweaked(one.right, two.left);
        }
    }

    //  Q4: is this Binary Search Tree
    public boolean isBST(TreeNode root){
        if(root == null)
            return true;
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(TreeNode root, int min, int max){
        if(root == null){
            return false;
        }
        if(root.val < min || root.val > max){
            return false;
        }
        return isBST(root.left, min, root.val - 1) && isBST(root.right, root.val + 1, max);
    }

    // Q5: is this Complete Binary Tree
    public boolean isCompleted(TreeNode root){
        if(root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = false;
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if(cur == null){
                flag = true;
            }
            if(cur != null){
                if(flag){
                    return false;
                }
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return true;
    }
}
