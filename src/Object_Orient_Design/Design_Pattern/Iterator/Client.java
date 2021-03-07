package Object_Orient_Design.Design_Pattern.Iterator;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 17:17
 *   @Description :
 *
 */
public class Client {

    public static void main(String[] args) {
        MyConcreteAggregator cma = new MyConcreteAggregator();
        cma.addObj("aa");
        cma.addObj("bb");
        cma.addObj("cc");

        MyIterator iter = cma.createIterator();
        while(iter.hasNext()){
            System.out.println(iter.getCurrentObj());
            iter.next();
        }

    }
}
