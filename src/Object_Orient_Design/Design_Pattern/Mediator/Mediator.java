package Object_Orient_Design.Design_Pattern.Mediator;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 17:23
 *   @Description :
 *
 */
public interface Mediator {

    void register(String departName, Department department);
    void command(String departName);
}
