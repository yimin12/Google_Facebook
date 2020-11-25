package Object_Orient_Design.Hotel_Design;

import java.util.Date;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/13 18:30
 *   @Description :
 *
 */
public class Reservation {

    private Date startDate;
    private Date endDate;
    private List<Room> roomsNeeded;

    public Reservation(Date startDate, Date endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<Room> getRoomsNeeded() {
        return roomsNeeded;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", roomsNeeded=" + roomsNeeded +
                '}';
    }
}
