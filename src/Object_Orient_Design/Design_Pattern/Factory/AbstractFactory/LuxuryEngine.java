package Object_Orient_Design.Design_Pattern.Factory.AbstractFactory;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 23:15
 *   @Description :
 *
 */
public class LuxuryEngine implements Engine{

    @Override
    public void run() {
        System.out.println("Running Fast");
    }

    @Override
    public void start() {
        System.out.println("Luxury Engine start");
    }
}
