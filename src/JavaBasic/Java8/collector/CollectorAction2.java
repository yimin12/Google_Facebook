package JavaBasic.Java8.collector;

import JavaBasic.Java8.stream.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static JavaBasic.Java8.collector.CollectorsAction.menu; // 使用静态方法导入
/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/21 18:21
 *   @Description :
 *
 */
public class CollectorAction2 {

    public static void main(String[] args) {
        testGroupingByConcurrentWithFunction();
        testGroupingByConcurrentWithFunctionAndCollector();
        testGroupingByConcurrentWithFunctionAndSupplierAndCollector();
        testJoining();
        testJoiningWithDelimiter();
        testJoiningWithDelimiterAndPrefixAndSuffix();
        testMapping();
        testMaxBy();
        testMinBy();
    }

    private static void testGroupingByConcurrentWithFunction(){
        System.out.println("testGroupingByConcurrentWithFunction");
        ConcurrentMap<Dish.Type, List<Dish>> collect = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType)); // groupingByConcurrent's default container is concurrentMap
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testGroupingByConcurrentWithFunctionAndCollector() {
        System.out.println("testGroupingByConcurrentWithFunctionAndCollector");
        ConcurrentMap<Dish.Type, Double> collect = menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testGroupingByConcurrentWithFunctionAndSupplierAndCollector(){
        System.out.println("testGroupingByConcurrentWithFunctionAndSupplierAndCollector");
        ConcurrentSkipListMap<Dish.Type, Double> collect = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType, ConcurrentSkipListMap::new, Collectors.averagingInt(Dish::getCalories)));
        Optional.of(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testJoining(){
        System.out.println("testJoining");
        Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining())).ifPresent(System.out::println);
    }

    private static void testJoiningWithDelimiter() {
        System.out.println("testJoiningWithDelimiter");
        Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining(",")))
                .ifPresent(System.out::println);
    }

    private static void testJoiningWithDelimiterAndPrefixAndSuffix(){
        System.out.println("testJoiningWithDelimiterAndPrefixAndSuffix");
        Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining(",", "Names[", "]"))).ifPresent(System.out::println);
    }

    // pork,beef,chicken,french fries,rice,season fruit,pizza,prawns,salmon
    private static void testMapping(){
        System.out.println("testMapping");
        Optional.of(menu.stream().collect(Collectors.mapping(Dish::getName, Collectors.joining(",")))).ifPresent(System.out::println);
    }

    private static void testMaxBy(){
        System.out.println("testMaxBy");
        menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))).ifPresent(System.out::println);
    }

    private static void testMinBy(){
        System.out.println("testMinBy");
        menu.stream().collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories))).ifPresent(System.out::println);
    }

}
