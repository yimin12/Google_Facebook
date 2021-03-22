package MultiThreading.DesignPattern.BalkingPattern;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 17:14
 *   @Description :
 *
 */
public class AutoSaveThread extends Thread{

    private final Document document;

    public AutoSaveThread(Document document){
        this.document = document;
    }

    @Override
    public void run() {
        while(true){
            try {
                document.save();
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException | IOException exception) {
                exception.printStackTrace();
                break;
            }
        }
    }
}
