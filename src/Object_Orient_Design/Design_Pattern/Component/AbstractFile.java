package Object_Orient_Design.Design_Pattern.Component;

import java.util.ArrayList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 15:05
 *   @Description :
 *
 */
public interface AbstractFile {
    void killVirus();
}

class ImageFile implements AbstractFile{

    private String name;

    public ImageFile(String name){
        super();
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("Image file is killing file");
    }

}

class TextFile implements AbstractFile{

    private String name;

    public TextFile(String name){
        super();
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("TextFile is killing file");
    }

}

class VideoFile implements AbstractFile{

    private String name;

    public VideoFile(String name){
        super();
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("VideoFile is killing file");
    }

}

class Folder implements AbstractFile{

    private String name;
    private List<AbstractFile> list = new ArrayList<>();

    public Folder(String name){
        super();
        this.name = name;
    }

    public void add(AbstractFile file){
        list.add(file);
    }

    public void remove(AbstractFile file){
        list.remove(file);
    }

    public AbstractFile getChild(int index){
        return list.get(index);
    }

    @Override
    public void killVirus() {
        System.out.println("File " + name + " is killing virus");

        for(AbstractFile file : list){
            file.killVirus();
        }
    }
}

