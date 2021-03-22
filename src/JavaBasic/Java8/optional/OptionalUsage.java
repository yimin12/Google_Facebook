package JavaBasic.Java8.optional;

import java.util.Optional;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/21 12:42
 *   @Description :
 *
 */
public class OptionalUsage {

    public static void main(String[] args) {
        Optional<Insurance> insuranceOptional = Optional.<Insurance>empty(); // no insurance reference
//        Insurance insurance = insuranceOptional.get();
//        System.out.println(insurance); // throw no value present exception

        Optional<Insurance> insuranceOptional2 = Optional.of(new Insurance());
        Insurance insurance = insuranceOptional2.get();
        System.out.println(insurance);

        Optional<Object> objectOptional = Optional.ofNullable(null); // create an null by optional
//        objectOptional.orElseGet(Insurance::new); // if null, create new object, and return
//        objectOptional.orElse(new Insurance()); // same
//        objectOptional.orElseThrow(RuntimeException::new); // if null, throw runtime exception
//        objectOptional.orElseThrow(() -> new RuntimeException("Not have reference"));

//        insurance = insuranceOptional.filter(i -> i.getName() != null).get();
//        System.out.println(insurance);

//        Optional<String> nameOptional = insuranceOptional.map(i -> i.getName());
//        System.out.println(nameOptional.orElse("empty value"));
//        nameOptional.ifPresent(System.out::println);

        System.out.println(getInsuranceName(null));
        System.out.println(getInsuranceNameByOptional(null));
    }

    private static String getInsuranceName(Insurance insurance){
        if(null == insurance){
            return "unknown";
        }
        return insurance.getName();
    }

    private static String getInsuranceNameByOptional(Insurance insurance){
        return Optional.ofNullable(insurance).map(Insurance::getName).orElse("unknown");
    }
}
