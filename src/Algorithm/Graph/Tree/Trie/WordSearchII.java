package Algorithm.Graph.Tree.Trie;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/26 14:57
 *   @Description :
        Given a matrix of lower alphabets and a dictionary. Find all words in the dictionary that can be found in the matrix. A word can start from any position in the matrix and go left/right/up/down to
         the adjacent position. One character only be used once in one word. No same word in dictionary
        Example 1:
        Input：["doaf","agai","dcan"]，["dog","dad","dgdg","can","again"]
        Output：["again","can","dad","dog"]
        Explanation：
          d o a f
          a g a i
          d c a n
        search in Matrix，so return ["again","can","dad","dog"].
 */
public class WordSearchII {

    // Use Trie
    class TrieNode{
        String word;
        HashMap<Character, TrieNode> children;
        public TrieNode(){
            this.word = null;
            this.children = new HashMap<>();
        }
    }
    class Trie{
        TrieNode root;
        public Trie(TrieNode trieNode){
            this.root = trieNode;
        }
        public void insert(String word){
            // insert the word to dictionary
            TrieNode node = root;
            for(int i = 0; i < word.length(); i++){
                if(!node.children.containsKey(word.charAt(i))){
                    node.children.put(word.charAt(i), new TrieNode());
                }
                node = node.children.get(word.charAt(i));
            }
            // mark after inserting all char of the string
            node.word = word;
        }
    }

    public int[] dx = {1,0,-1,0};
    public int[] dy = {0,1,0,-1};

    // main function
    public List<String> wordSearchII(char[][] board, List<String> words){
        // sanity check
        if(board == null || board.length == 0 || board[0].length == 0 || words == null){
            return new ArrayList<>();
        }
        int m = board.length, n = board[0].length;
        List<String> res = new ArrayList<>();
        Trie tree = new Trie(new TrieNode()); // dummy root, trie do not start with specific character
        // Construct the tree
        for(String word:words){
            tree.insert(word);
        }
        for(int i = 0 ; i < m; i++){
            for(int j = 0; j < n; j++){
                search(board, i, j, tree.root, res);
            }
        }
        return res;
    }
    public void search(char[][] board, int x, int y, TrieNode root, List<String> results){
        if(!root.children.containsKey(board[x][y])){
            return;
        }
        TrieNode child = root.children.get(board[x][y]);
        // base case
        if(child.word != null){
            // List has contains api
            if(!results.contains(child.word)){
                results.add(child.word);
            }
        }
        char tmp = board[x][y];
        board[x][y] = 0; // mark as used
        for(int i = 0; i < 4; i++){
            if(!isValid(board, x + dx[i], y + dy[i])){
                // if invalid, pruning it
                continue;
            }
            // start recursion
            search(board, x + dx[i], y + dy[i], child, results);
        }
        // backtracking
        board[x][y] = tmp;
    }
    private boolean isValid(char[][] board, int x, int y){
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length){
            return false;
        }
        return board[x][y] != 0;
    }

    // pure dfs
    public List<String> wordSearchIIDFS(char[][] board, List<String> words){
        if(board == null || board.length == 0 || board[0].length == 0){
            return new ArrayList<>();
        }
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        Map<String, Boolean> prefixIsWord = getPrefixSet(words);
        Set<String> wordSet = new HashSet<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                visited[i][j] = true;
                dfs(board, visited, i, j, String.valueOf(board[i][j]), prefixIsWord, wordSet);
                visited[i][j] = false;
            }
        }
        return new ArrayList<>();
    }
    private Map<String, Boolean> getPrefixSet(List<String> words){
        Map<String, Boolean> prefixIsWord = new HashMap<>();
        for(String word:words){
            for(int i = 0; i < word.length() - 1; i++){
                String prefix = word.substring(0, i + 1);
                if(!prefixIsWord.containsKey(prefix)){
                    prefixIsWord.put(prefix, false);
                }
            }
            prefixIsWord.put(word, true);
        }
        return prefixIsWord;
    }
    private void dfs(char[][] board, boolean[][] visited, int x, int y, String word, Map<String, Boolean> prefixIsWord, Set<String> wordSet){
        if(!prefixIsWord.containsKey(word)){
            return;
        }
        if(prefixIsWord.get(word)){
            wordSet.add(word);
        }
        for(int i=0; i < 4; i++){
            int neiX = x + dx[i];
            int neiY = y + dy[i];
            if(isValid(board, neiX, neiY)){
                visited[neiX][neiY] = true;
                dfs(board, visited, neiX, neiY, word + board[neiX][neiY], prefixIsWord, wordSet);
                visited[neiX][neiY] = false;
            }
        }
    }
}
