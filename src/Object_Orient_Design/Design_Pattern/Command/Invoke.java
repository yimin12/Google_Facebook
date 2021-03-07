package Object_Orient_Design.Design_Pattern.Command;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 14:55
 *   @Description :
 *
 */
public class Invoke {

    private Command command;

    public Invoke(Command command){
        super();
        this.command = command;
    }

    public void call(){
        command.execute();
    }
}
