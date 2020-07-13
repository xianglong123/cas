package com.cas.owner.dataStructrue.queue;

import lombok.Data;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:39 2020-06-11
 * @version: V1.0
 * @review: 数组实现队列 队列长度有限，但是考虑到平时一般都有使用有届队列
 * 下面实现的入队和出队又毛病
 */
@Data
public class Queue {
    private Object[] objs;
    private int head;
    private int end;
    private int size;

    public Queue(int size) {
        objs = new Object[size];
        this.head = 0;
        this.end = -1;
        this.size = 0;
    }

    /**
     * 入队列
     * @param obj
     * @throws Exception
     */
    public void push(Object obj) throws Exception {
        if (this.size >= objs.length)
            throw new Exception("Queue is full");
        if (end == objs.length - 1)
            end = -1;
        objs[++end] = obj;
        size ++;
    }

    /**
     * 出队列
     * @return
     * @throws Exception
     */
    public Object pop() throws Exception {
        if(this.size == 0)
            throw new Exception("Queue is empty!");
        Object tmp = objs[head++];
        if(head == objs.length)
            head = 0;
        size --;
        return tmp;
    }

    public Object peek() throws Exception {
        if(this.size == 0)
            throw new Exception("Queue is empty!");
        return objs[head];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return (size ==0);
    }

    public boolean isFull() {
        return (size == objs.length);
    }

    public void display() {
        for (int i = 0; i <= size - 1; i++) {
            System.out.println(objs[i] + " | ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) throws Exception{
        Queue queue = new Queue(3);
        queue.push("a");
        queue.push("b");
        queue.display();
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.peek());
        queue.push("c");
        queue.push("d");
        queue.display();
    }

}
