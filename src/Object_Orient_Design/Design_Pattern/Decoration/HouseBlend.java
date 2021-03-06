package Object_Orient_Design.Design_Pattern.Decoration;

import Object_Orient_Design.CoffeeMaker_Design.CoffeeDecorator;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 15:28
 *   @Description :
 *
 */
public class HouseBlend extends Coffee{

    public HouseBlend(String name) {
        this.setName(name);
    }

    @Override
    float cost() {
        return (float) 2.39;
    }
}
