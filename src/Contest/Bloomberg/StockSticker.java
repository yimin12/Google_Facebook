package Contest.Bloomberg;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/3 23:58
 *   @Description :
 *
 */

import java.util.*;

/**
 * Give a stream of stock prices, design a data structure to support the following operations:
 *
 * StockSticker(int k) Initialize the size of the ticker.
 * void addOrUpdate(String stock, double price) add or update the price of a stock to the data structure.
 * List<Map.Entry<String, double>> top() return the top k price stocks and their current prices.
 */
public class StockSticker {

    private final int k;
    private Map<String, Double> map;
    private TreeSet<Map.Entry<String, Double>> set;

    // Initialize the size of the ticker.
    public StockSticker(int k){
        this.k = k;
        map = new HashMap<String, Double>();
        set = new TreeSet<>(new Comparator<Map.Entry<String, Double>>(){
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                int res = o2.getValue().compareTo(o1.getValue());
                if(res == 0) return o2.getKey().compareTo(o2.getKey());
                return res;
            }
        });
    }

    // void addOrUpdate(String stock, double price) add or update the price of a stock to the data structure.
    public void addOrUpdate(String stock, double price){
        AbstractMap.SimpleEntry<String, Double> entry = new AbstractMap.SimpleEntry<>(stock, price);
        if(map.containsKey(stock)){
            set.remove(new AbstractMap.SimpleEntry<>(stock, map.get(stock)));
        }
        map.put(stock, price); // update
        set.add(entry);
    }

    // List<Map.Entry<String, double>> top() return the top k price stocks and their current prices.
    public List<Map.Entry<String, Double>> top() {
        List<Map.Entry<String, Double>> res = new ArrayList<>();
        int i = 0;
        Iterator<Map.Entry<String, Double>> iterator = set.iterator();
        while(i < k && iterator.hasNext()){
            res.add(iterator.next());
            i ++;
        }
        return res;
    }
}
