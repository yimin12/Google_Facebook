package MultiThreading.ProviderConsumerPattern;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 16:25
 *   @Description :
 *
 */
public class Data {

    private Integer id;
    private String name;

    public Data(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
