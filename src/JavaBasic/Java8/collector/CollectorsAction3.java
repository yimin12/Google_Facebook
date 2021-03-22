package JavaBasic.Java8.collector;

import JavaBasic.Java8.stream.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static JavaBasic.Java8.collector.CollectorsAction.menu;
/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/21 18:58
 *   @Description :
 *
 */
public class CollectorsAction3 {

    public static void main(String[] args) {
        testPartitioningByWithPredicate();
        testPartitioningByWithPredicateAndCollector();
        testReducingBinaryOperator();
        testReducingBinaryOperatorAndIdentiy();
        testReducingBinaryOperatorAndIdentiyAndFunction();
        testSummarizingDouble();
        testSummarizingLong();
        testSummarizingInt();
    }

    // 根据是否素食进行分类，如果是素食则归为一类，否则归为另外一类。
    private static void testPartitioningByWithPredicate(){
        System.out.println("testPartitioningByWithPredicate");
        Map<Boolean, List<Dish>> collect = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testPartitioningByWithPredicateAndCollector(){
        System.out.println("testPartitioningByWithPredicateAndCollector");
        Map<Boolean, Double> collect = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.averagingInt(Dish::getCalories)));
        Optional.of(collect).ifPresent(System.out::println);
    }

    /**
    * Represents an operation upon two operands of the same type, producing a result
    * of the same type as the operands.  This is a specialization of
    * {@link BiFunction} for the case where the operands and the result are all of
    * the same type.
    */
    private static void testReducingBinaryOperator(){
        System.out.println("testReducingBinaryOperator");
        menu.stream().collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))));
    }

    private static void testReducingBinaryOperatorAndIdentiy(){
        System.out.println("testReducingBinaryOperatorAndIdentity");
        Integer result = menu.stream().map(Dish::getCalories).collect(Collectors.reducing(0, (d1, d2) -> d1 + d2));
        System.out.println(result);
    }

    private static void testReducingBinaryOperatorAndIdentiyAndFunction() {
        System.out.println("testReducingBinaryOperatorAndIdentiyAndFunction");
        Integer result = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (d1, d2) -> d1 + d2));
        System.out.println(result);
    }

    private static void testSummarizingDouble() {
        System.out.println("testSummarizingDouble");
        Optional.of(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testSummarizingLong() {
        System.out.println("testSummarizingLong");
        Optional.of(menu.stream().collect(Collectors.summarizingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testSummarizingInt() {
        System.out.println("testSummarizingLong");
        Optional.of(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }
}



