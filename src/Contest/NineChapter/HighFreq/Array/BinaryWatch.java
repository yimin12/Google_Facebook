package Contest.NineChapter.HighFreq.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * Given a watch with a binary display time and a non-negative integer n which represents the number of 1s on a given timetable, return all possible time.
 *
 * The order of output does not matter.
 * The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 * The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 * hour < 12, minute < 60
 * Example
 * Example1
 *
 * Input: 1
 * Output: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 */
public class BinaryWatch {

    public List<String> readBinaryWatch(int num) {
        ArrayList<String> ans = new ArrayList<String>();
        ArrayList<ArrayList<Integer>> hour = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> min = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < 4; i++) {
            hour.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < 6; i++) {
            min.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < 12; i++) {
            int n = Integer.bitCount(i);
            hour.get(n).add(i);
        }
        for(int i = 0; i < 60; i++) {
            min.add(new ArrayList<Integer>());
            int n = Integer.bitCount(i);
            min.get(n).add(i);
        }
        for(int i = 0; i <= num && i < 4; i++) {
            for(int h = 0; h < hour.get(i).size(); h++) {
                for(int m = 0; m < min.get(num - i).size() && num - i < 6; m++) {
                    String string = hour.get(i).get(h).toString() + ":";
                    if(min.get(num - i).get(m) < 10) {
                        string += "0";
                    }
                    string += min.get(num - i).get(m).toString();
                    ans.add(string);
                }
            }
        }
        return ans;
    }
}
