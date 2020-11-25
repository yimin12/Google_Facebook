package Object_Orient_Design.Kindle_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 20:09
 *   @Description :
 *
 */
public class MobiReader extends EBookReader {

    public MobiReader(Book book) {
        super(book);
    }

    @Override
    public String readBook() {
        return book.getFormat().getContent();
    }

    @Override
    public String displayReaderType() {
        return "Using MOBI reader";
    }
    
}
