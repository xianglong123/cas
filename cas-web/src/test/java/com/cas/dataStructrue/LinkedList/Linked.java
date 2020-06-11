package com.cas.dataStructrue.LinkedList;

import lombok.Data;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 18:16 2020-06-10
 * @version: V1.0
 * @review: 链表结构实现
 */
@Data
public class Linked <T> {

    @Data
    private class Node{
        private T t;
        private Node next;
        public Node(T t, Node next) {
            this.t = t;
            this.next = next;
        }

        public Node(T t) {
            this(t, null);
        }
    }

    private Node head; // 头节点
    private int size; // 链表元素个数

    // 构造函数
    public Linked() {
        this.head = null;
        this.size =  0;
    }

    // 链表头部添加元素
    public void addFirst(T t) {
        Node node = new Node(t);
        node.next = this.head;
        this.head = node;
        this.size ++;
    }

    // 向链表尾部插入元素
    public void addLast(T t) {
        this.add(t, this.size);
    }

    // 向链表中间插入元素
    public void add(T t, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("超出插入范围");
        }
        if (index == 0) {
            this.addFirst(t);
            return;
        }
        Node preNode = this.head;
        // 找到要插入节点的前一个节点
        for (int i = 0; i < index - 1; i ++) {
            preNode = preNode.next;
        }

        Node node = new Node(t);
        // 要插入的节点的下一个节点指向preNode节点的下一个节点
        node.next = preNode.next;
        // preNode的下一个节点指向要插入节点node
        preNode.next = node;
        this.size++;
    }

    // 删除链表的第一个元素
    public void remove(T t) {
        if(head == null) {
            System.out.println("无元素可删除");
            return ;
        }
        // 要删除的元素与头节点的元素相同
        while (head != null &&  head.t.equals(t)) {
            head = head.next;
            this.size--;
        }

        /**
         * 上面已经对头节点判别是否进行删除
         * 所以要对头节点的下一个节点进行判别
         */
        Node cur = this.head;
        while (cur.next != null) {
            if (cur.next.t.equals(t)) {
                this.size--;
                cur.next = cur.next.next;
            }
            else cur = cur.next;
        }
    }

    // 删除链表第一个元素
    public T removeFirst() {
        if (this.head == null) {
            System.out.println("无元素可删除");
            return null;
        }
        Node delNode = this.head;
        this.head = this.head.next;
        delNode.next = null; // 置空被GC回收
        this.size--;
        return delNode.t;
    }

    // 删除链表的最后一个元素
    public T removeLast() {
        if (this.head == null) {
            System.out.println("无元素可删除");
            return null;
        }

        // 只有一个元素
        if (this.getSize() == 1) {
            return this.removeFirst();
        }

        Node cur = this.head;
        Node pre = this.head;
        while (cur.next != null) {
            pre = cur;
            cur = cur.next;
        }
        pre.next = cur.next;
        this.size--;
        return cur.t;
    }

    // 加入虚拟头节点的链表进行删除
    public void removeElt(T t) {
        // 构造虚拟头节点，并且下一个节点指向head
        Node dummy = new Node(t, this.head);
        // 声明结点指向虚拟头结点
        Node cur = dummy;
        // 从虚拟头结点的下一个结点开始遍历
        while(cur.next != null) {
            if (cur.next.t.equals(t)) {
                cur.next = cur.next.next;
                this.size--;
            } else cur = cur.next;
        }
        // 去除虚拟头结点
        this.head = dummy.next;
    }

    // 使用虚拟头结点进行链表的插入
    public void insert(T t, T des) {
        // 构造虚拟头结点，并且下一个结点指向head
        Node dummy = new Node(null, this.head);

        // 构造要插入的结点
        Node dNode = new Node(des);
        // 声明变量cur指向虚拟头结点
        Node cur = dummy;

        while (cur.next != null) {
            if (cur.next.t.equals(t)) {
                dNode.next = cur.next;
                cur.next = dNode;
                this.size ++;
                break;
            } else cur = cur.next;
        }

        this.head = dummy.next;
    }

    // 判断某个元素是否在链表的结点上
    public boolean contains(T t) {
        Node cur = this.head;
        while(cur != null) {
            if(cur.t.equals(t)) {
                return true;
            } else cur = cur.next;
        }
        return false;
    }



}
