package Object_Orient_Design.VendingMachine_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/27 11:24
 *   @Description :
 *
 */
public interface State {

    public void selectItem(String selection);
    public void insertMoney(int value);
    public void executeTransaction();
    public double cancelTransaction();
    public String toString();

}
