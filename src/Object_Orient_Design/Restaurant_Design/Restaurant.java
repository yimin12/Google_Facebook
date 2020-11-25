package Object_Orient_Design.Restaurant_Design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/13 20:41
 *   @Description :
 *
 */
public class Restaurant {
    private List<Table> tables;
    private List<Meal> menu;
    public static final int MAX_DINEHOUR = 2;
    public static final long HOUR = 3600*1000;

    public Restaurant(){
        tables = new ArrayList<>();
        menu = new ArrayList<>();
    }

    public void findTable(Party p) throws NotableException{
        Date current = new Date();
        for(Table t : tables){
            if(t.isAvailable()){
                if(t.getCapacity() >= p.getSize()){
                    if(t.noFollowReservation(current)){
                        t.markUnavailable();
                        return;
                    }
                }
            }
        }
        throw new NotableException(p);
    }

    public void takeOrder(Table t, Order o){
        t.setOrder(o);
    }

    public float checkOut(Table t){
        float bill = 0;
        if(t.getOrder() != null){
            bill = t.getOrder().getBill();
        }
        t.markAvailable();
        t.setOrder(null);
        return bill;
    }

    public List<Meal> getMenu() {
        return menu;
    }

    public void addTable(Table t){
        tables.add(t);
        Collections.sort(tables);
    }

    public Reservation findTableForReservation(Party p, Date date){
        for(Table table : tables){
            if(table.getCapacity() >= p.getSize()){
                if(table.reserveForDate(date)){
                    return new Reservation(table, date);
                }
            }
        }
        return null;
    }

    public void cancelReservation(Reservation r){
        Date date = r.getDate();
        r.getTable().removeReservation(date);
    }

    public void redeemReservation(Reservation r){
        Date date = r.getDate();
        Table table = r.getTable();
        table.markUnavailable();
        table.removeReservation(date);
    }

    public String restaurantDescription()
    {
        String description = "";
        for(int i = 0; i < tables.size(); i++)
        {
            Table table = tables.get(i);
            description += ("Table: " + table.getId() + ", table size: " + table.getCapacity() + ", isAvailable: " + table.isAvailable() + ".");
            if(table.getOrder() == null)
                description += " No current order for this table";
            else
                description +=  " Order price: " + table.getOrder().getBill();

            description += ". Current reservation dates for this table are: ";

            for(Date date : table.getReservations())
            {
                description += date.toGMTString() + " ; ";
            }

            description += ".\n";
        }
        description += "*****************************************\n";
        return description;
    }
}
