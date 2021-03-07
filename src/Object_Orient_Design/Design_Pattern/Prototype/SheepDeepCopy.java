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
public class SheepDeepCopy implements Cloneable, Serializable {

    private String sname;
    private Date birthday;

    // deep copy by using clone
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        SheepDeepCopy s = (SheepDeepCopy) obj;
        s.birthday = (Date) this.birthday.clone();
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


    public SheepDeepCopy(String sname, Date birthday) {
        super();
        this.sname = sname;
        this.birthday = birthday;
    }

    public SheepDeepCopy() {
    }
}
