package JavaBasic.Java8.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/20 18:17
 *   @Description :
 *      lambda表达式的自动推导:
 *          1. A method reference to a static method (for example, the method parseInt of Integer, Integer::parseInt)
 *          2. A method reference to an instance method of an arbitrary type (for example, the method length of String, written String::length)
 *          3. A method reference to an instance method of an existing object (for example, suppose you have a local variable expensiveTransaction that holds an object of type
 *              Transaction, it supports an instance method getValue, you can write expensiveTransaction::getValue);
 */
public class MethodReference {

    public static void main(String[] args) {
        /*Consumer<String> consumer = (s) -> System.out.println(s);
        useConsumer(consumer, "Hello yimin");*/

//        useConsumer(s -> System.out.println(s), "hello Yimin");
//        useConsumer(System.out::println, "Hello yimin Huang"); // System.out.println implements Consumer
        List<Apple> list = Arrays.asList(new Apple("abc", 123), new Apple("Green", 110), new Apple("red", 123));
        System.out.println(list);

        list.sort((a1, a2) -> a1.getColor().compareTo(a2.getColor()));
        System.out.println(list);

        list.stream().forEach(System.out::println); // read it like a stream,
        System.out.println("==========================");

        // 这一部分与C++函数指针，利用指针直接调用函数类似
        int value = Integer.parseInt("123");
        Function<String, Integer> f = Integer::parseInt; // A method reference to a static method
        Integer result = f.apply("123");
        System.out.println(result);

        BiFunction<String,Integer,Character> f2 = String::charAt; // A method reference to an instance method of an arbitrary type
        Character c = f2.apply("hello", 2);
        System.out.println(c);

        String string = new String("Hello");
        Function<Integer, Character> f3 = string::charAt; // A method reference to an instance method of an existing object
        Character c2 = f3.apply(4);
        System.out.println(c2);

        Supplier<String> supplier = String::new;
        String s = supplier.get();
        String s2 = supplier.get();
        System.out.println("Equal or not : " + s == s2);

        BiFunction<String, Long, Apple> appleFunction = Apple::new;
        Apple apple = appleFunction.apply("red", 100L);
        System.out.println(apple);


        ThreeFunction<String, Long, String, ComplexApple> threeFunction = ComplexApple::new; // define your own Function interface
        ComplexApple complexApple = threeFunction.apply("Green", 123L, "FUSHI");
        System.out.println(complexApple);

        List<Apple> list2 = Arrays.asList(new Apple("abc", 123), new Apple("Green", 110), new Apple("red", 123));
        System.out.println(list2);
        list2.sort(Comparator.comparing(Apple::getColor));
        System.out.println(list2);
    }

    @FunctionalInterface
    private interface ThreeFunction<T,U,K,R>{
        R apply(T t, U u, K k);
    }

    private static <T> void useConsumer(Consumer<T> consumer, T t){
        consumer.accept(t);
//        consumer.accept(t);
    }
}
