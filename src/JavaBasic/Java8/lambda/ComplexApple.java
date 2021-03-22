package JavaBasic.Java8.lambda;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/20 18:39
 *   @Description :
 *
 */
public class ComplexApple {

    private String color;
    private long weight;
    private String name;

    public ComplexApple(String color, long weight, String name) {
        this.color = color;
        this.weight = weight;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ComplexApple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }
}
