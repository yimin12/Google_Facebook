package Object_Orient_Design.Design_Pattern.Iterator;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 17:13
 *   @Description :
 *
 */
public interface MyIterator {

    void first();
    void next();
    boolean hasNext();
    boolean isFirst();
    boolean isLast();

    Object getCurrentObj();
}
