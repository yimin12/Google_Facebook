package Object_Orient_Design.Design_Pattern.Decoration;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 15:26
 *   @Description :
 *
 */
public abstract class Coffee {

    private String name;

    public Coffee() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract float cost();
}
