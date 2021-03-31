package Object_Orient_Design.CoffeeMaker_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 21:21
 *   @Description :
 *
 */
public abstract class CoffeeDecorator implements Coffee{
    protected final Coffee decoreatedCoffee;

    public CoffeeDecorator(Coffee decoreatedCoffee) {
        this.decoreatedCoffee = decoreatedCoffee;
    }

    @Override
    public double getCost() {
        return decoreatedCoffee.getCost();
    }

    @Override
    public String getIngredients() {
        return decoreatedCoffee.getIngredients();
    }
}

