package MultiThreading.TwoPhaseTerminationPattern;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 21:17
 *   @Description :
 *
 */
public class AppServerClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        AppServer appServer = new AppServer(13345);
        appServer.start();

        TimeUnit.SECONDS.sleep(20);
        appServer.shutdown();
    }
}
