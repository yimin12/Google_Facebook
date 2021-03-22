package JavaBasic.Java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/20 18:57
 *   @Description :
 *
 */
public class SimpleStream {

    public static void main(String[] args) {
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

        // stream should be invoked by iterable
        /*Stream<Dish> stream = menu.stream();
        stream.forEach(System.out::println);*/

        Stream<Dish> dishStream = Stream.of(new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));
        dishStream.forEach(System.out::println);

        System.out.println("=================================");

        List<String> result = menu.stream().filter(d -> {
            System.out.println("filtering -> " + d.getName());
            return d.getCalories() > 300;
        }).map(d -> {
            System.out.println("map -> " + d.getName());
            return d.getName();
        }).limit(3).collect(toList());
        System.out.println(result);

        List<String> dishNameByCollections = getDishNameByCollections(menu);
        System.out.println(dishNameByCollections);

        final List<String> dishNameByStream = getDishNameByStream(menu);
        System.out.println(dishNameByStream);
    }

    private static List<String> getDishNameByStream(List<Dish> menu){
        return menu.parallelStream().filter(d -> {
            try{
                Thread.sleep(10000);
            } catch (InterruptedException e ){
                e.printStackTrace();
            }
            return d.getCalories() < 400;
        }).sorted(comparing(Dish::getCalories)).map(Dish::getName).collect(toList());
    }

    private static List<String> getDishNameByCollections(List<Dish> menu){
        List<Dish> lowCalories = new ArrayList<>();
        for(Dish dish : menu){
            if(dish.getCalories() < 400){
                lowCalories.add(dish);
            }
        }
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Collections.sort(lowCalories, (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));
        List<String> dishNamelist = new ArrayList<>();;
        for(Dish dish : lowCalories) {
            dishNamelist.add(dish.getName());
        }
        return dishNamelist;
    }
}
