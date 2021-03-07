package Object_Orient_Design.Design_Pattern.Decoration;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 15:28
 *   @Description :
 *
 */
public class DarkRoast extends Coffee{

    public DarkRoast(String name) {
        this.setName(name);
    }

    @Override
    float cost() {
        return (float) 1.19;
    }
}
