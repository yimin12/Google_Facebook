package Algorithm.Iterator;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/3 20:02
 *   @Description :
 *      Given a nested list of integers, implement an iterator to flatten it.
        Each element is either an integer, or a list -- whose elements may also be integers or other lists.
        Example 1:
        Input: [[1,1],2,[1,1]]
        Output: [1,1,2,1,1]

        Explanation:
        By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
        Example 2:
        Input: [1,[4,[6]]]
        Output: [1,4,6]

        Explanation:
        By calling next repeatedly until hasNext returns false, the order of elements ret
 */
public class FlattenNestedList {

    // it could be a single value or a single list
    class NestedInteger {
        private Integer value;
        private List<NestedInteger> list;
        private boolean isSingle;
        public NestedInteger(Integer value, List<NestedInteger> list){
            this.value = value;
            this.list = list;
            this.isSingle = this.value == null;
        }
        public Integer getInteger(){
            return value;
        }
        public boolean isSingle(){
            return this.isSingle;
        }
        public List<NestedInteger> getList(){
            return this.list;
        }
    }

    // Stack
    class NestedIterator implements Iterator<Integer>{

        // use stack as a helper
        private Deque<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestList){
            stack = new LinkedList<>();
            flattenList(nestList);
        }

        @Override
        public boolean hasNext() {
            while(!stack.isEmpty()){
                if(stack.peek().isSingle) return true;
                flattenList(stack.pop().getList());
            }
            return false;
        }

        @Override
        public Integer next() {
            return hasNext() ? stack.pop().getInteger() : null;
        }

        private void flattenList(List<NestedInteger> list){
            for(int i = list.size() - 1; i >= 0; i--){
                stack.push(list.get(i));
            }
        }
    }

    // Using Queue to store
    public class NestedIteratorI implements Iterator<Integer>{

        private Queue<Integer> queue = new LinkedList<>();

        public NestedIteratorI(List<NestedInteger> nestedList){
            flatten(nestedList);
        }

        private void flatten(List<NestedInteger> nestedList){
            if(nestedList == null){
                return;
            }
            for(NestedInteger val : nestedList){
                if(val.isSingle()){
                    queue.offer(val.getInteger());
                } else {
                    flatten(val.getList());
                }
            }
        }
        @Override
        public boolean hasNext() {
            if(!queue.isEmpty()){
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Integer next() {
            if(hasNext()){
                return queue.poll();
            } else {
                return -1;
            }
        }
    }
}
