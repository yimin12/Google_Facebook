package Object_Orient_Design.VendingMachine_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/27 11:26
 *   @Description :
 *
 */
abstract class AbstractState implements State{

    protected VendingMachine vendingMachine;

    public AbstractState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }
}
