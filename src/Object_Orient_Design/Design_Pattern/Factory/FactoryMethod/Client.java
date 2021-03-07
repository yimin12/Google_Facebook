package Object_Orient_Design.Design_Pattern.Factory.FactoryMethod;


/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 22:53
 *   @Description :
 *
 */
public class Client {

    public static void main(String[] args) {
        Phone oppo = new OppoFactory().createPhone();
        Phone huawei = new HuaweiFactory().createPhone();
        Phone apple = new AppleFactory().createPhone();

        oppo.playGame();
        huawei.playGame();
        apple.playGame();
    }
}
