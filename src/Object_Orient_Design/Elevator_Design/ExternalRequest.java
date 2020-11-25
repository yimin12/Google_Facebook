package Object_Orient_Design.Elevator_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/13 22:15
 *   @Description :
 *
 */
public class ExternalRequest extends Request {

    private Direction direction;

    public ExternalRequest(int level, Direction direction) {
        super(level);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
