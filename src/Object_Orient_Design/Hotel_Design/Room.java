package Object_Orient_Design.Hotel_Design;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/13 18:23
 *   @Description :
 *
 */
public class Room {

    public static final int DAY = 1*24*60*60*1000;

    private int id;
    private RoomType roomType;
    private Set<Date> reservations;

    public Room(int id, RoomType roomType){
        this.id = id;
        this.roomType = roomType;
        reservations = new HashSet<>();
    }
    // Comes from searching criteria
    public boolean isValidRequest(SearchRequest request){
        Date date = new Date(request.getStartDate().getTime());
        for(;date.before(request.getEndDate()); date.setTime(date.getTime() + DAY)){
            Date tempDate = new Date(date.getTime());
            if(reservations.contains(tempDate)){
                return false;
            }
        }
        return true;
    }

    // make reservation for specific room
    public void makeReservation(Date startDate, Date endDate){
        Date date = new Date(startDate.getTime());
        for(;date.before(endDate);date.setTime(date.getTime() + DAY)){
            Date tempDate = new Date(date.getTime());
            reservations.add(tempDate);
        }
    }

    // cancel reservation for specific room
    public void cancelReservation(Reservation reservation){
        Date date = new Date(reservation.getStartDate().getTime());
        for (; date.before(reservation.getEndDate()); date.setTime(date.getTime() + DAY))
        {
            Date tempDate = new Date(date.getTime());
            reservations.remove(tempDate);
        }
    }

    public RoomType getRoomType()
    {
        return roomType;
    }

    public int getId()
    {
        return this.id;
    }
}
