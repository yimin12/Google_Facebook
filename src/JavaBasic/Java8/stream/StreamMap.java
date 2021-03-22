package JavaBasic.Java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/20 21:39
 *   @Description :
 *
 */
public class StreamMap {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 6, 7, 7, 1);
        List<Integer> result = list.stream().map(i -> i * 2).collect(toList());
        System.out.println(result);

        listDish().stream().map(d -> d.getName()).forEach(System.out::println);
        List<String> dishes = listDish().stream().map(d->d.getName()).collect(toList());
        System.out.println(dishes);

        // flatMap flat operation
        String[] words = {"Hello", "World"};
        Stream<String[]> stream = Arrays.stream(words).map(w -> w.split(""));        //{h,e,l,l,o},{W,o,r,l,d}
        Stream<String> stringStream = stream.flatMap(Arrays::stream);  //H,e,l,l,o,W,o,r,l,d
        stringStream.distinct().forEach(System.out::println);
    }

    private static List<Dish> listDish() {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        return menu;
    }

}
