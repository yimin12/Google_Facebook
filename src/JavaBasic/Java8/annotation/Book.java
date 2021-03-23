package JavaBasic.Java8.annotation;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/22 19:05
 *   @Description :
 *
 */
@Author(name = "Yimin")
@Author(name = "Huang")
@Author(name = "ShySensei")
public class Book {

    public static void main(String[] args) {
        Author[] authors = Book.class.getAnnotationsByType(Author.class); // take value from annotation
        Arrays.asList(authors).stream().forEach(a -> {
            System.out.println(a.name());
        });
    }
}
