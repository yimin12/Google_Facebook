package Object_Orient_Design.Kindle_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 20:01
 *   @Description :
 *
 */
public enum Format {

    EPUB("epub"), PDF("pdf"), MOBI("mobi");
    private String content;
    Format(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
}
