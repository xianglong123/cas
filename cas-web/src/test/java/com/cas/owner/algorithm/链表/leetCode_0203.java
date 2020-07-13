package com.cas.owner.algorithm.链表;

import lombok.Data;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:53 2020-06-19
 * @version: V1.0
 * @review: 删除中间节点
 */
public class leetCode_0203 {

    @Data
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode next = new ListNode(2);
        root.setNext(next);
        next.setNext(new ListNode(3));

        deleteNode(next);
    }
}
