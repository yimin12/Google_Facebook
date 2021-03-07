package Object_Orient_Design.Design_Pattern.Adaptor;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 13:07
 *   @Description :
 *      Use inheritance to implement the Adaptor pattern
 */
public class Adapter extends Adaptee implements Target{

    @Override
    public void handleReq() {
        super.request();
    }
}
