package JavaBasic.Java8.collector;

import JavaBasic.Java8.stream.Dish;

import java.util.*;
import java.util.stream.Collectors;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/21 14:07
 *   @Description :
 *      Accumulator type operator
 */
public class CollectorsAction {

    // 加上final关键字，在其他类调用这个menu，则是被动加载，没有占用内存。如果没有final关键字，则其他类调用这个menu会触发主动加载。
    public final static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    private static void testAveragingDouble(){
        System.out.println("testAveragingDouble");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories))).ifPresent(System.out::println); // 计算所有食物平均卡路里
    }

    private static void testAveragingInt(){
        System.out.println("testAveragingInt");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories))).ifPresent(System.out::println);
    }

    private static void testAveragingLong() {
        System.out.println("testAveragingLong");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    // you can do something after collecting, e.g. 1. collecting the average calories and then output it in well-form String.
    private static void testCollectingAndThen(){
        System.out.println("testCollectingAndThen");
        Optional.ofNullable(menu.stream().collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories), a -> "The Average Calories is->" + a)))
                .ifPresent(System.out::println);

//        List<Dish> list = menu.stream().filter(d -> d.getType().equals(Dish.Type.MEAT))
//                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList)); //说明这个collections不可以修改，否则抛出unsupported异常
//        list.add(new Dish("", false, 100, Dish.Type.OTHER));
//        System.out.println(list);
    }

    private static void testCounting(){
        System.out.println("testCounting");
        Optional.of(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);
    }

    private static void testGroupingByFunction(){
        System.out.println("testGroupingByFunction");
        Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType))).ifPresent(System.out::println);
    }

    private static void testGroupingByFunctionAndCollector() {
        System.out.println("testGroupingByFunctionAndCollector");
        Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    private static void testGroupingByFunctionAndSupplierAndCollector(){
        System.out.println("testGroupingByFunctionAndSupplierAndCollector");
        Map<Dish.Type, Double> map = menu.stream().collect(Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.averagingInt(Dish::getCalories)));
        Optional.of(map.getClass()).ifPresent(System.out::println);
        Optional.of(map).ifPresent(System.out::println);
    }

    private static void testSummarizingInt(){
        System.out.println("testSummarizingInt");
        IntSummaryStatistics result = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        Optional.of(result).ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        testAveragingDouble();
        testAveragingInt();
        testAveragingLong();
        testCollectingAndThen();
        testCounting();
        testGroupingByFunction();
        testGroupingByFunctionAndCollector();
        testGroupingByFunctionAndSupplierAndCollector();
        testSummarizingInt();
    }
}
