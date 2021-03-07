package Object_Orient_Design.Design_Pattern.Prototype;

import java.io.Serializable;
import java.util.Date;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 17:19
 *   @Description :
 *
 */
public class Sheep implements Cloneable, Serializable {
    private String sname;
    private Date birthday;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();  //直接调用object对象的clone()方法！
        return obj;
    }


    public String getSname() {
        return sname;
    }


    public void setSname(String sname) {
        this.sname = sname;
    }


    public Date getBirthday() {
        return birthday;
    }


    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public Sheep(String sname, Date birthday) {
        super();
        this.sname = sname;
        this.birthday = birthday;
    }

    public Sheep() {
    }
}
