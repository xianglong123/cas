package com.cas.dataStructrue.Stack.linked;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 12:26 2020-06-11
 * @version: V1.0
 * @review: 栈单链表实现：没有长度限制，并且出栈和入栈速度都很快
 */
public class LinkedList<T> {

    private class Node{
        private T t;
        private Node next;

        Node(T t) {
            this.t = t;
        }
    }

    private Node head = null;

    public void insertFirst(T t) {
        Node node = new Node(t);
        node.next = head;
        head = node;
    }

    public T deleteFirst() throws Exception {
        if(head == null)
            throw new Exception("empty!!");
        Node node = head;
        head = head.next;
        return node.t;
    }

    public void display() {
        if(head == null)
            System.out.println("empty!!!");
        System.out.println("top -> bottom : |");
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.t + " | ");
            cur = cur.next;
        }
        System.out.println();
    }




}
