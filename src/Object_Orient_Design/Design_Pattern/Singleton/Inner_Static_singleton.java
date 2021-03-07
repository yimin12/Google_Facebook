package Object_Orient_Design.Design_Pattern.Singleton;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 17:09
 *   @Description :
 *
 */
public class Inner_Static_singleton {

    // private inner static class, will be loaded after Inner_Static_singleton class is loaded
    // Worked as lazy init and naturally thread safe because JVM class Loader
    private static class Inner_class_instance{
        private static final Inner_Static_singleton instance = new Inner_Static_singleton();
    }

    private Inner_Static_singleton(){

    }

    public static Inner_Static_singleton getInstance(){
        return Inner_class_instance.instance;
    }
}
