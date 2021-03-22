package JavaBasic.Java8.optional;

import java.util.Optional;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/21 12:34
 *   @Description :
 *
 */
public class Person {

    private Optional<Car> car;

    public Optional<Car> getCar(){
        return this.car;
    }
}
