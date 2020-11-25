package Algorithm.Graph.Tree.Basic;

import DataStructure.AlgoUtils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/19 23:08
 *   @Description :
 *      serialization and deserialization
 */
public class TreeRepresentation {
    // First kind of BFS
    public String serialize(TreeNode root){
        if(root == null){
            return "{}";
        }
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        for(int i = 0; i < queue.size(); i++){
            TreeNode cur = queue.get(i);
            if(cur == null){
                continue;
            }
            queue.add(cur.left);
            queue.add(cur.right);
        }
        while(queue.get(queue.size() - 1) == null){
            queue.remove(queue.size() - 1); // remove the leaf node
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(queue.get(0).val);
        for(int i = 1; i < queue.size(); i++){
            if(queue.get(i) == null){
                sb.append(",#");
            } else {
                sb.append(",");
                sb.append(queue.get(i).val);
            }
        }
        sb.append("}");
        return sb.toString();
    }
    // Second version of BFS
    public String serializeII(TreeNode root){
        if(root == null){
            return "";
        }
        List<TreeNode> nodes = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            nodes.add(cur);
            if(cur == null){
                continue;
            }
            queue.add(cur.left);
            queue.add(cur.right);
        }
        while(nodes.get(nodes.size() - 1) == null){
            nodes.remove(nodes.size() - 1);
        }
        // do the serialization
        StringBuffer result = new StringBuffer();
        result.append("{");
        for(int i = 0; i < nodes.size(); i++){
            if(i > 0){
                result.append(",");
            }
            if(nodes.get(i) == null){
                result.append("#");
            } else {
                result.append(Integer.toString(nodes.get(i).val));
            }
        }
        result.append("}");
        return result.toString();
    }

    public TreeNode deserialize(String data){
        if(data.equals("{}")){
            return null;
        }
        String[] buffer = data.substring(1, data.length() - 1).split(","); // get rid of {} and split by token
        int n = buffer.length;
        TreeNode root = getTreeNode(buffer[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        int pointer = 1;
        queue.add(root);
        // reverse bfs
        while(!queue.isEmpty()){
            if(pointer < n){
                int levelSize = queue.size();
                for(int i = 0; i < levelSize; i++){
                    TreeNode node = queue.poll();
                    node.left = getTreeNode(buffer[pointer]);
                    if(node.left != null){
                        queue.add(node.left);
                    }
                    pointer++;
                    if(pointer == n){
                        break;
                    }
                    node.right = getTreeNode(buffer[pointer]);
                    if(node.right != null){
                        queue.add(root.right);
                    }
                    pointer++;
                    if(pointer == n){
                        break;
                    }
                }
            } else {
                break;
            }
        }
        return root;
    }
    private TreeNode getTreeNode(String source){
        if(source == "#"){
            return null;
        } else {
            return new TreeNode(Integer.parseInt(source));
        }
    }
}
