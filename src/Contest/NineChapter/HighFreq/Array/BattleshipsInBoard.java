package Contest.NineChapter.HighFreq.Array;

/**
 * Description
 * Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
 *
 * You receive a valid board, made of only battleships or empty slots.
 * Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
 * At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 * Example
 * Example1
 *
 * Input:
 * X..X
 * ...X
 * ...X
 * Output: 2
 * Explanation:
 * In the above board there are 2 battleships.
 * Example2
 *
 * Input:
 * ...X
 * XXXX
 * ...X
 * Explanation:
 * This is an invalid board that you will not receive - as battleships
 */
public class BattleshipsInBoard {

    // 我们发现对于船头，他的左边和上面一定为空地（或边界），因此只需要统计船头的个数即可知道战舰的个数。
    public int countBattleships(char[][] board){
        if(board == null || board.length == 0 || board[0].length == 0){
            return 0;
        }
        int res = 0;
        int m = board.length, n = board[0].length;
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                if(board[i][j] == 'X' && (i == 0 || board[i-1][j] == '.') && (j == 0 || board[i][j - 1] == '.')){
                    res ++;
                }
            }
        }
        return res;
    }
}
