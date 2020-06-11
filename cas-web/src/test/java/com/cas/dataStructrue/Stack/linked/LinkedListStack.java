package com.cas.dataStructrue.Stack.linked;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 12:38 2020-06-11
 * @version: V1.0
 * @review:
 */
public class LinkedListStack {
    private LinkedList<String> ll = new LinkedList<>();

    public void push(String t) {
        ll.insertFirst(t);
    }

    public String pop() throws Exception{
        return ll.deleteFirst();
    }

    public void display() {
        ll.display();
    }

    public static void main(String[] args) throws Exception{
        LinkedListStack lls = new LinkedListStack();
        lls.push("a");
        lls.push("b");
        lls.push("c");
        lls.display();
        System.out.println(lls.pop());
        lls.display();

    }


}
