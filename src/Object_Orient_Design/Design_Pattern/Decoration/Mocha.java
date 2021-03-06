package Object_Orient_Design.Design_Pattern.Decoration;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 15:37
 *   @Description :
 *
 */
public class Mocha extends CoffeeDecorator{

    Coffee coffee;

    public Mocha(Coffee coffee) {
        this.coffee = coffee;
    }


    @Override
    float cost() {
        return (float) (coffee.cost() + 0.5);
    }
}
