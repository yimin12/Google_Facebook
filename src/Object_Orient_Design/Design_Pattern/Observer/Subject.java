package Object_Orient_Design.Design_Pattern.Observer;

import java.util.ArrayList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 21:16
 *   @Description :
 *
 */
public class Subject {

    protected List<Observer> list = new ArrayList<>();

    public void registerObserver(Observer observer){
        list.add(observer);
    }

    public void removeObserver(Observer observer){
        list.remove(observer);
    }

    public void notifyAllObservers(){
        for(Observer observer:list){
            observer.update(this);
        }
    }
}
