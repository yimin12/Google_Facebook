package JavaBasic.Generic;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/15 22:58
 *   @Description :
 *
 */
public class GlmapperGeneric<T> {

    private T t;
    public void set(T t){
        this.t = t;
    }
    public T get(){
        return t;
    }
    public static void main(String[] args) {

    }

    // 不指定类型
    public void noSpecifyType(){
        GlmapperGeneric glmap = new GlmapperGeneric();
        // 需要强制类型转换
        glmap.set("test");
        String test = (String) glmap.get();
        System.out.println(test);
    }

    /**
     * 指定类型
     */
    public void specifyType(){
        GlmapperGeneric<String> glmap = new GlmapperGeneric<>();
        glmap.set("test");
        String test = glmap.get();
        System.out.println(test);
    }

}
