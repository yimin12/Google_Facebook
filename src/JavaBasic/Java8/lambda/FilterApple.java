package JavaBasic.Java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/19 22:44
 *   @Description :
 *
 */
public class FilterApple {

    // inner interface, you can use annotation here
    @FunctionalInterface
    public interface AppleFilter {
        boolean filter(Apple apple);
    }

    // good to know about this design pattern
    public static List<Apple> findApple(List<Apple> apples, AppleFilter appleFilter){
        List<Apple> list = new ArrayList<>();
        for(Apple apple : apples){
            if(appleFilter.filter(apple)){
                list.add(apple);
            }
        }
        return list;
    }

    public static class GreenAnd160WeightFilter implements AppleFilter {
        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals("green") && apple.getWeight() >= 160);
        }
    }

    public static class YellowLess150WeightFilter implements AppleFilter{
        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals("yellow") && apple.getWeight() < 150);
        }
    }

    // tradition way to find green apple
    public static List<Apple> findGreenApple(List<Apple> apples){
        List<Apple> list = new ArrayList<>();
        for(Apple apple : apples){
            if("green".equals(apple.getColor())){
                list.add(apple);
            }
        }
        return list;
    }

    public static List<Apple> findApple(List<Apple> apples, String color){
        List<Apple> list = new ArrayList<>();
        for(Apple apple : apples){
            if(color.equals(apple.getColor())){
                list.add(apple);
            }
        }
        return list;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Apple> list = Arrays.asList(new Apple("green", 150), new Apple("yellow", 120), new Apple("green", 170));
        /*List<Apple> greenApples = findGreenApple(list);
        assert greenApples.size() == 2;
        List<Apple> greenApples2 = findApple(list, "green");
        assert greenApples2.size() == 2;
        List<Apple> result = findApple(list, new GreenAnd160WeightFilter());
        System.out.println(result);
        List<Apple> yellowList = findApple(list, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                return "yellow".equals(apple.getColor());
            }
        });
        System.out.println(yellowList.toString());*/

        // you can use lambda expression, save the method implementation
        List<Apple> lambdaResult = findApple(list, apple -> apple.getColor().equals("green"));
        System.out.println(lambdaResult);

        // create thread
        new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
        Thread.currentThread().join();
    }


}
