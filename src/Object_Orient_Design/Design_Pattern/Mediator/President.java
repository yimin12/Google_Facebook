package Object_Orient_Design.Design_Pattern.Mediator;

import java.util.HashMap;
import java.util.Map;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 17:24
 *   @Description :
 *
 */
public class President implements Mediator{

    private Map<String, Department> map = new HashMap<>();


    @Override
    public void register(String departName, Department department) {
        map.put(departName, department);
    }

    @Override
    public void command(String departName) {
        map.get(departName).selfAction();
    }
}
