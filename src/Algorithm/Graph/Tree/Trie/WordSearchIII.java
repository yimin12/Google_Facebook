package Algorithm.Graph.Tree.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/26 18:54
 *   @Description :
 *         Given a matrix of lower alphabets and a dictionary. Find maximum words in the dictionary that can be found in the matrix in the meantime. A word can start from any position in the matrix and go left/right/up/down to the adjacent position. One character only be used once in the matrix. No same word in dictionary
            Have you met this question in a real interview?
            Example
            Example 1:

            Input：["doaf","agai","dcan"]，["dog","dad","dgdg","can","again"]
            Output：2
            Explanation：
              d o a f
              a g a i
              d c a n
            search in Matrix, you can find `dog` and `can` in the meantime.
            Example 2:

            Input：["a"]，["b"]
            Output：0
            Explanation：
             a
            search in Matrix，return 0.
 */
public class WordSearchIII {

    // Idea is similar with WordSearchII
    class TrieNode{
        String word;
        Map<Character, TrieNode> children;
        public TrieNode(){
            word = null;
            children = new HashMap<>();
        }
    }
    class Trie{
        TrieNode root;
        public Trie(TrieNode root){
            this.root = root;
        }
        public void insert(String word){
            // step 1: get the root
            TrieNode node = root;
            for(int i = 0; i < word.length(); i++){
                if(!node.children.containsKey(word.charAt(i))){
                    node.children.put(word.charAt(i), new TrieNode());
                }
                // get to the next level
                node = node.children.get(word.charAt(i));
            }
            node.word = word;
        }
    }

    public int[] dx = {1, 0, -1, 0};   //搜索方向
    public int[] dy = {0, 1, 0, -1};

    public int wordSearchIII(char[][] board, List<String> words){
        if(board == null || board.length == 0 || board[0].length == 0 || words == null){
            return 0;
        }
        int m = board.length, n = board[0].length;
        List<String> results = new ArrayList<>();
        int[] res = new int[1];
        res[0] = 0;
        Trie tree = new Trie(new TrieNode());
        for(String word : words){
            tree.insert(word);
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                search(board, i, j, i, j, tree.root, tree.root, results, res);
            }
        }
        return res[0];
    }
    public void search(char[][] board, int x, int y, int start_x, int start_y, TrieNode cur, TrieNode root, List<String> results, int[] res){
        if(!cur.children.containsKey(board[x][y])){
            return;
        }
        TrieNode child = cur.children.get(board[x][y]);
        char tmp = board[x][y];
        board[x][y] = 0;
        // base case( contains another recursion)
        if(child.word != null){
            // Once you find one possible solution, add it to results, then erase its word and keep diving
            String tmpstr = child.word;
            results.add(tmpstr);
            child.word = null;
            res[0] = Math.max(res[0], results.size());
            for(int i = start_x; i < board.length; i++){
                int start_j = 0;
                if(i == start_x){
                    start_j = start_y + 1;
                }
                for(int j = start_j; j < board[0].length; j++){
                    if(board[i][j] != 0){
                        search(board, i, j, i, j, root, root, results, res); // keep diving
                    }
                }
            }
            results.remove(results.size() - 1);
            child.word = tmpstr;
        }
        for(int i = 0; i < 4; i++){
            if(!isValid(board, x + dx[i], y + dy[i])){
                continue;
            }
            search(board, x + dx[i], y + dy[i], start_x, start_y, child, root, results, res);
        }
        board[x][y] = tmp;
    }
    private boolean isValid(char[][] board, int x, int y){
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length){
            return false;
        }
        return board[x][y] != 0;
    }
}
