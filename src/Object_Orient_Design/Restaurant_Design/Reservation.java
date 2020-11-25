package Object_Orient_Design.Restaurant_Design;

import java.util.Date;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/13 20:26
 *   @Description :
 *
 */
public class Reservation {
    private Table table;
    private Date date;

    public Reservation(Table table, Date date){
        this.table = table;
        this.date = date;
    }

    public Table getTable() {
        return table;
    }

    public Date getDate() {
        return date;
    }
}
