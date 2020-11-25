package Algorithm.Graph.Tree.Basic;

import DataStructure.AlgoUtils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/21 23:26
 *   @Description :
*       Assumption:
* 	        The BST is not null and the k >=0
*       Examples:
	        5
	     /    \
	    2      11
	         /    \
	        6     14
 */
public class BSTKthSmallest {

    int k = 0;
    public List<Integer> kthSmallest(TreeNode root, int k){
        List<Integer> res = new ArrayList<>();
        if(root == null || k < 0){
           return res;
       }
        this.k = k;
        inOrder(root, res);
        return res;
    }
    private void inOrder(TreeNode root, List<Integer> res){
        if(root == null || k < 0){
            return;
        }
        inOrder(root.left, res);
        if(--k >= 0){
            res.add(root.val);
        }
        inOrder(root.right, res);
    }

    public static void main(String[] args) {
        TreeNode one = new TreeNode(5);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(11);
        TreeNode four = new TreeNode(6);
        TreeNode five = new TreeNode(14);
        one.left = two;
        one.right = three;
        three.left = four;
        three.right = five;

        BSTKthSmallest solution = new BSTKthSmallest();
        List<Integer> res = solution.kthSmallest(one, 3);
        System.out.println(res.toString());
    }
}
