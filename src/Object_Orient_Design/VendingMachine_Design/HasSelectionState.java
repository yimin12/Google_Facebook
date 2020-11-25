package Object_Orient_Design.VendingMachine_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/27 11:37
 *   @Description :
 *
 */
public class HasSelectionState extends AbstractState{

    public HasSelectionState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void selectItem(String selection) {
        vendingMachine.setCurrentSelectedItem(selection);
    }

    @Override
    public void insertMoney(int value) {
        vendingMachine.insertMoney(value);
        vendingMachine.changeToInsertedMoneyState();
    }

    @Override
    public void executeTransaction() {
        System.out.println("You need to insert money first");
    }

    @Override
    public double cancelTransaction() {
        System.out.println("Transaction canceled");
        vendingMachine.setCurrentSelectedItem(null);
        vendingMachine.changeToNoSelectionState();
        return 0;
    }

    @Override
    public String toString() {
        return "HasSelection";
    }
}
