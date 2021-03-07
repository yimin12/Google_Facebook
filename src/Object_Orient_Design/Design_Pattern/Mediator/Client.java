package Object_Orient_Design.Design_Pattern.Mediator;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 17:28
 *   @Description :
 *
 */
public class Client {
    public static void main(String[] args) {
        Mediator m = new President();

        Market   market = new Market(m);
        Development devp = new Development(m);
        Finacial f = new Finacial(m);

        market.selfAction();
        market.outAction();

    }
}
