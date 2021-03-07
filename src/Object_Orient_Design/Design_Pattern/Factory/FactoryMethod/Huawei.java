package Object_Orient_Design.Design_Pattern.Factory.FactoryMethod;

import Object_Orient_Design.Design_Pattern.Factory.FactoryMethod.Phone;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 21:58
 *   @Description :
 *
 */
public class Huawei implements Phone {

    @Override
    public void playGame() {
        System.out.println("The game is on Huawei");
    }
}
