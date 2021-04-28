package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description
 * Design an algorithm and write code to serialize and deserialize a binary tree. Writing the tree to a file is called 'serialization' and reading back from the file to reconstruct the exact same binary tree is 'deserialization'.
 *
 * There is no limit on how to serialize or deserialize a binary tree，you just need to ensure the binary tree can be serialized to a string，and the string can be deserialized to original binary tree.
 *
 * There is no limit of how you deserialize or serialize a binary tree, LintCode will take your output of serialize as the input of deserialize, it won't check the result of serialize.
 *
 * Example
 * Example 1:
 *
 * Input:
 *
 * tree = {3,9,20,#,#,15,7}
 * Output:
 *
 * {3,9,20,#,#,15,7}
 * Explanation:
 *
 * Binary tree {3,9,20,#,#,15,7}, denote the following structure:
 * 　　　3
 * 　　/　＼
 * 　　9　20
 * 　　　/　＼
 * 　　　15　　7
 * it will be serialized {3,9,20,#,#,15,7}
 */
public class TreeSerialization {

    public String serialize(TreeNode root) {
        if(root == null) return "{}";
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.offer(root);
        sb.append("{");
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if(cur == null){
                sb.append("#");
            } else {
                sb.append(cur.val);
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            if(!queue.isEmpty()){
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.equals("{}")) {
            return null;
        }
        String[] val = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(val[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isLeftChild = true; // identify whether is left or right
        for (int i = 1; i < val.length; i++) {
            if (!val[i].equals("#")) {
                TreeNode child = new TreeNode(Integer.parseInt(val[i]));
                if (isLeftChild) {
                    queue.peek().left = child;
                } else {
                    queue.peek().right = child;
                }
                queue.offer(child);
            }
            if (!isLeftChild) {
                queue.poll();
            }
            isLeftChild = !isLeftChild;
        }
        return root;
    }

}
