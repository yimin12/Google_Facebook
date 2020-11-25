package Object_Orient_Design.CoffeeMaker_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 21:25
 *   @Description :
 *
 */
public class WithSugar extends CoffeeDecorator{

    public WithSugar(Coffee decoreatedCoffee) {
        super(decoreatedCoffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", Sugar";
    }
}
