package Object_Orient_Design.Design_Pattern.Singleton;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 15:38
 *   @Description :
 *
 */
public class EagerInit_singleton {

    // Eager init
    private static EagerInit_singleton instance = new EagerInit_singleton();

    // key word: private constructor, which is not exposed to outside
    private EagerInit_singleton(){

    }

    public static EagerInit_singleton getInstance(){
        return instance;
    }
}
