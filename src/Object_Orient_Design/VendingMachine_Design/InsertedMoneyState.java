package Object_Orient_Design.VendingMachine_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/27 11:37
 *   @Description :
 *
 */
public class InsertedMoneyState extends AbstractState{

    public InsertedMoneyState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void selectItem(String selection) {
        System.out.println("Already has a selection, please cancel transaction to make a new selection");
    }

    @Override
    public void insertMoney(int value) {
        vendingMachine.insertMoney(value);
    }

    @Override
    public void executeTransaction() {
        double diff = vendingMachine.getCurrentInsertedMoney() - vendingMachine.getSalePrice();
        if(diff >= 0){
            System.out.println("Executing transaction, will return you : " + diff + " money and item: " + vendingMachine.getCurrentSelectedItem());
            vendingMachine.setCurrentSelectedItem(null);
            vendingMachine.emptyInsertedMoney();
            vendingMachine.changeToNoSelectionState();
        } else {
            System.out.println("Not Enough money, please insert " + (-diff) + "more.");
        }
    }

    @Override
    public double cancelTransaction() {
        double insertedMoney = vendingMachine.getCurrentInsertedMoney();
        vendingMachine.setCurrentSelectedItem(null);
        vendingMachine.emptyInsertedMoney();
        vendingMachine.changeToNoSelectionState();
        return insertedMoney;
    }

    @Override
    public String toString() {
        return "InsertedMoney";
    }
}
