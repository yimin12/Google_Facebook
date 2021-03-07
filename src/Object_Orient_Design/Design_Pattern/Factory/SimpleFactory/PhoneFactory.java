package Object_Orient_Design.Design_Pattern.Factory.SimpleFactory;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 22:01
 *   @Description :
 *
 */
public class PhoneFactory {

    public Phone createApple(){
        return new Apple();
    }

    public Phone createOPPO(){
        return new OPPO();
    }

    public Phone createHuawei(){
        return new Huawei();
    }
}
