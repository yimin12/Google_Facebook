package Algorithm.Graph;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/27 17:14
 *   @Description :
 *      Medium
        Given a list of airline tickets represented by pairs of departure and arrival airports[from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs fromJFK. Thus, the itinerary must begin withJFK.
        Note:
        If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary
        ["JFK", "LGA"]
        has a smaller lexical order than
        ["JFK", "LGB"]
        .
        All airports are represented by three capital letters (IATA code).
        You may assume all tickets form at least one valid itinerary.
 */
public class ReconstructItinerary {

    Map<String, PriorityQueue<String>> flights;
    LinkedList<String> path;

    public List<String> findItinerary(String[][] tickets){
        flights = new HashMap<>();
        path = new LinkedList<>();
        for(String[] ticket : tickets){
            flights.putIfAbsent(ticket[0], new PriorityQueue<>());
            flights.get(ticket[0]).add(ticket[1]);
        }
        dfs("JFK");
        return path;
    }

    public void dfs(String depature){
        PriorityQueue<String> arrivals = flights.get(depature);
        while(arrivals != null && !arrivals.isEmpty()){
            dfs(arrivals.poll());
        }
        path.addFirst(depature);

    }
}
