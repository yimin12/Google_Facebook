package MultiThreading.DesignPattern.FlightSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/23 21:50
 *   @Description :
 *
 */
public class FlightQueryTask extends Thread implements FlightQuery {

    private final String from; // where you from
    private final String to; // where you want to go
    private final List<String> flightList = new ArrayList<>(); // results of the search

    public FlightQueryTask(String airline, String from, String to){
        super("{" + airline + "}");
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        System.out.printf("%s-query from %s to %s \n", this.getName(), this.from, this.to);
        int random = ThreadLocalRandom.current().nextInt(10);
        try{
            TimeUnit.SECONDS.sleep(random);
            // add the results of search to the fightList
            this.flightList.add(this.getName() + " - " + random);
            System.out.printf("The flight:%s list query successful \n", this.getName());
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<String> get() {
        return this.flightList;
    }
}
