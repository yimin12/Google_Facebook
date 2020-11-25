package Object_Orient_Design.Tic_tac_toe_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/27 11:06
 *   @Description :
 *
 */
public class TicTacToe {

    private char[][] board;
    private char currentPlayer;
    private boolean endGame;
    public TicTacToe(){
        board = new char[3][3];
        initialize();
    }
    public char getCurrentPlayer(){
        return this.currentPlayer;
    }
    public void initialize(){
        this.endGame = false;
        currentPlayer = 'x';
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = '-';
            }
        }
    }
    public boolean isBoradFull(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == '-'){
                    return false;
                }
            }
        }
        endGame = true;
        return true;
    }
    public void changePlayer(){
        if(currentPlayer == 'x'){
            currentPlayer = 'o';
        } else {
            currentPlayer = 'x';
        }
    }
    // true means this move wins the game, false means otherwise
    public boolean move(int row, int col) throws AlreadyTakenException, GameEndException {
        if (endGame) {
            throw new GameEndException();
        }
        if (board[row][col] != '-') {
            throw new AlreadyTakenException();
        }
        board[row][col] = currentPlayer;
        boolean win;
        //check row
        win = true;
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] != currentPlayer) {
                win = false;
                break;
            }
        }
        if (win) {
            endGame = true;
            return win;
        }
        //check column
        win = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] != currentPlayer) {
                win = false;
                break;
            }
        }
        if (win) {
            endGame = true;
            return win;
        }
        //check back diagonal
        win = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i] != currentPlayer) {
                win = false;
                break;
            }
        }
        if (win) {
            endGame = true;
            return win;
        }
        //check forward diagonal
        win = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][board.length - i - 1] != currentPlayer) {
                win = false;
                break;
            }
        }
        if (win) {
            endGame = true;
            return win;
        }
        changePlayer();
        return win;
    }

}
