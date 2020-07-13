package com.cas.wykt.java高级开发.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:19 2020-07-13
 * @version: V1.0
 * @review: 延迟队列，会对每个加入的数据进行时间计算，只有队列尾部时间到了才会被取出来，不然为空
 */
public class DelayQueueDemo {

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Message> queue = new DelayQueue<>();

        Message message1 = new Message("message - 00001", new Date(System.currentTimeMillis() + 5000L));
        Message message2 = new Message("message - 00002", new Date(System.currentTimeMillis() + 9000L));
        Message message3 = new Message("message - 00003", new Date(System.currentTimeMillis() + 7000L));
        queue.add(message1);
        queue.add(message2);
        queue.add(message3);
        /**
         * null
         * null
         * null
         * null
         * null
         * message - 00001
         * null
         * message - 00003
         * null
         * message - 00002
         * null
         */

        for(;;) {
            Message message = queue.poll();
            System.out.println(message == null ? "null" : message.content);
            Thread.sleep(1000L);
        }
    }

//    public static void main(String[] args) {
//        System.out.println(0 >>> 1);
//        System.out.println(1 >>> 1);
//        System.out.println(2 >>> 1);
//        System.out.println(3 >>> 1);
//        System.out.println(4 >>> 1);
//        System.out.println(5 >>> 1);
//        System.out.println(6 >>> 1);
//    }

    static class Message implements Delayed {
        @Override
        public long getDelay(TimeUnit unit) {
            long duration = sendTime.getTime() - System.currentTimeMillis();
            return TimeUnit.NANOSECONDS.convert(duration, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return o.getDelay(TimeUnit.NANOSECONDS) > this.getDelay(TimeUnit.NANOSECONDS) ? -1 : 1;
        }

        String content;
        Date sendTime;

        Message(String content, Date sendTime) {
            this.content = content;
            this.sendTime = sendTime;
        }


        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Date getSendTime() {
            return sendTime;
        }

        public void setSendTime(Date sendTime) {
            this.sendTime = sendTime;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
