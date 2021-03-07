package Object_Orient_Design.Design_Pattern.Iterator;

import java.util.ArrayList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 17:14
 *   @Description :
 *
 */
public class MyConcreteAggregator {

    private List<Object> list = new ArrayList<>();

    public void addObj(Object obj){
        this.list.add(obj);
    }

    public void removeObj(Object obj){
        this.list.remove(obj);
    }

    public List<Object> getList(){
        return list;
    }

    public void setList(List<Object> list){
        this.list = list;
    }

    public MyIterator createIterator(){
        return new ConcreteIterator();
    }

    private class ConcreteIterator implements MyIterator{

        private int cursor;

        @Override
        public void first() {
            cursor = 0;
        }

        @Override
        public Object getCurrentObj() {
            return list.get(cursor);
        }

        @Override
        public boolean hasNext() {
            if(cursor<list.size()){
                return true;
            }
            return false;
        }

        @Override
        public boolean isFirst() {
            return cursor==0?true:false;
        }

        @Override
        public boolean isLast() {
            return cursor==(list.size()-1)?true:false;
        }

        @Override
        public void next() {
            if(cursor<list.size()){
                cursor++;
            }
        }
    }
}
