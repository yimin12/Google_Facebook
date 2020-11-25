package Object_Orient_Design.VendingMachine_Design;

import java.util.HashMap;
import java.util.Map;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/27 11:30
 *   @Description :
 *
 */
public class VendingMachine {
    // required field for vending machine
    private String currentSelectedItem;
    private double currentInsertedMoney;
    private AbstractState state;
    private NoSelectionState noSelectionState;
    private HasSelectionState hasSelectionState;
    private InsertedMoneyState insertedMoneyState;
    private Map<String, Double> itemPrice;

    public VendingMachine(){
        currentInsertedMoney = 0;
        currentSelectedItem = null;
        noSelectionState = new NoSelectionState(this);
        hasSelectionState = new HasSelectionState(this);
        insertedMoneyState = new InsertedMoneyState(this);
        // original state
        state = noSelectionState;

        itemPrice = new HashMap<>();
        itemPrice.put("Coke", 1.99);
        itemPrice.put("Sprite", 2.01);
        itemPrice.put("MountainDew", 3.99);
    }

    // basic required function
    public double getSalePrice(){
        if(currentSelectedItem == null){
            System.out.println("Please make a selection before asking price");
            return 0;
        } else {
            return itemPrice.get(currentSelectedItem);
        }
    }

    public void insertMoney(int amount) {
        this.currentInsertedMoney += amount;
    }

    public void emptyInsertedMoney() {
        this.currentInsertedMoney = 0;
    }

    public void changeToNoSelectionState(){
        state = noSelectionState;
    }

    public void changeToHasSelectionState(){
        state = hasSelectionState;
    }

    public void changeToInsertedMoneyState(){
        state = insertedMoneyState;
    }

    public void seletItem(String selection){
        state.selectItem(selection);
    }

    public void addMoney(int value) {
        state.insertMoney(value);
    }

    public void executeTransaction() {
        state.executeTransaction();
    }

    public double cancelTransaction() {
        return state.cancelTransaction();
    }

    // getter and setter
    public void setCurrentSelectedItem(String currentSelectedItem) {
        this.currentSelectedItem = currentSelectedItem;
    }

    public void setCurrentInsertedMoney(double currentInsertedMoney) {
        this.currentInsertedMoney = currentInsertedMoney;
    }

    public void setState(AbstractState state) {
        this.state = state;
    }

    public void setNoSelectionState(NoSelectionState noSelectionState) {
        this.noSelectionState = noSelectionState;
    }

    public void setHasSelectionState(HasSelectionState hasSelectionState) {
        this.hasSelectionState = hasSelectionState;
    }

    public void setInsertedMoneyState(InsertedMoneyState insertedMoneyState) {
        this.insertedMoneyState = insertedMoneyState;
    }

    public void setItemPrice(Map<String, Double> itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getCurrentSelectedItem() {
        return currentSelectedItem;
    }

    public double getCurrentInsertedMoney() {
        return currentInsertedMoney;
    }

    public AbstractState getState() {
        return state;
    }

    public NoSelectionState getNoSelectionState() {
        return noSelectionState;
    }

    public HasSelectionState getHasSelectionState() {
        return hasSelectionState;
    }

    public InsertedMoneyState getInsertedMoneyState() {
        return insertedMoneyState;
    }

    public Map<String, Double> getItemPrice() {
        return itemPrice;
    }
}
