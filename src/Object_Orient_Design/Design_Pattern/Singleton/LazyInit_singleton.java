package Object_Orient_Design.Design_Pattern.Singleton;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 16:58
 *   @Description :
 *
 */
public class LazyInit_singleton {

    // Lazy init
    private static LazyInit_singleton instance;

    // private constructor
    private LazyInit_singleton(){

    }

    // handle the multiple thread edge case
    public static synchronized LazyInit_singleton getInstance(){
        if(instance == null){
            instance = new LazyInit_singleton();
        }
        return instance;
    }
}
