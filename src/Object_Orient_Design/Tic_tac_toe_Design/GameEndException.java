package Object_Orient_Design.Tic_tac_toe_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/27 11:11
 *   @Description :
 *
 */
public class GameEndException extends Exception{

    public GameEndException(){
        super("Game has been ended, can not make any more moves");
    }

}
