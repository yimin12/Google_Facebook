package Object_Orient_Design.VendingMachine_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/27 11:34
 *   @Description :
 *
 */
public class NoSelectionState extends AbstractState{

    public NoSelectionState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void selectItem(String selection) {
        vendingMachine.setCurrentSelectedItem(selection);
        vendingMachine.changeToHasSelectionState();
    }

    @Override
    public void insertMoney(int value) {
        System.out.println("Please make a selection first");
    }

    @Override
    public void executeTransaction() {
        System.out.println("Please make a selection first");
    }

    @Override
    public double cancelTransaction() {
        System.out.println("Please make a selection first");
        return 0;
    }

    @Override
    public String toString() {
        return "No Selection";
    }
}
