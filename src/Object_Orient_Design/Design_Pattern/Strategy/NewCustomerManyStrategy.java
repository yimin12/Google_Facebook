package Object_Orient_Design.Design_Pattern.Strategy;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 12:39
 *   @Description :
 *
 */
public class NewCustomerManyStrategy implements Strategy{
    @Override
    public double getPrice(double price) {
        System.out.println("10 Percent Discount");
        return price * 0.9;
    }
}
