package Object_Orient_Design.CoffeeMaker_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 21:10
 *   @Description :
 *
 */
public class CoffeeMaker {

    public Coffee makeCoffee(CoffeePack pack){
        Coffee coffee = new SimpleCoffee();
        for(int i = 0; i < pack.getNeededMilk(); i++){
            coffee = new WithMilk(coffee);
        }
        for(int i = 0; i < pack.getNeededSuger(); i++){
            coffee = new WithSugar(coffee);
        }
        return coffee;
    }
}
