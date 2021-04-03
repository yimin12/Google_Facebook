package Contest.Bloomberg;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/2 21:37
 *   @Description :
 *
 */
public class FlattenMultilevelDoubleLinkedList {

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };

    // iterative way to traverse
    public Node flatten(Node head) {
        if(head == null) return head;
        Node p = head;
        while(p != null){
            // Case 1: no child
            if(p.child == null){
                p = p.next;
                continue;
            }
            // Case 2 : get child, find thetail of the child
            Node temp = p.child;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = p.next;
            if(p.next != null) p.next.prev = temp;
            p.next = p.child;
            p.child.prev = p;
            p.child = null;
        }
        return head;
    }
}
