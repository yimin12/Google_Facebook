package Object_Orient_Design.Design_Pattern.TemplateMethod;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 21:24
 *   @Description :
 *
 */
public abstract class BankTemplateMethod {

    // concrete method
    public void takeNumber(){
        System.out.println("Taking number");
    }

    public abstract void transact();

    public void evaluate(){
        System.out.println("evaluation");
    }

    public final void process(){
        this.takeNumber();
        this.transact();
        this.evaluate();
    }

}
