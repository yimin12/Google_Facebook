package MultiThreading.DesignPattern.TwoPhaseTerminationPattern;

import java.io.*;
import java.net.Socket;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 19:39
 *   @Description :
 *
 */
public class ClientHandler implements Runnable {

    private final Socket socket;
    private volatile boolean isRunning = true;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try(InputStream is = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            PrintWriter pw = new PrintWriter(out)) {
            while(isRunning){
                String message = br.readLine();
                if(null == message){
                    break;
                }
                System.out.println("收到客户端的消息为：" + message);
                pw.write("ehco message: " + message + "\r\n");
                pw.flush();
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            this.stop();
            System.out.println("客戶端二階段終止執行");
        }
    }

    public void stop(){
        if(!isRunning){
            return;
        }
        this.isRunning = false;
        try{
            this.socket.close();
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
        System.out.println("客戶端退出了");
    }
}

