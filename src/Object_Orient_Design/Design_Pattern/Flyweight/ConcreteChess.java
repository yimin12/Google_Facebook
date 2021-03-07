package Object_Orient_Design.Design_Pattern.Flyweight;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 17:08
 *   @Description :
 *
 */
public class ConcreteChess implements ChessFlyWeight{

    private String color;

    public ConcreteChess(String color) {
        super();
        this.color = color;
    }

    @Override
    public void display(Coordinate c) {
        System.out.println("chess color: "+color);
        System.out.println("chess position: "+c.getX()+"----"+c.getY());
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String c) {
        this.color = c;
    }
}
