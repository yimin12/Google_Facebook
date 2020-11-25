package DataStructure.Design.Heap;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/13 21:34
 *   @Description :
 *
 */
public class MinIndexedBinaryHeap<T extends Comparable<T>> extends MinIndexedDHeap {

    public MinIndexedBinaryHeap(int maxSize) {
        super(2, maxSize);
    }
}
