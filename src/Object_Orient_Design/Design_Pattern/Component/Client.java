package Object_Orient_Design.Design_Pattern.Component;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 15:10
 *   @Description :
 *
 */
public class Client {

    public static void main(String[] args) {
        AbstractFile f2,f3,f4,f5;
        Folder f1 = new Folder("My Favourites");

        f2 = new ImageFile("MyImage.jpg");
        f3 = new TextFile("Hello.txt");
        f1.add(f2);
        f1.add(f3);

        Folder f11 = new Folder("电影");
        f4 = new VideoFile("NBA.avi");
        f5 = new VideoFile("CBA.avi");
        f11.add(f4);
        f11.add(f5);
        f1.add(f11);


//		f2.killVirus();

        f1.killVirus();
    }
}
