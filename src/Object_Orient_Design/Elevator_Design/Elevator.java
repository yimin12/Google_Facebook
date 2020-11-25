package Object_Orient_Design.Elevator_Design;

import java.util.ArrayList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/13 22:11
 *   @Description :
 *
 */
public class Elevator {

    private List<ElevatorButton> buttons;
    private List<Boolean> upStops; // if there is a request, mark as true, and mark false after finish
    private List<Boolean> downStops;

    private int currLevel;
    private Status status;

    public Elevator(int n){
        buttons = new ArrayList<ElevatorButton>();
        upStops = new ArrayList<Boolean>();
        downStops = new ArrayList<Boolean>();
        currLevel = 0;
        status = Status.IDLE;
        for(int i = 0; i < n; i++){
            upStops.add(false);
            downStops.add(false);
        }
    }

    public void insertButton(ElevatorButton eb){
        buttons.add(eb);
    }

    public void hadnleExternalRequest(ExternalRequest r){
        if(r.getDirection() == Direction.UP){
            upStops.set(r.getLevel() - 1, true); // going down stair
            if(noRequests(downStops)){
                status = Status.UP;
            }
        } else {
            // going down
            downStops.set(r.getLevel() - 1, true);
            if(noRequests(upStops)){
                status = Status.DOWN;
            }
        }
    }

    public void closeGate(){
        if(status == Status.IDLE){
            if(noRequests(downStops)){
                status = Status.UP;
                return;
            }
            if(noRequests(upStops)){
                status = Status.DOWN;
                return;
            }
        } else if(status == Status.UP){
            if(noRequests(upStops)){
                if(noRequests(downStops)){
                    status = Status.IDLE;
                } else status = Status.DOWN;
            }
        } else {
            if(noRequests(downStops)){
                if(noRequests(upStops)){
                    status = Status.IDLE;
                } else status = Status.UP;
            }
        }
    }

    private boolean noRequests(List<Boolean> upStops) {
        for(int i = 0; i < upStops.size(); i++){
            if(upStops.get(i)){
                return false;
            }
        }
        return true;
    }

    public void handleInternalRequest(InternalRequest request){
        if(status == Status.UP){
            if(request.getLevel() >= currLevel + 1){
                upStops.set(request.getLevel() - 1, true);
            }
        } else if(status == Status.DOWN){
            if(request.getLevel() <= currLevel + 1){
                downStops.set(request.getLevel() - 1, true);
            }
        }
    }

   public void openGate() throws Exception{
        if(status == Status.UP){
            for(int i = 0; i < upStops.size(); i++){
                int checkLevel = (currLevel + i) % upStops.size();
                if(upStops.get(checkLevel)){
                    currLevel = checkLevel;
                    upStops.set(checkLevel, false);
                    break;
                }
            }
        } else if(status == Status.DOWN){
            for(int i = 0; i < upStops.size(); i++){
                int checkLevel = (currLevel + downStops.size() - i) % downStops.size();
                if(downStops.get(checkLevel)){
                    currLevel = checkLevel;
                    downStops.set(checkLevel, false);
                    break;
                }
            }
        }
   }
    public String elevatorStatusDescription()
    {
        String description = "Currently elevator status is : " + status
                + ".\nCurrent level is at: " + (currLevel + 1)
                + ".\nup stop list looks like: " + upStops
                + ".\ndown stop list looks like:  " + downStops
                + ".\n*****************************************\n";
        return description;
    }

}
