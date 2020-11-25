package MultiThreading.ProducerConsumer;

import javax.swing.*;
import java.util.stream.Stream;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/23 23:38
 *   @Description :
 *
 */
public class ProducerConsumer {

    private final Object LOCK = new Object();
    private int i;
    private boolean isProduced;

    // Producer
    public void produce(){
        synchronized (LOCK){
            if(isProduced){
                try{
                    LOCK.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println(Thread.currentThread().getName() + " -> " + i);
                isProduced = !isProduced;
                LOCK.notifyAll();
            }
        }
    }

    // Consumer
    public void consume(){
        synchronized(LOCK){
            if(!isProduced){
                try{
                    LOCK.wait();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " -> " + i);
                isProduced = !isProduced;
                LOCK.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        Stream.of("p1", "p2", "p3").forEach(p->{
            new Thread(p){
                @Override
                public void run() {
                    while(true) pc.produce();
                }
            }.start();
        });

        Stream.of("C1", "C2", "C3", "C4", "C5").forEach(c ->{
            new Thread(c){
                @Override
                public void run() {
                    while(true) pc.consume();
                }
            }.start();
        });
    }
}
