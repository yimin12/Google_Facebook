package JavaBasic.Java8.optional;

import java.util.Optional;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/21 13:35
 *   @Description :
 *
 */
public class OptionalInAction {

    public static void main(String[] args) {
        Optional.ofNullable(getInsuranceNameByOptional(null)).ifPresent(System.out::println);
    }

    private static String getInsuranceNameByOptional(Person person) {
        return Optional.ofNullable(person)
                .flatMap(Person::getCar).flatMap(Car::getInsurance)
                .map(Insurance::getName).orElse("Unknown");
    }
}
