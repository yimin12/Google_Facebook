package JavaBasic.Java8.stream;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/20 21:16
 *   @Description :
 *
 */
public class StreamFilter {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 6, 7, 7, 1);
        List<Integer> result = list.stream().filter(i -> i % 2 == 0).collect(toList());
        System.out.println(result);

        result = list.stream().distinct().collect(toList());
        System.out.println(result);

        result = list.stream().skip(50).collect(toList());
        System.out.println(result);

        list.forEach(System.out::println);
        list.forEach(i -> System.out.println(i));
        list.forEach((Integer i) -> System.out.println(i));
        for(int i : list){
            System.out.println(i);
        }

    }
}
