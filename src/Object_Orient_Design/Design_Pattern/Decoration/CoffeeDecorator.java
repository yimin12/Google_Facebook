package Object_Orient_Design.Design_Pattern.Decoration;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 15:31
 *   @Description :
 *
 */
public abstract class CoffeeDecorator extends Coffee{

    protected Coffee decoratedCoffee;

    public CoffeeDecorator() {
        super();
    }

    public CoffeeDecorator(Coffee decoratedCoffee){
        super();
        this.decoratedCoffee = decoratedCoffee;
    }

}
