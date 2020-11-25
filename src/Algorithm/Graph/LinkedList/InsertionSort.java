package Algorithm.Graph.LinkedList;

import DataStructure.AlgoUtils.ListNode;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/2 23:23
 *   @Description :
 *      Given -1->5->2->4>-3->0 to -1->0->2>-3>-4>-5
 */
public class InsertionSort {

    public static void main(String[] args) {
        ListNode one = new ListNode(-1);
        ListNode two = new ListNode(5);
        ListNode three = new ListNode(2);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(3);
        ListNode six = new ListNode(0);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        ListNode res = sort(one);
        while(res != null){
            System.out.print(res.value + " ");
            res = res.next;
        }

    }

    public static ListNode sort(ListNode head){
        ListNode dummy = new ListNode(0);
        // 这个dummy的作用是，把head开头的链表一个个的插入到dummy开头的链表里
        // 所以这里不需要dummy.next = head;

        while (head != null) {
            ListNode node = dummy;
            while (node.next != null && node.next.value < head.value) {
                node = node.next;
            }
            ListNode temp = head.next;
            head.next = node.next;
            node.next = head;
            head = temp;
        }

        return dummy.next;
    }

}
