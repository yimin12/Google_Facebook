package Object_Orient_Design.Design_Pattern.State;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 23:29
 *   @Description :
 *
 */
public class FreeState implements State{
    @Override
    public void handle() {
        System.out.println("No one check in, it is free");
    }
}
