package MultiThreading.DesignPattern.TwoPhaseTerminationPattern;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 19:37
 *   @Description :
 *
 */
public class AppServer extends Thread{

    private int port;
    private final static int DEFAULT_PORT = 12722;
    private volatile boolean isRunning = true;
    private ServerSocket server;
    private List<ClientHandler> clientHandlers = new ArrayList<>();
    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public AppServer(int port){
        this.port = port;
    }

    public AppServer(){
        this(DEFAULT_PORT);
    }

    @Override
    public void run() {
        System.out.println("服務端口已啟動，啟動端口為：" + this.port);
        try{
            server = new ServerSocket(port);
            while(isRunning){
                Socket client = this.server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                threadPool.submit(clientHandler);
                this.clientHandlers.add(clientHandler);
                System.out.println(client.getLocalAddress() + "客戶端已經成功連接服務器");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            this.dispose();
        }
    }

    // 二阶段处理的业务
    private void dispose(){
        clientHandlers.stream().forEach(ClientHandler::stop);
        this.threadPool.shutdown();
        System.out.println("服务端二阶段终止操作被执行。");
    }

    // 对外显示声明的关闭方法
    public void shutdown() throws IOException{
        if(!isRunning) return;
        this.isRunning = false;
        this.interrupt();
        this.server.close();
        System.out.println("服務器已經關閉");
    }

}
