package com.cas.dataStructrue.queue.doubleSideQueue;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 17:18 2020-06-11
 * @version: V1.0
 * @review: 双端链实现队列
 * 和数组相比，链表的优势在于长度不受限制，并且在进行插入和删除操作时，不需要移动数据项，故尽管某些操作的时间复杂度与数组想同，实际效率上还是比数组要高很多
 * 劣势在于随机访问，无法像数组那样直接通过下标找到特定的数据项
 */
public class FirstLastQueue {

    private class Node {
        private Object obj;
        private Node next;

        Node(Object obj) {
            this.obj = obj;
        }
    }

    private Node first = null;
    private Node last = null;

    public void insertLast(Object obj) {
        Node node = new Node(obj);
        if(first == null)
            first = node;
        else last.next = node; // 这里last是引用地址，修改引用地址的next会影响到first
        last = node;
    }

    public Object deleteFirst() throws Exception {
        if(first == null)
            throw new Exception("empty");
        Node temp = first;
        if(first.next == null)
            last = null;
        first = first.next;
        return temp.obj;
    }

    public void display() {
        if(first == null)
            System.out.println("empty");
        System.out.println("first -> last : |");
        Node cur = first;
        while(cur != null) {
            System.out.println(cur.obj.toString() + " | ");
            cur = cur.next;
        }
        System.out.print("\n");
    }


}
