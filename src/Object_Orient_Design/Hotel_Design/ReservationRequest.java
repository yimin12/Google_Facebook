package Object_Orient_Design.Hotel_Design;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/13 18:30
 *   @Description :
 *
 */
public class ReservationRequest {

    private Date startDate;
    private Date endDate;
    private Map<RoomType, Integer> roomsNeeded;
    public ReservationRequest(Date startDate, Date endDate, Map<RoomType, Integer> roomsNeeded){
        this.startDate = startDate;
        this.endDate = endDate;
        roomsNeeded = new HashMap<>();
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Map<RoomType, Integer> getRoomsNeeded() {
        return roomsNeeded;
    }
}
