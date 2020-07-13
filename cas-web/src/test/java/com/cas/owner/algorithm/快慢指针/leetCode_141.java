package com.cas.owner.algorithm.快慢指针;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 13:58 2020-06-19
 * @version: V1.0
 * @review: 环形链表
 */
public class leetCode_141 {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        do {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;

        } while (slow != fast);

        return true;
    }
}
