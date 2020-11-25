package Algorithm.TopK;

import DataStructure.AlgoUtils.ListNode;

import java.util.List;
import java.util.PriorityQueue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/4 21:43
 *   @Description :
 *      Merge k sorted linked lists and return it as one sorted list.
 *
Example 1:
	Input:   [2->4->null,null,-1->null]
	Output:  -1->2->4->null

Example 2:
	Input: [2->6->null,5->null,7->null]
	Output:  2->5->6->7->null

 */
public class MergeKSortedList {

    // Ignore the brute force, sort two list in a time, total cost O(n^2) time
    // Divide And Conquer or merge it two by two -> O(nlogn)
    // Method 1: Divide and conquer
    public ListNode mergeKLists(List<ListNode> lists){
        if(lists.size() == 0){
            return null;
        }
        return mergeKLists(lists, 0, lists.size() - 1);
    }
    private ListNode mergeKLists(List<ListNode> lists, int left, int right){
        if(left == right){
            return lists.get(left);
        }
        // Divide
        int mid = left + (left + right) >> 2;
        ListNode leftPart = mergeKLists(lists, left, mid);
        ListNode rightPart = mergeKLists(lists, mid +1, right);
        // Conquer
        return mergeTwoList(leftPart, rightPart);
    }
    private ListNode mergeTwoList(ListNode left, ListNode right){
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while(left != null && right != null){
            if(left.value < right.value){
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right.next;
            }
        }
        if(left.next != null){
            tail.next = left;
        }
        if(right.next != null){
            tail.next = right;
        }
        return dummy.next;
    }

    // Use heap to sort k value together in a time, Time:O(nklogk)
    public ListNode mergeKListsI(List<ListNode> lists){
        if(lists == null || lists.size() == 0){
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(),(a, b)->a.value - b.value);
        for(int i = 0; i < lists.size(); i++){
            if(lists.get(i) != null){
                heap.offer(lists.get(i));
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while(!heap.isEmpty()){
            ListNode cur = heap.poll();
            head.next= cur;
            head = cur;
            if(cur.next != null){
                heap.offer(cur.next);
            }
        }
        return dummy.next;
    }
}
