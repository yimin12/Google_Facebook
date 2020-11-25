package MultiThreading.BalkingPattern;

import javax.print.Doc;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 17:07
 *   @Description :
 *      modifying the document
 */
public class Document {

    // if the document is modified, it wil be marked as true
    private boolean changed;

    // Context you need to save in a time
    private List<String> content = new ArrayList<>();
    private final FileWriter fileWriter;
    private static AutoSaveThread autoSaveThread;

    public Document(String documentPath, String documentName) throws IOException{
        this.fileWriter = new FileWriter(new File(documentPath, documentName));
    }

    public static Document create(String documentPath, String documentName) throws IOException{
        Document document = new Document(documentPath, documentName);
        autoSaveThread = new AutoSaveThread(document);
        autoSaveThread.start();
        return document;
    }

    // update, add or remove
    public void edit(String content){
        synchronized (this){
            this.content.add(content);
            this.changed = true;
        }
    }

    public void save() throws IOException{
        synchronized (this){
            if(!changed){
                return;
            }
            for(String cache:content){
                this.fileWriter.write(cache);
                this.fileWriter.write("\r\n");
            }
            this.fileWriter.flush();
            System.out.println(Thread.currentThread().getName() + " 保存成功，保存內容為: " + this.content);
            this.changed = false;
            this.content.clear();
        }
    }

    public void close() throws IOException{
        autoSaveThread.interrupt();
        fileWriter.close();
    }
}
