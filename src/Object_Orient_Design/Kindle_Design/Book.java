package Object_Orient_Design.Kindle_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 19:58
 *   @Description :
 *
 */
public class Book {
    private Format format;
    public Book(Format format){
        this.format = format;
    }
    public Format getFormat(){
        return format;
    }
}
