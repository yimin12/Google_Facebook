package JavaBasic.Java8.stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/20 22:29
 *   @Description :
 *
 */
public class StreamReduce {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7});
        Integer result = stream.reduce(0, Integer::sum); // sum + start
        System.out.println(result);

        /**
         * If a value is present, invoke the specified consumer with the value,
         * otherwise do nothing.
         *
         * @param consumer block to be executed if a value is present
         * @throws NullPointerException if value is present and {@code consumer} is
         * null
         */
        stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7});
        stream.reduce((i, j) -> i + j).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce(Integer::max).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce(Integer::min).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce((i, j) -> i > j ? j : i).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Integer reduce = stream.filter(i -> i % 2 == 0).reduce(1, (i, j) -> i * j); // 偶数位上的连续积 2 * 4 * 6 = 48
        Optional.of(reduce).ifPresent(System.out::println);
    }
}
