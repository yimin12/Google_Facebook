package Object_Orient_Design.Design_Pattern.State;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 23:31
 *   @Description :
 *
 */
public class Hotel {

    private State state;

    public void setState(State s){
        System.out.println("Changing the state");
        state = s;
        state.handle();
    }
}
