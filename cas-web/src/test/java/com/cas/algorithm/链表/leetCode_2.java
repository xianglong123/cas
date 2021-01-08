package com.cas.algorithm.链表;

import lombok.Data;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:36 2020-06-15
 * @version: V1.0
 * @review:
 */
public class leetCode_2 {

    @Data
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 保存结果的链表
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;// 进位数
        while(l1 != null || l2 != null || carry != 0) {
            // 加法链表
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            // 进位
            carry = sumVal / 10;

            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        return root.next;
    }


    public static void main(String[] args) {
        ListNode root1 = new ListNode(2);
        ListNode next1 = new ListNode(4);
        root1.setNext(next1);
        next1.setNext(new ListNode(3));

        ListNode root2 = new ListNode(5);
        ListNode next2 = new ListNode(6);
        root2.setNext(next2);
        next2.setNext(new ListNode(4));

        addTwoNumbers(root1, root2);

    }


}
