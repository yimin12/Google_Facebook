package Object_Orient_Design.Design_Pattern.Decoration;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 15:40
 *   @Description :
 *
 */
public class Client {

    public void test(){
        Coffee coffee_1 = new Expresso("Expresso");
        coffee_1 = new Mocha(coffee_1); // add Mocha
        coffee_1 = new Milk(coffee_1); // then add milk
        coffee_1 = new Soy(coffee_1); // then add soy

        System.out.println(coffee_1.cost());
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.test();
    }
}
