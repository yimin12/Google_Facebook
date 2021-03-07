package Object_Orient_Design.Design_Pattern.Adaptor;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 13:11
 *   @Description :
 *
 */
public class Client {

    public void test(Target t){
        t.handleReq();
    }

    public static void main(String[] args) {
        Client c = new Client();
        Adaptee a = new Adaptee();
        Target t = new Adapter();
        // bind the adaptee by composition
        Target t2 = new AdapterComposite(a);
        c.test(t);
        c.test(t2);
    }
}
