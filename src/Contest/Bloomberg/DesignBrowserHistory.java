package Contest.Bloomberg;

import java.util.ArrayList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/3 20:23
 *   @Description :
 *
 */
public class DesignBrowserHistory {

    List<String> cache;
    int cur, last;

    public DesignBrowserHistory(String homepage) {
        cache = new ArrayList<>();
        cache.add(homepage);
        cur = 0;
        last = 0;
    }

    public void visit(String url) {
        if(cache.size() == cur + 1){
            cache.add(url); // adding new url to the end
            cur ++;
            last = cur;
        } else {
            // cache.size() > cur + 1, used back before
            cache.set(++cur, url);
            last = cur; // It clears up all the forward history
        }
    }

    public String back(int steps) {
        cur = Math.max(0, cur - steps);
        return cache.get(cur);
    }

    public String forward(int steps) {
        cur = Math.min(cur + steps, last);
        return cache.get(cur);
    }
}
