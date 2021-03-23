package JavaBasic.Java8.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/22 19:01
 *   @Description :
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Authors {

    Author[] value();
}
