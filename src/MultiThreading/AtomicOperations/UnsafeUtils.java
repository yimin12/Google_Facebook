package MultiThreading.AtomicOperations;

import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/16 22:40
 *   @Description :
 *      Use Unsafe to simulate to do some evils
 */
public class UnsafeUtils {

    private static Unsafe getUnsafe(){
        try{
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        // Common way to create instance
//        Simple simple = new Simple();
//        System.out.println(simple.get());
//        Simple simple = Simple.class.newInstance(); // it will trigger constructor
//        Class.forName("MultiThreading.AtomicOperations.UnsafeUtils$Simple");
        Unsafe unsafe = getUnsafe();
//        Simple simple = (Simple)unsafe.allocateInstance(Simple.class); // it will not trigger constructor but create an instance
//        System.out.println(simple.get());
//        System.out.println(simple.getClass());
//        System.out.println(simple.getClass().getClassLoader()); // sun.misc.Launcher$AppClassLoader

//        Guard guard = new Guard();
//        guard.work();
//
//        Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
//        unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42);
//        guard.work();

//        byte[] bytes = loadClassContent();
//        Class aClass = unsafe.defineClass(null, bytes, 0, bytes.length, null, null);
//        int res = (Integer)aClass.getMethod("get").invoke(aClass.newInstance(), null);
//        System.out.println(res);
        System.out.println(sizeOf(new Simple()));
    }

    // Calculate the total size of class
    private static long sizeOf(Object obj){
        Unsafe unsafe = getUnsafe();
        Set<Field> fields = new HashSet<Field>();
        Class clazz = obj.getClass();
        while(clazz != Object.class){
            Field[] declaredFields = clazz.getDeclaredFields();
            for(Field f : declaredFields){
                if((f.getModifiers() & Modifier.STATIC) == 0){
                    fields.add(f);
                }
            }
            clazz = clazz.getSuperclass();
        }
        long maxOffset = 0;
        for(Field f : fields){
            long offSet = unsafe.objectFieldOffset(f);
            if(offSet > maxOffset){
                maxOffset = offSet;
            }
        }
        return ((maxOffset/8) + 1) * 8;
    }

    // byte array that hold class infomation
    private static byte[] loadClassContent() throws IOException {
        File f = new File("F:\\A.class");
        FileInputStream fis = new FileInputStream(f);
        byte[] content = new byte[(int) f.length()];
        fis.read(content);
        fis.close();
        return content;
    }

    static class Guard{
        private int ACCESS_ALLOWED = 1;

        private boolean allow(){
            return 42 == ACCESS_ALLOWED;
        }

        public void work(){
            if(allow()){
                System.out.println("Yes you have hacked in");
            }
        }
    }

    static class Simple {
        // java 8 优化了自动对齐，与C++一样
        private int i = 10;
        private long l = 0;
        private byte b = (byte) 0x01;
        public Simple(){
            this.l = 1;
            System.out.println("=========================");
        }

        public long get(){
            return this.l;
        }
    }
}
