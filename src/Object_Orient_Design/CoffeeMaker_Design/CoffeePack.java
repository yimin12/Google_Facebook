package Object_Orient_Design.CoffeeMaker_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 21:13
 *   @Description :
 *
 */
public class CoffeePack {

    private int neededMilk;
    private int neededSuger;

    public CoffeePack(int neededMilk, int neededSuger) {
        this.neededMilk = neededMilk;
        this.neededSuger = neededSuger;
    }

    public int getNeededMilk() {
        return neededMilk;
    }

    public int getNeededSuger() {
        return neededSuger;
    }
}
