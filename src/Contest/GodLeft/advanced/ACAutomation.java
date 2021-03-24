package Contest.GodLeft.advanced;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/24 0:30
 *   @Description :
 *
 */
public class ACAutomation {

    // preparation for trie tree
    public static class Node{

        public int end; // need record the rank of current node
        public String endWord;
        public Node fail;
        public Node[] children;

        public Node(){
            endWord = null;
            end = 0;
            fail = null;
            children = new Node[26]; // Assume a..z in lower case
        }
    }

    // Automation for character is implemented by trie tree
    public static class Automation{

        private Node root;

        public Automation(){
            root = new Node();
        }

        public void insert(String s){
            char[] str = s.toCharArray();
            Node cur = root;
            int index = 0;
            for(int i = 0; i < str.length; i ++){
                index = str[i] - 'a';
                if(cur.children[index] == null){
                    Node next = new Node();
                    cur.children[index] = next;
                }
                cur = cur.children[index];
            }
            cur.end ++;
            cur.endWord = s;
        }

        // build automation
        public void build(){
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(root);
            Node cur = null, cfail = null;
            while(!queue.isEmpty()){
                cur = queue.poll();
                for(int i = 0; i < 26; i ++){
                    if(cur.children[i] != null){
                        cur.children[i].fail = root;
                        cfail = cur.fail;
                        while(cfail != null){
                            if(cfail.children[i] != null){
                                cur.children[i].fail = cfail.children[i];
                                break;
                            }
                            cfail = cfail.fail;
                        }
                        queue.add(cur.children[i]);
                    }
                }
            }
        }

        public int containNum(String content){
            char[] str = content.toCharArray();
            Node cur = root, follow = null;
            int index = 0, res = 0;
            for(int i = 0; i < str.length; i ++){
                index = str[i] - 'a';
                while(cur.children[index] == null && cur != root){
                    cur = cur.fail;
                }
                cur = cur.children[index] != null ? cur.children[index] : root;
                follow = cur;
                while(follow != root){
                    if(follow.end == -1) {
                        // has been visited
                        break;
                    }
                    // modify the function to suit yourself
                    res += follow.end;
                    follow.end = -1;
                    follow = follow.fail;
                }
            }
            return res;
        }

        public List<String> containWords(String content) {
            char[] str = content.toCharArray();
            Node cur = root;
            Node follow = null;
            int index = 0;
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                while (cur.children[index] == null && cur != root) {
                    cur = cur.fail;
                }
                cur = cur.children[index] != null ? cur.children[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.endWord == null) {
                        break;
                    }
                    { // 不同的需求，在这一段{ }之间修改
                        ans.add(follow.endWord);
                        follow.endWord = null;
                    } // 不同的需求，在这一段{ }之间修改
                    follow = follow.fail;
                }
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        Automation ac = new Automation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("c");
        ac.build();
        System.out.println(ac.containNum("cdhe"));
        List<String> contains = ac.containWords("cdhe");
        for (String word : contains) {
            System.out.println(word);
        }
    }
}
