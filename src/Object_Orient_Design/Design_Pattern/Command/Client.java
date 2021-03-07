package Object_Orient_Design.Design_Pattern.Command;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 14:59
 *   @Description :
 *      JVM dynamic proxy
 */
public class Client {

    public static void main(String[] args) {
        Command c = new ConcreteCommand(new Receiver());
        Invoke i = new Invoke(c);
        i.call();
    }
}
