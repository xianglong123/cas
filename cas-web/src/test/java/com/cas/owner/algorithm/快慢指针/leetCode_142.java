package com.cas.owner.algorithm.快慢指针;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:37 2020-06-19
 * @version: V1.0
 * @review: 环形链表II
 */
public class leetCode_142 {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * hash表解决
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();

        while(head != null) {
            if(set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }
}
