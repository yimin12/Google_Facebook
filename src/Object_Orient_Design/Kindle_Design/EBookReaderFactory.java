package Object_Orient_Design.Kindle_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 20:07
 *   @Description :
 *
 */
public class EBookReaderFactory {

    public EBookReader creatReader(Book book){
        if(book.getFormat() == Format.EPUB){
            return new EpubReader(book);
        } else if(book.getFormat() == Format.MOBI){
            return new MobiReader(book);
        } else if(book.getFormat() == Format.PDF){
            return new PdfReader(book);
        } else {
            return null;
        }
    }
}
