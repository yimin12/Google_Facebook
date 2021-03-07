package Object_Orient_Design.Design_Pattern.Component;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 15:02
 *   @Description :
 *
 */
public interface Component {

    void operation();
}

interface Leaf extends Component{

}

interface Composite extends Component{
    void add(Component c);
    void remove(Component c);
    Component getChild(int index);
}
