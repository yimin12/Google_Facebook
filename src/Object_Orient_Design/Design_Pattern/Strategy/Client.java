package Object_Orient_Design.Design_Pattern.Strategy;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 12:55
 *   @Description :
 *
 */
public class Client {

    public static void main(String[] args) {
        Strategy strategy = new OldCustomerManyStrategy();
        Context context = new Context(strategy);
        System.out.println("The original price is 1000");
        context.printPrice(1000);
    }
}
