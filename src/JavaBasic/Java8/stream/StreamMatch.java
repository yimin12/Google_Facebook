package JavaBasic.Java8.stream;

import java.util.Arrays;
import java.util.stream.Stream;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/20 22:45
 *   @Description :
 *
 */
public class StreamMatch {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        boolean matched = stream.allMatch(i -> i > 10);
        System.out.println(matched);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        matched = stream.anyMatch(i -> i > 6);

        System.out.println(matched);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        matched = stream.noneMatch(i -> i < 0);
        System.out.println(matched);

    }
}
