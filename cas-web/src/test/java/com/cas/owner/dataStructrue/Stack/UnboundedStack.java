package com.cas.owner.dataStructrue.Stack;

import java.util.Arrays;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 00:04 2020-06-11
 * @version: V1.0
 * @review: 优点：无长度限制  缺点：长度自动扩张，数据大的时候会慢
 */
public class UnboundedStack {
    private int top = -1;
    private Object[] objs;

    public UnboundedStack() throws Exception {
        this(10);
    }

    public UnboundedStack(int capacity) throws Exception {
        if(capacity < 0)
            throw new Exception("Illegal capacity:" + capacity);
        objs = new Object[capacity];
    }

    public void push(Object obj) {
        if(top == objs.length - 1) {
            this.enlarge();
        }
        objs[++top] = obj;
    }

    public Object pop() throws Exception {
        if(top == -1)
            throw new Exception("Stack is empty!");
        return objs[top--];
    }

    private void enlarge() {
        int num = objs.length/3;
        if(num == 0)
            num = 1;
        objs = Arrays.copyOf(objs, objs.length + num);
    }

    public void dispaly() {
        Arrays.asList(objs).forEach(System.out::print);
    }

    public static void main(String[] args) throws Exception{
        UnboundedStack us = new UnboundedStack(2);
        us.push(1);
        us.push(2);
        us.dispaly();
        System.out.println(us.pop());
        us.dispaly();
        us.push(99);
        us.dispaly();
        us.push(99);
        us.dispaly();
    }


}
