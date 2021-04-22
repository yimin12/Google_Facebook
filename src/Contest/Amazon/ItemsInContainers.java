package Contest.Amazon;

import java.util.*;
import java.util.stream.Collectors;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/14 23:21
 *   @Description :
 *
 */
public class ItemsInContainers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int rangesLen = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> ranges = new ArrayList<>();
        for(int i = 0; i < rangesLen; i ++){
            ranges.add(Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        List<Integer> res = numberOfItems(s, ranges);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));

    }

    public static List<Integer> numberOfItems(String s, List<List<Integer>> ranges){
        List<Integer> res = new ArrayList<Integer>();
        if(s == null || s.length() == 0) return res;
        int len = s.length();
        Map<Integer, Integer> prefixSum = new HashMap<>();
        int curSum = 0;
        for(int i = 0; i < len; i ++){
            if(s.charAt(i) == '|'){
                prefixSum.put(i, curSum);
            } else {
                curSum ++;
            }
        }
        // left: value represent the left border
        // right: value represent the right border
        int[] left = new int[len], right = new int[len];
        int last = -1;
        for(int i = 0; i < len; i ++){
            if(s.charAt(i) == '|'){
                last = i;
            }
            left[i] = last;
        }
        last = -1;
        for(int i = len - 1; i >= 0; i --){
            if(s.charAt(i) == '|'){
                last = i;
            }
            right[i] = last;
        }
        for(int i = 0; i < ranges.size(); i ++){
            int start = right[ranges.get(i).get(0)];
            int end = left[ranges.get(i).get(1)];
            if(start != -1 && end != -1 && start < end){
                res.add(prefixSum.get(end) - prefixSum.get(start));
            } else {
                res.add(0);
            }
        }
        return res;
    }
}
