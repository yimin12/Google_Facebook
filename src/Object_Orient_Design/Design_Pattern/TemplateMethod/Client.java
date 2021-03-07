package Object_Orient_Design.Design_Pattern.TemplateMethod;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 21:26
 *   @Description :
 *
 */
public class Client {

    public static void main(String[] args) {
        BankTemplateMethod btm = new DrawMoney();
        btm.process();

        //采用匿名内部类
        BankTemplateMethod btm2 = new BankTemplateMethod() {

            @Override
            public void transact() {
                System.out.println("我要存钱！");
            }
        };
        btm2.process();

        BankTemplateMethod btm3 = new BankTemplateMethod() {
            @Override
            public void transact() {
                System.out.println("我要理财！我这里有2000万韩币");
            }
        };

        btm3.process();
    }
}
class DrawMoney extends BankTemplateMethod {

    @Override
    public void transact() {
        System.out.println("我要取款！！！");
    }

}
