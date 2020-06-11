package com.cas.dataStructrue.Stack;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 23:35 2020-06-10
 * @version: V1.0
 * @review: 栈数组实现1：优点：入栈和出栈速度快，缺点：长度有限
 */
public class Stack {
    private int top = -1;
    private Object[] objs;

    public Stack(int capacity) throws Exception {
        if(capacity < 0)
            throw new Exception("Illegal capacity:" + capacity);
        objs = new Object[capacity];
    }

    public void push(Object obj) throws Exception {
        if (top == objs.length - 1)
            throw new Exception("Stack is full!");
        objs[++top] = obj;
    }

    public Object pop() throws Exception {
        if (top == -1)
            throw new Exception("Stack is empty!");
        return objs[top--];
    }

    public void display() {
        for (int i = 0; i <= top; i++) {
            System.out.println(objs[i] + " | ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) throws Exception{
        Stack s = new Stack(5);
        s.push(1);
        s.push(2);
        s.display();
        System.out.println(s.pop());
        s.display();
        s.push(99);
        s.display();
        s.push(99);
    }

}
