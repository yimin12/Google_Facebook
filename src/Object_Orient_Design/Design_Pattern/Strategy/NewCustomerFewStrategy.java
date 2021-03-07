package Object_Orient_Design.Design_Pattern.Strategy;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 12:39
 *   @Description :
 *
 */
public class NewCustomerFewStrategy implements Strategy{
    @Override
    public double getPrice(double price) {
        System.out.println("No discount!");
        return price;
    }
}
