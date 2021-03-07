package Object_Orient_Design.Design_Pattern.Bridge;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 13:19
 *   @Description :
 *
 */
public class Client {

    public static void main(String[] args) {
        //销售联想的笔记本电脑
        Computer2  c = new Laptop2(new Lenovo());
        c.sale();

        //销售苹果的台式机
        Computer2 c2 = new Desktop2(new Apple());
        c2.sale();
    }
}
