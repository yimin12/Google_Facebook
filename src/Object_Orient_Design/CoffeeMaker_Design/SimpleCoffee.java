package Object_Orient_Design.CoffeeMaker_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 21:17
 *   @Description :
 *
 */
public class SimpleCoffee implements Coffee{


    @Override
    public double getCost() {
        return 2;
    }

    @Override
    public String getIngredients() {
        return "Plain Coffee";
    }
}
