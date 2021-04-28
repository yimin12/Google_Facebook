package Contest.NineChapter.HighFreq.Tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Description
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.
 *
 *      _9_
 *     /   \
 *    3     2
 *   / \   / \
 *  4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
 *
 * Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.
 *
 * Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
 *
 * You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".
 *
 * Example
 * Example 1:
 * 	Input:  tree = "#"
 * 	Output:  true
 *
 * 	Explanation:
 * 	Empty tree is legal.
 *
 * Example 2:
 * 	Input: tree = "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * 	Output:  true
 *
 * Example 3:
 * 	Input: tree = "1,#"
 * 	Output:  false
 *
 * 	Explanation:
 * 	It's not a complete tree.
 *
 * Example 4:
 * 	Input: tree = "9,#,#,1"
 * 	Output:  false
 *
 * 	Explanation:
 * 	It's not a tree.
 */
public class VerifyPreorderSerializationOfBinaryTree {

    // 消消乐的想法，跟Candy Crush类似
    // O(n) time and O(n) space
    public boolean isValidSerialization(String preorder) {
        String[] ss = preorder.split(",");
        Deque<String> s = new LinkedList<>();
        for(int i = 0; i < ss.length; i ++){
            if(ss[i].equals("#")){
                while(!s.isEmpty() && s.peek().equals("#")){
                    s.pop();
                    if(s.isEmpty() || s.peek().equals("#")){
                        return false;
                    }
                    s.pop();
                }
            }
            s.push(ss[i]);
        }
        return s.size() == 1 && s.peek().equals("#");
    }
}
