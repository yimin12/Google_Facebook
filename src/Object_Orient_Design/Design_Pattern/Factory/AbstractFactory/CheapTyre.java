package Object_Orient_Design.Design_Pattern.Factory.AbstractFactory;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 23:10
 *   @Description :
 *
 */
public class CheapTyre implements Tyre{
    @Override
    public void revolve() {
        System.out.println("Cheap Tyre");
    }
}
