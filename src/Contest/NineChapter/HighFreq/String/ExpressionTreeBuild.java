package Contest.NineChapter.HighFreq.String;

import java.util.Deque;
import java.util.LinkedList;

public class ExpressionTreeBuild {

    public class ExpressionTreeNode {
       public String symbol;
       public ExpressionTreeNode left, right;
       public ExpressionTreeNode(String symbol) {
           this.symbol = symbol;
           this.left = this.right = null;
       }
   }

    class TreeNode {
        public int val;
        public ExpressionTreeNode eNode;
        public TreeNode(int val, String s) {
            this.val = val;
            eNode = new ExpressionTreeNode(s);
        }
    }

   public ExpressionTreeNode build(String[] expression){
        if(expression == null || expression.length == 0) return null;
        Deque<TreeNode> stack = new LinkedList<>();
        int base = 0, val = 0;
        for(int i = 0; i < expression.length; i ++){
            if(expression[i].equals("(")){
                base += 10;
                continue;
            }
            if(expression[i].equals(")")){
                base -= 10;
                continue;
            }
            val = getWeight(base, expression[i]);
            TreeNode node = new TreeNode(val, expression[i]);
            while(!stack.isEmpty() && node.val <= stack.peek().val){
                node.eNode.left = stack.pop().eNode;
            }
            if(!stack.isEmpty()){
                stack.peek().eNode.right = node.eNode;
            }
            stack.push(node);
        }
        if(stack.isEmpty()){
            return null;
        }
        TreeNode rst = stack.pop();
        while(!stack.isEmpty()){
            rst = stack.pop();
        }
        return rst.eNode;
   }

   private int getWeight(int base, String s){
        if(s.equals("+") || s.equals("-")){
            return base + 1;
        }
        if(s.equals("*") || s.equals("/")){
            return base + 2;
        }
        return Integer.MAX_VALUE;
   }
}
