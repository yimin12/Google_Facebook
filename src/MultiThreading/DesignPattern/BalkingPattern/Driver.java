package MultiThreading.DesignPattern.BalkingPattern;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 17:39
 *   @Description :
 *
 */
public class Driver {

    public static void main(String[] args) {
        new DocumentEditThread("D:\\", "testAutoSave.txt").start();
    }
}
