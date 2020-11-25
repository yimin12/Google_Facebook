package MultiThreading.FlightSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/23 22:13
 *   @Description :
 *
 */
public class App {

    private static List<String> airlineList = Arrays.asList("Unite Airline", "Detail Airline", "Sprite Airline");

    public static void main(String[] args) {
        List<String> results = search("Boston", "Los Angle");
    }

    private static List<String> search(String origin, String destination){
        final List<String> result = new ArrayList<>();
        // search all flight info
        List<FlightQueryTask> tasks = airlineList.stream().map(f -> new FlightQueryTask(f, origin, destination)).collect(Collectors.toList());
        tasks.forEach(Thread::start);
        tasks.forEach(t->{
            try{
                t.join(); // all threads need to use join(), because we need to guarantee that the main thread will run after all these working thread
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        // Current thread comes to BLOCKED state
        tasks.stream().map(FlightQuery::get).forEach(result::addAll);
        return result;
    }
}
