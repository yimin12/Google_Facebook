package Object_Orient_Design.Design_Pattern.Strategy;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 12:39
 *   @Description :
 *
 */
public class OldCustomerManyStrategy implements Strategy{
    @Override
    public double getPrice(double price) {
        System.out.println("20 Percent Discount");
        return price * 0.8;
    }
}
