package Contest.Bloomberg;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/2 22:41
 *   @Description :
 *
 */
public class CandyCrush {

    // ------------------------------------------------------------------Remove duplicate letters with specific k adjacent, 1D candicrush
    public String removeDuplicates(String s, int k) {
        int i = 0, n = s.length(), count[] = new int[n];
        char[] stack = s.toCharArray();
        for (int j = 0; j < n; ++j, ++i) {
            stack[i] = stack[j];
            count[i] = i > 0 && stack[i - 1] == stack[j] ? count[i - 1] + 1 : 1;
            if (count[i] == k) i -= k;
        }
        return new String(stack, 0, i);
    }

    public int[][] candyCrush(int[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0){
            return board;
        }
        int m = board.length, n = board[0].length;
        boolean terminate = false;
        while(!terminate){
            terminate = true;
            for(int i = 0; i < m; i ++){
                for(int j = 0; j < n; j ++){
                    int val = Math.abs(board[i][j]);
                    if(val == 0) continue;
                    if(j < n - 2 && Math.abs(board[i][j+1]) == val && Math.abs(board[i][j+2]) == val){
                        // crush and create new possibilities
                        terminate = false;
                        int crush = j;
                        while(crush < n && Math.abs(board[i][crush]) == val) board[i][crush ++] = - val;
                    }
                    if(i < m - 2 && Math.abs(board[i + 1][j]) == val && Math.abs(board[i+2][j]) == val){
                        terminate = false;
                        int cursh = i;
                        while(cursh < m && Math.abs(board[cursh][j]) == val) board[cursh++][j] = -val;
                    }
                }
            }
            if(!terminate){
                // move the positive to the bottom and reset it to 0
                for(int j = 0; j < n; j ++){
                    int store = m - 1;
                    for(int i = store; i >= 0; i --){
                        if(board[i][j] > 0){
                            board[store--][j] = board[i][j];
                        }
                    }
                    for(int k = store; k >= 0; k --) board[k][j] = 0;
                }
            }
        }
        return board;
    }
}
