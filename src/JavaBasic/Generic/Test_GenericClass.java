package JavaBasic.Generic;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/15 23:44
 *   @Description :
 *      定义的泛型类，就一定要传入泛型类型实参么？并不是这样，在使用泛型的时候如果传入泛型实参，则会根据传入的泛型实参做相应的限制，此时泛型才会起到本应起到的限制作用。如果不传入泛型类型实参的话，在泛型类中使用泛型的方法或成员变量定义的类型可以为任何的类型。
 */
public class Test_GenericClass {

    public static void main(String[] args) {
        test();
    }

    public static void test(){
        Generic<Integer> generic1 = new Generic<Integer>(123);
        Generic<Integer> generic2 = new Generic<>(123);
        Generic<String> genericString = new Generic<String>("my");
        System.out.println(generic1.getVar());
        System.out.println(genericString.getVar());
    }
}

/**
 * 1、此处T虽然可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
 * 但是为了代码的可读性一般来说：
 * K,V用来表示键值对
 * E是Element的缩写，常用来遍历时表示
 * T就是Type的缩写，常用在普通泛型类上
 * 2、还有一些不常见的U,R啥的
 */
class Generic<T>{
    private T var;

    public Generic(T var) {
        this.var = var;
    }

    public T getVar() {
        return var;
    }
}

class MyMap<K, V>{
    private K key;
    private V value;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
