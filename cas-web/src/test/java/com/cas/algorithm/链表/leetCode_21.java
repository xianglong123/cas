package com.cas.algorithm.链表;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:50 2020-06-16
 * @version: V1.0
 * @review: 合并两个有序链表
 */
public class leetCode_21 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 保存链表
        ListNode root = new ListNode(0);
        ListNode temp = root;

        // 当一个链表排序完了，另一个多余的直接粘贴就行了
        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                temp.next = l1;
                temp = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                temp = l2;
                l2 = l2.next;
            }
        }
        // 有一个为空，直接连接另一条
        if (l1 == null) {
            temp.next = l2;
        } else {
            temp.next = l1;
        }

        return root.next;
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode listNode = new ListNode(2);
        l1.next = listNode;
        listNode.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        ListNode listNode2 = new ListNode(3);
        l2.next = listNode2;
        listNode2.next = new ListNode(4);

        mergeTwoLists(l1, l2);
    }

}
