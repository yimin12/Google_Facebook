package JavaBasic.Java8.collector;

import JavaBasic.Java8.stream.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/21 18:47
 *   @Description :
 *
 */
public class CollectorReduce {

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

        long count = menu.stream().filter(d -> d.isVegetarian()).count();
        Long collect = menu.stream().filter(d -> d.isVegetarian()).collect(Collectors.counting());
        System.out.println(count + " : " + collect);

        // Get the food that have max calories
//        Optional<Integer> maxCalories = menu.stream().map(Dish::getCalories).reduce(Integer::max);
        Optional<Dish> maxCalories = menu.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
        maxCalories.ifPresent(System.out::println);

        Optional<Dish> maxCaloriesCollect = menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
        maxCaloriesCollect.ifPresent(System.out::println);
        
        // param1 : collectors  param2: Function Interface
        Integer collectInteger = menu.stream().collect(Collectors.collectingAndThen(toList(), t -> t.size()));
        Map<Dish.Type, List<Dish>> collect1 = menu.stream().collect(Collectors.groupingBy(Dish::getType)); // partition by type
        Map<Dish.Type, Double> collect2 = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories))); // partition by type and aggregating by average.
        Optional.of(collect1).ifPresent(System.out::println);
    }

}
