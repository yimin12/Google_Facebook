package Object_Orient_Design.Elevator_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/13 22:11
 *   @Description :
 *
 */
public class ElevatorButton {

    private int level;
    private Elevator elevator;

    public ElevatorButton(int level, Elevator elevator){
        this.level = level;
        this.elevator = elevator;
    }

    public void pressButton(){
        InternalRequest request = new InternalRequest(level);
        elevator.handleInternalRequest(request);
    }

}
