package Algorithm.Graph.Tree.Basic;

import DataStructure.AlgoUtils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/21 23:17
 *   @Description :
 *      Description:
 *          Get keys in binary search tree in given range
 */
public class BSTGetRange {

    public List<Integer> getRange(TreeNode root, int min, int max){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        getRange(root, min, max, res);
        return res;
    }

    private void getRange(TreeNode root, int min, int max, List<Integer> res) {
        // base case
        if(root == null){
            return;
        }
        if(root.val < min){
            getRange(root.right, min, max, res);
        }
        // inorder to guarantee sorted in ascending order
        if(root.val < max && root.val > min){
            res.add(root.val);
        }
        if(root.val > max){
            getRange(root.left, min, max, res);
        }

    }
}
