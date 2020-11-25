package Object_Orient_Design.Tic_tac_toe_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/27 11:13
 *   @Description :
 *
 */
public class AlreadyTakenException extends Exception{

    public AlreadyTakenException(){
        super("This place has been taken");
    }

}


