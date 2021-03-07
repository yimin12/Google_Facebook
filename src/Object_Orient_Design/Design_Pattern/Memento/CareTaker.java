package Object_Orient_Design.Design_Pattern.Memento;

import java.util.ArrayList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 21:05
 *   @Description :
 *
 */
public class CareTaker {

    private EmpMemento memento;
//    private List<EmpMemento> list = new ArrayList<>();

    public EmpMemento getMemento(){
        return this.memento;
    }

    public void setMemento(EmpMemento memento) {
        this.memento = memento;
    }
}
