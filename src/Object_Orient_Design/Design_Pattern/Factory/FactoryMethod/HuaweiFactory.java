package Object_Orient_Design.Design_Pattern.Factory.FactoryMethod;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 22:47
 *   @Description :
 *
 */
public class HuaweiFactory implements PhoneFactory {
    @Override
    public Phone createPhone() {
        return new Huawei();
    }
}
