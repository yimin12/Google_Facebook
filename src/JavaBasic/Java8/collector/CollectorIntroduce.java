package JavaBasic.Java8.collector;

import JavaBasic.Java8.lambda.Apple;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/21 13:41
 *   @Description :
 *
 */
public class CollectorIntroduce {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170)
                , new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170));

        List<Apple> greenList = list.stream().filter(a -> a.getColor().equals("green")).collect(toList());
        // use Optional to avoid NPE
        Optional.ofNullable(greenList).ifPresent(System.out::println);
        Optional.ofNullable(groupByNormal(list)).ifPresent(System.out::println); // convert map to color : apples
        System.out.println("===================================================");
        Optional.ofNullable(groupByFunction(list)).ifPresent(System.out::println);
        System.out.println("===================================================");
        Optional.ofNullable(groupByCollector(list)).ifPresent(System.out::println);


    }

    private static Map<String, List<Apple>> groupByNormal(List<Apple> apples){
        // key : color and value is list of apples that belong to the color
        Map<String, List<Apple>> map = new HashMap<>();
        for(Apple a : apples){
            List<Apple> list = map.get(a.getColor());
            if(null == list){
                list = new ArrayList<>();
                map.put(a.getColor(), list);
            }
            list.add(a);
        }
        return map;
    }

    private static Map<String, List<Apple>> groupByFunction(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();
        apples.parallelStream().forEach(a -> {
            List<Apple> colorList = Optional.ofNullable(map.get(a.getColor())).orElseGet(() -> {
                List<Apple> list = new ArrayList<>();
                map.put(a.getColor(), list);
                return list;
            });
            colorList.add(a);
        });
        return map;
    }

    private static Map<String, List<Apple>> groupByCollector(List<Apple> apples){
        return apples.parallelStream().collect(groupingBy(Apple::getColor)); // have inner implementation of grouping by
    }
}
