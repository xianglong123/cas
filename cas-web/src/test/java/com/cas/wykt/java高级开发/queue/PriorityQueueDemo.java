package com.cas.wykt.java高级开发.queue;

import lombok.Data;

import java.util.PriorityQueue;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:54 2020-07-13
 * @version: V1.0
 * @review: 优先级队列，可以对数据进行排序
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue<Message> queue = new PriorityQueue<>((o1, o2) -> o1.order > o2.order ? -1 : 1);
        Message message1 = new Message(3, "1");
        Message message2 = new Message(1, "1");
        Message message3 = new Message(2, "1");

        queue.add(message1);
        queue.add(message2);
        queue.add(message3);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }


    @Data
    static
    class Message {
        Integer order;
        String name;

        Message(Integer order, String name) {
            this.order = order;
            this.name = name;
        }
    }

}
