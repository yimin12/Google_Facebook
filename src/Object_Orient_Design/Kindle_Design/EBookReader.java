package Object_Orient_Design.Kindle_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 20:06
 *   @Description :
 *
 */
public abstract class EBookReader {

    protected Book book;
    public EBookReader(Book book){
        this.book = book;
    }
    public abstract String readBook();
    public abstract String displayReaderType();
}
