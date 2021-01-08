package com.cas.algorithm.快慢指针;

import lombok.Data;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 15:06 2020-06-19
 * @version: V1.0
 * @review: 链表的中间结点
 */
public class leetCode_876 {

    @Data
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {
            if(fast.next == null) {
                return slow;
            }
            slow = slow.next;
            fast = fast.next.next;

        }

        return slow;


    }


    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        root.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);

        System.out.println(middleNode(root));


    }
}
