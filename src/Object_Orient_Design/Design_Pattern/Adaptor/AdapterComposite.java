package Object_Orient_Design.Design_Pattern.Adaptor;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 13:10
 *   @Description :
 *      Use composition relationship to implement the adaptor pattern
 */
public class AdapterComposite implements Target{

    private Adaptee adaptee;

    @Override
    public void handleReq() {
        adaptee.request();
    }

    public AdapterComposite(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
}
