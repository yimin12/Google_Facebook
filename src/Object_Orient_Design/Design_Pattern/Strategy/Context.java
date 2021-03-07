package Object_Orient_Design.Design_Pattern.Strategy;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 12:53
 *   @Description :
 *
 */
public class Context {

    // state an hooker and set the strategy
    private Strategy strategy;

    public Context(Strategy strategy){
        super();
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void printPrice(double s){
        double price = strategy.getPrice(s);
        System.out.println("The price is " + price);
    }
}
