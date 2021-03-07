package Object_Orient_Design.Design_Pattern.Memento;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 20:52
 *   @Description :
 *
 */
public class Emp {

    private String ename;
    private int age;
    private double salary;

    // Add the data to the memory
    public EmpMemento memento(){
        return new EmpMemento(this);
    }

    // recovery the data from memento
    public void recovery(EmpMemento memento){
        this.ename = memento.getEname();
        this.age = memento.getAge();
        this.salary = memento.getSalary();
    }

    public Emp(String ename, int age, double salary){
        super();
        this.ename = ename;
        this.age = age;
        this.salary = salary;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
