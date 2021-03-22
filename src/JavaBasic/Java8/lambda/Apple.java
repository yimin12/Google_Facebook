package JavaBasic.Java8.lambda;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/19 22:43
 *   @Description :
 *
 */
public class Apple {

    private String color;
    private long weight;

    public Apple(String color, long weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple() {

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
