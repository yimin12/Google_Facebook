package Algorithm.Graph.Tree.Basic;

import DataStructure.AlgoUtils.TreeNode;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/21 21:57
 *   @Description :
 *      Basic function of Binary Search
 */
public class BSTOperation {

    // Search function, recursive way
    public TreeNode search(TreeNode root, int target){
        if(root == null || root.val == target){
            return root;
        }
        if(target < root.val){
            return search(root.left, target);
        } else {
            return search(root.right, target);
        }
    }
    // Search function, iterative way
    public TreeNode searchI(TreeNode root, int target){
        if(root == null){
            return null;
        }
        TreeNode cur = root;
        while(cur != null){
            if(cur.val < target){
                cur = cur.right;
            } else if(cur.val > target){
                cur = cur.left;
            } else {
                return cur;
            }
        }
        return cur;
    }

    // Insert function, recursive way
    public TreeNode insert(TreeNode root, int target){
        if(root == null){
            TreeNode newRoot = new TreeNode(target);
            return newRoot;
        }
        if(root.val < target){
            root.right = insert(root.right, target);
        }
        if(root.val > target){
            root.left = insert(root.left, target);
        }
        return root;
    }

    // Insert function, iterative way
    public TreeNode insertI(TreeNode root, int target){
        TreeNode newRoot = new TreeNode(target);
        if(root == null){
            return newRoot;
        }
        TreeNode cur = root;
        while(cur.val != target){
            if(cur.val > target){
                if(cur.left != null){
                    cur = cur.left;
                } else {
                    cur.left = newRoot;
                    break;
                }
            } else{
                if(cur.right != null){
                    cur = cur.right;
                } else {
                    cur.right = newRoot;
                    break;
                }
            }
        }
        return root;
    }

    // delete function, iterative way
    public TreeNode delete(TreeNode root, int target){
        if(root == null){
            return root;
        }
        if(root.val == target){
            if(root.left == null){
                return root.right;
            } else if(root.left  == null){
                return root.left;
            } else if(root.right.left == null){
                root.right.left = root.left;
                return root.right;
            } else {
                TreeNode newRoot = findSmallest(root.right);
                newRoot.left = root.left;
                newRoot.right = root.right;
            }
        } else if(root.val < target){
            root.right = delete(root.right, target);
        } else {
            root.left = delete(root.left, target);
        }
        return root;
    }
    private TreeNode findSmallest(TreeNode root){
        while(root.left.left != null){
            root = root.left;
        }
        TreeNode smallest = root.left;
        // unlinked it
        root.left = root.left.right;
        return smallest;
    }
}
