package MultiThreading.BalkingPattern;

import java.io.IOException;
import java.util.Scanner;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 17:34
 *   @Description :
 *
 */
public class DocumentEditThread extends Thread{

    private final String documentPath;
    private final String documentName;
    private final static Scanner SCANNER = new Scanner(System.in);

    public DocumentEditThread(String documentPath, String documentName){
        super("Yimin's Editing Thread");
        this.documentName = documentName;
        this.documentPath = documentPath;
    }

    @Override
    public void run() {
        // simulate user give 5 inputs
        int count = 0;
        try{
            Document document = Document.create(documentPath, documentName);
            while(true){
                String text = SCANNER.next();
                if("exit".equals(text)){
                    document.close();
                    break;
                }
                document.edit(text);
                // simulate user will save the file every 5 times input
                if(count == 5){
                    document.save();
                    count = 0;
                }
                count++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
