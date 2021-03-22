package JavaBasic.Java8.lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/19 23:54
 *   @Description :
 *      Consumer take parameters return nothing    Consumer accept(T t);
 *      Function take parameters and return value  Function<T, R> R apply(T t);
 *      Predicate is similar with Function, it take parameters and return boolean which is encapsulated in Predicate class. Predicate boolean test(T t);
 *      Supplier is like a getter by invoking static method in class Supplier<T> T get()
 */
public class LambdaExpression {

    public static void main(String[] args) {

        // 1. tradition way to use anonymous inner class
        Comparator<Apple> byColor = new Comparator<Apple>(){
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };

        List<Apple> list = Collections.emptyList();
        list.sort(byColor);

        // 2. use lambda expression
        Comparator<Apple> byColor2 = (o1, o2) -> o1.getColor().compareTo(o2.getColor());

        Function<String, Integer> flambda = s -> s.length(); // Function类在java8中与Object并列为顶级类，Function<String, Integer>，key代表传入值的类型，value代表返回指的类型

        /**
         * Evaluates this predicate on the given argument.
         *
         * @param t the input argument
         * @return {@code true} if the input argument matches the predicate,
         * otherwise {@code false}
         * boolean test(T t);
         */
        Predicate<Apple> p = (Apple a) -> a.getColor().equals("green"); // Predicate类是专门用于做判断返回的类，将返回值保存在Predicate类中，lambda默认调的参数是test(T t)，记得要将泛型传入进去

        Function<Apple,Boolean> f = (a)->a.getColor().equals("green");

        Supplier<Apple> s = Apple::new; // create a constructor
    }
}
