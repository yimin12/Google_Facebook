package JavaBasic.Java8.collector;

import JavaBasic.Java8.stream.Dish;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static JavaBasic.Java8.collector.CollectorsAction.menu;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/21 19:27
 *   @Description :
 *
 */
public class CollectorsAction4 {

    public static void main(String[] args) {
        testSummingDouble();
        testSummingLong();
        testSummingInt();
        testToCollection();
        testToConcurrentMap();
        testToConcurrentMapWithBinaryOperator();
        testToConcurrentMapWithBinaryOperatorAndSupplier();

        testToList();
        testToSet();

        testToMap();
        testToMapWithBinaryOperator();
        testToMapWithBinaryOperatorAndSupplier();
    }

    private static void testSummingDouble(){
        System.out.println("testSummingDouble");
        Optional.of(menu.stream().collect(Collectors.summingDouble(Dish::getCalories))).ifPresent(System.out::println);
        Optional.of(menu.stream().map(Dish::getCalories).mapToInt(Integer::intValue).sum()).ifPresent(System.out::println);
    }

    private static void testSummingLong(){
        System.out.println("testSummingLong");
        Optional.of(menu.stream().collect(Collectors.summingInt(Dish::getCalories))).ifPresent(System.out::println);
    }

    private static void testSummingInt() {
        System.out.println("testSummingInt");
        Optional.of(menu.stream().collect(Collectors.summingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    // put all qualified elements to an collections
    private static void testToCollection(){
        System.out.println("testToCollection");
        Optional.of(menu.stream().filter(d -> d.getCalories() > 600).collect(Collectors.toCollection(LinkedList::new))).ifPresent(System.out::println);
    }

    private static void testToConcurrentMap(){
        System.out.println("testToConcurrentMap");
        Optional.of(menu.stream().collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories))).ifPresent(v -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });
    }

    /**
     * Type:Total
     */
    private static void testToConcurrentMapWithBinaryOperator(){
        System.out.println("testToConcurrentMapWithBinaryOperator");
        /**
         *   * @param <T> the type of the input elements
         *      * @param <K> the output type of the key mapping function
         *      * @param <U> the output type of the value mapping function
         *      * @param keyMapper a mapping function to produce keys
         *      * @param valueMapper a mapping function to produce values
         *      * @param mergeFunction a merge function, used to resolve collisions between
         *      *                      values associated with the same key, as supplied
         *      *                      to {@link Map#merge(Object, Object, BiFunction)}
         *      * @return a concurrent, unordered {@code Collector} which collects elements into a
         *      * {@code ConcurrentMap} whose keys are the result of applying a key mapping
         *      * function to the input elements, and whose values are the result of
         *      * applying a value mapping function to all input elements equal to the key
         *      * and combining them using the merge function
         */
        Optional.of(menu.stream().collect(Collectors.toConcurrentMap(Dish::getType, v -> 1L, (a, b) -> a + b))).ifPresent(v -> {
            System.out.println(v);
            System.out.println(v.getClass());;
        });
    }

    /**
     * Type:Total
     */
    private static void testToConcurrentMapWithBinaryOperatorAndSupplier(){
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        System.out.println("testToConcurrentMapWithBinaryOperatorAndSupplier");
        Optional.of(menu.stream().collect(Collectors.toConcurrentMap(Dish::getType, v -> 1L, (a, b) -> a + b, ConcurrentSkipListMap::new)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                        });
    }

    private static void testToList(){
        Optional.of(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList())).ifPresent(r -> {
            System.out.println(r.getClass());
            System.out.println(r);
        });
    }

    private static void testToSet(){
        Optional.of(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toSet())).ifPresent(r -> {
            System.out.println(r.getClass());
            System.out.println(r);
        });
    }

    private static void testToMap(){
        System.out.println("testToMap");
        Optional.of(menu.stream().collect(
                Collectors.collectingAndThen(Collectors.toMap(Dish::getName, Dish::getCalories), Collections::synchronizedMap)
        )).ifPresent(v -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for(Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()){
            Thread key = entry.getKey();
            StackTraceElement[] value = entry.getValue();
            if(key.getId() != Thread.currentThread().getId()) continue;
            for(StackTraceElement v : value){
                if(v.isNativeMethod()){
                    continue;
                }
                System.out.println(v.getClassName());
                System.out.println("isNativeMethod" + v.isNativeMethod());
                System.out.println(v.getMethodName());
                System.out.println(v.getLineNumber());
                System.out.println(v.getFileName());
            }
        }
    }

    private static void testToMapWithBinaryOperator(){
        System.out.println("testToMapWithBinaryOperator");
        Optional.of(menu.stream().collect(Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b))).ifPresent(v -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });
    }

    private static void testToMapWithBinaryOperatorAndSupplier(){
        System.out.println("testToMapWithBinaryOperatorAndSupplier");
        Optional.of(menu.stream().collect(Collectors.toMap(Dish::getType, v -> 1L, (a, b)->a + b, Hashtable::new))).ifPresent(v -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });
    }
}
