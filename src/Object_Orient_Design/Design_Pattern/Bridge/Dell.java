package Object_Orient_Design.Design_Pattern.Bridge;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 13:14
 *   @Description :
 *
 */
public class Dell implements Brand{
    @Override
    public void sale() {
        System.out.println("Selling Dell");
    }
}
