package Object_Orient_Design.Design_Pattern.Bridge;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 13:19
 *   @Description :
 *
 */
public class Computer2 {

    protected Brand brand;

    public Computer2(Brand b) {
        this.brand = b;
    }

    public void sale(){
        brand.sale();
    }

}

class Desktop2 extends Computer2 {

    public Desktop2(Brand b) {
        super(b);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售台式机");
    }
}

class Laptop2 extends Computer2 {

    public Laptop2(Brand b) {
        super(b);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售笔记本");
    }
}

