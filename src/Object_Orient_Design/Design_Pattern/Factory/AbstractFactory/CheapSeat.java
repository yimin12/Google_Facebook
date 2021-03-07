package Object_Orient_Design.Design_Pattern.Factory.AbstractFactory;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 23:13
 *   @Description :
 *
 */
public class CheapSeat implements Seat{
    @Override
    public void message() {
        System.out.println("Cheap Seat");
    }
}
