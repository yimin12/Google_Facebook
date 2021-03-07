package Object_Orient_Design.Design_Pattern.Proxy.StaticProxy;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 15:45
 *   @Description :
 *
 */
public class ProxyStar implements Star{

    private Star star;

    public ProxyStar(Star star) {
        super();
        this.star = star;
    }

    @Override
    public void bookTicket() {
        System.out.println("ProxyStar.bookTicket()");
    }

    @Override
    public void collectMoney() {
        System.out.println("ProxyStar.collectMoney()");
    }

    @Override
    public void confer() {
        System.out.println("ProxyStar.confer()");
    }

    @Override
    public void signContract() {
        System.out.println("ProxyStar.signContract()");
    }

    @Override
    public void sing() {
        star.sing();
    }
}
