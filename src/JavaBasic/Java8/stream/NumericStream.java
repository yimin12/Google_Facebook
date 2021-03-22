package JavaBasic.Java8.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/20 23:14
 *   @Description :
 *
 */
public class NumericStream {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        IntStream intStream = stream.mapToInt(i -> i.intValue());
        int result = intStream.filter(i -> i > 3).sum();
        System.out.println(result);

        int a = 9;
        IntStream.rangeClosed(1, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed().map(b -> new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a=" + r[0] + ",b=" + r[1] + ",c=" + r[2]));

        IntStream.rangeClosed(1, 100).filter(b -> Math.sqrt(a*a + b*b)%1 == 0)
                .mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a*a + b*b)})
                .forEach(r -> System.out.println("a=" + r[0] + ",b=" + r[1] + ",c=" + r[2]));
    }
}
