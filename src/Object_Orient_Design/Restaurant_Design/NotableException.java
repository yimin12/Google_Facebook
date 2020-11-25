package Object_Orient_Design.Restaurant_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/13 20:17
 *   @Description :
 *
 */
public class NotableException extends Exception{
    public NotableException(Party party){
        super("No table available for party size: " + party.getSize());
    }
}
