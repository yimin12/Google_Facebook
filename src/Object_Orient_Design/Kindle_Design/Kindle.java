package Object_Orient_Design.Kindle_Design;

import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 19:57
 *   @Description :
 *
 */
public class Kindle {

    private List<Book> books;
    private EBookReaderFactory readerFactory;

    public Kindle(List<Book> books) {
        this.books = books;
        this.readerFactory = new EBookReaderFactory();
    }

    public String readBook(Book book) throws Exception{
        EBookReader reader = readerFactory.creatReader(book);
        if(reader == null){
            throw new Exception("Can't read this format");
        }
        return reader.displayReaderType() + ", book content is : " + reader.readBook();
    }

    public void downloadBook(Book book){
        books.add(book);
    }

    public void uploadBook(Book book){
        books.add(book);
    }

    public void deleteBook(Book book){
        books.remove(book);
    }
}

