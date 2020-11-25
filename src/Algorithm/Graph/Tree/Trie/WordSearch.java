package Algorithm.Graph.Tree.Trie;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/26 14:27
 *   @Description :
 *      Given a 2D board and a word, find if the word exists in the grid.
 *      The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
 *      The same letter cell may not be used more than once.
 *   Input：["ABCE","SFCS","ADEE"]，"ABCCED"
        Output：true
        Explanation：
        [
             A B C E
             S F C S
             A D E E
        ]
        (0,0)A->(0,1)B->(0,2)C->(1,2)C->(2,2)E->(2,1)D
 */
public class WordSearch {

    // dfs to traverse all possibilities
    // Time : O(nm(4^length(target))) in worst case
    // Space: the depth of recursion is O(length(target));
    public boolean exist(char[][] board, String word){
        if(board == null || board.length == 0 || board[0].length == 0){
            return false;
        }
        if(word.length() == 0){
            return true;
        }
        int m = board.length, n = board[0].length;
        // find all possible entrance
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == word.charAt(0)){
                    // start dfs traverse
                    boolean res = find(board, i, j, word, 0);
                    if(res){ // if find the target, return true;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean find(char[][] board, int x, int y, String word, int level){
        // base case
        if(level == word.length()){
            return true;
        }
        // out of bound
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != word.charAt(level)){
            return false;
        }
        // in the bound and match the right char, start recursion
        board[x][y] = '#';
        boolean res = find(board, x - 1, y, word, level+1) || find(board, x + 1, y, word, level + 1)
                || find(board, x, y - 1, word, level +1) || find(board, x, y + 1, word, level + 1);
        return res;
    }

    // Memory Search
    int[][][] can = null;
    public boolean existI(char[][] board, String word){
        if(board == null || board.length == 0 || board[0].length == 0){
            return false;
        }
        if(word.length() == 0){
            return true;
        }
        int m = board.length, n = board[0].length;
        can = new int[m][n][word.length()]; // sub case: whether the first i(3rd dimension parameter) char of word is exist with the given board
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < word.length(); k++){
                    can[i][j][k] = -1; // -1 represent unvisited
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == word.charAt(0)){
                    findany(board, i, j, word, 0);
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == word.charAt(0)){
                    if(findI(board, i, j, word, 0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    // -1:unvisited, 0:unreachable, 1: can reach
    private boolean findany(char[][] board, int i, int j, String word, int level){
        if(level == word.length()){
            return true;
        }
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(level)){
            return false;
        }
        if(can[i][j][level] != -1){
            // already visited
            return can[i][j][level] == 1;
        }
        boolean res = findany(board, i - 1, j, word, level+1) || findany(board, i + 1, j, word, level+1)
                || findany(board,i ,j-1 , word, level+1) || findany(board, i , j + 1, word, level+1);
        can[i][j][level] = res ? 1 : 0;
        return res;
    }
    private boolean findI(char[][] board, int i, int j, String word, int level){
        if(level == word.length()){
            return true;
        }
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(level)){
            return false;
        }
        // pruning, efficient of memory search
        if(can[i][j][level] == 0){
            return false;
        }
        // recursion
        board[i][j] = '#';
        boolean res = findany(board, i - 1, j, word, level+1) || findany(board, i + 1, j, word, level+1)
                || findany(board,i ,j-1 , word, level+1) || findany(board, i , j + 1, word, level+1);
        // handle the backtracking
        board[i][j] = word.charAt(level);
        return res;
    }
}
