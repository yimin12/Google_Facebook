package Object_Orient_Design.Restaurant_Design;

import java.util.Date;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/13 20:26
 *   @Description :
 *
 */
public class Table implements Comparable<Table>{

    private int id;
    private int capacity;
    private boolean available;
    private Order order;
    List<Date> reservations;

    final int MILLI_TO_HOUR = 1000 * 60 * 60;

    public Table(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void markAvailable() {
        this.available = true;
    }

    public void markUnavailable(){
        this.available = false;
    }

    public void setOrder(Order order) {
       if(order == null){
           this.order = order;
       } else {
           if(order != null){
               this.order.mergeOrder(order);
           }
       }
    }

    public void setReservations(List<Date> reservations) {
        this.reservations = reservations;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public Order getOrder() {
        return order;
    }

    public List<Date> getReservations() {
        return reservations;
    }

    @Override
    public int compareTo(Table o) {
        return this.capacity - o.getCapacity();
    }

    private int findDatePosition(Date d){
        int len = reservations.size();
        if(len == 0){
            return 0;
        }
        if(d.getTime() > reservations.get(len - 1).getTime()){
            return len;
        }
        int i = 0;
        int j = len;
        // binary search
        while(i < j){
            int m = i + (j - i)/2;
            if(d.getTime() > reservations.get(m).getTime()){
                i = m + 1;
            } else {
                j = m;
            }
        }
        return j;
    }

    public boolean noFollowReservation(Date d){
        int position = findDatePosition(d);
        if(position < reservations.size()){
            Date nextReservation = reservations.get(position);
            int diff = (int)((nextReservation.getTime() - d.getTime())/MILLI_TO_HOUR);
            if(diff < Restaurant.MAX_DINEHOUR){
                return false;
            }
        }
        return true;
    }

    public boolean reserveForDate(Date d){
        int position = findDatePosition(d);
        int before = position - 1;
        int after = position;
        if(before >= 0){
            Date previousReservation = reservations.get(before);
            int diff = (int)((d.getTime() - previousReservation.getTime()) / MILLI_TO_HOUR);
            if(diff < Restaurant.MAX_DINEHOUR){
                return false;
            }
        }
        reservations.add(position, d);
        return true;
    }
    public void removeReservation(Date d){
        reservations.remove(d);
    }
}
