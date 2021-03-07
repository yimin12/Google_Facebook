package Object_Orient_Design.Design_Pattern.Proxy.DynamicProxy;

import Object_Orient_Design.Design_Pattern.Proxy.DynamicProxy.Star;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 15:44
 *   @Description :
 *
 */
public class RealStar implements Star {

    @Override
    public void bookTicket() {
        System.out.println("RealStar.bookTicket()");
    }

    @Override
    public void collectMoney() {
        System.out.println("RealStar.collectMoney()");
    }

    @Override
    public void confer() {
        System.out.println("RealStar.confer()");
    }

    @Override
    public void signContract() {
        System.out.println("RealStar.signContract()");
    }

    @Override
    public void sing() {
        System.out.println("RealStar(JayChou).sing()");
    }

}
