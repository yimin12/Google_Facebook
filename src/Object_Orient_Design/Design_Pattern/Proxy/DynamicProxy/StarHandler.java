package Object_Orient_Design.Design_Pattern.Proxy.DynamicProxy;

import java.io.ObjectInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 15:49
 *   @Description :
 *
 */
public class StarHandler implements InvocationHandler {

    Star realStar;

    public StarHandler(Star realStar){
        this.realStar = realStar;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object = null;
        // what need to be done by proxy
        System.out.println("ProxyStar.confer()");
        System.out.println("ProxyStar.bookTicket()");
        System.out.println("ProxyStar.signContract()");
        if(method.getName().equals("sing")){
            object = method.invoke(realStar, args); // let the real star to sing
        }
        System.out.println("ProxyStar.collectMoney()");
        return object;
    }
}
