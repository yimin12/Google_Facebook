package Object_Orient_Design.Design_Pattern.Singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 17:01
 *   @Description :
 *
 */
public class Double_Lock_singleton implements Serializable {

    // Lazy init

    private static Double_Lock_singleton instance = null;

    // in case of get the class by reflection
    private Double_Lock_singleton(){
        if(instance != null){
            throw new RuntimeException();
        }
    }

    // Lock more specifically
    public static Double_Lock_singleton getInstance(){
        if(instance == null){
            Double_Lock_singleton temp;
            // many multiple thread and cause race condition
            synchronized (Double_Lock_singleton.class){
                temp = instance; // reference to exact one address
                if(temp == null){
                    // it might cause happen before for JVM optimization, the second time lock
                    synchronized (Double_Lock_singleton.class){
                        if(temp == null){
                            temp = new Double_Lock_singleton();
                        }
                    }
                    instance = temp;
                }
            }
        }
        return instance;
    }

    //反序列化时，如果定义了readResolve()则直接返回此方法指定的对象。而不需要单独再创建新对象！
    private Object readResolve() throws ObjectStreamException {
        return instance;
    }
}
