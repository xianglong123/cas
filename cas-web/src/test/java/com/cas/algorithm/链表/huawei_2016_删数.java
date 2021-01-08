package com.cas.algorithm.链表;

import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 17:14 2020-06-22
 * @version: V1.0
 * @review: 有一个数组a[N]顺序存放0~N-1，要求每隔两个数删掉一个数，到末尾时循环至开头继续进行，求最后一个被删掉的数的原始下标位置。以8个数(N=7)为例:｛0，1，2，3，4，5，6，7｝，0->1->2(删除)->3->4->5(删除)->6->7->0(删除),如此循环直到最后一个数被删除。
 */
public class huawei_2016_删数 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int n = Integer.valueOf(in.next());

            if (n > 1000) {
                n = 999;
            }

            ListNode root = new ListNode(0);
            ListNode temp = root;

            // 典型链表模型
            for (int i = 1; i <= n - 1; i++) {
                if (i == n - 1) {
                    ListNode next = new ListNode(i);
                    temp.next = next;
                    temp = next;
                    // 循环链表
                    temp.next = root;
                } else {
                    ListNode next = new ListNode(i);
                    temp.next = next;
                    temp = next;
                }
            }
            // 需要删除的结点
            ListNode del = root;
            int flag = 0;
            // 走删除逻辑
            while (del.val != del.next.val) {
                if (flag == 2) {
                    del.val = del.next.val;
                    del.next = del.next.next;
                    flag = 0;
                } else {
                    del = del.next;
                    flag++;
                }
            }
            System.out.println(del.val);
        }
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

}
