package Object_Orient_Design.Design_Pattern.Observer;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 21:20
 *   @Description :
 *
 */
public class ObserverA implements Observer{

    private int myState;

    @Override
    public void update(Subject subject) {
        myState = ((ConcreteSubject)subject).getState();
    }

    public int getMyState(){
        return myState;
    }

    public void setState(int myState){
        this.myState = myState;
    }
}
