package com.cas.owner.algorithm.线程;

import lombok.Data;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:54 2020-07-08
 * @version: V1.0
 * @review: 异步3个线程A,B,C 依次输出1，2，3，4，5，6
 */
public class ali_Thread {

    public static void main(String[] args) {
        Param param = new Param();
        new Thread(new Letter(param, "1", 0), "A").start();
        new Thread(new Letter(param, "2", 1), "B").start();
        new Thread(new Letter(param, "3", 2), "C").start();
    }

    @Data
    static class Letter implements Runnable {

        private Param param;
        private String name;
        private int process;

        Letter(Param param, String name, int process) {
            this.param = param;
            this.name = name;
            this.process = process;
        }

        @Override
        public void run() {
            synchronized (param) {
                for (int i = 0; i < 100; i ++) {
                    int state = param.getState();
                    while (state != process) {
                        try {
                            param.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        state = param.getState();
                    }
                    System.out.println("-----[{" + name + "}]------ 线程名-[{" + Thread.currentThread().getName() + "}]");
                    param.setState(++state % 3);
                    param.notifyAll();
                }
            }
        }
    }

    @Data
    static class Param {
        private int state = 0;
    }

}
