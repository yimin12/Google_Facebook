package Object_Orient_Design.Design_Pattern.Mediator;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 17:26
 *   @Description :
 *
 */
public class Finacial implements Department{

    private Mediator m;  //持有中介者(总经理)的引用

    public Finacial(Mediator m) {
        super();
        this.m = m;
        m.register("finacial", this);
    }

    @Override
    public void outAction() {
        System.out.println("Report, the money is not enough");
    }

    @Override
    public void selfAction() {
        System.out.println("Counting money");
    }
}
