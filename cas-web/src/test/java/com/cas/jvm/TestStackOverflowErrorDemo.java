package com.cas.jvm;

/**
 * @Copyright (C)
 * @Author: LI DONG PING
 * @Date: 2019-07-15 17:17
 * @Description: 栈内存溢出测试
 * <p>
 * 测试代码设计思路
 * 修改默认堆栈大小后,利用递归调用一个方法,达到栈深度过大的异常目的,同时在递归调用过程中记录调用此次,得出最大深度的数据
 * jvm参数
 * -Xss 180k:设置每个线程的堆栈大小(最小180k),默认是1M
 */
public class TestStackOverflowErrorDemo {
    //栈深度统计值
    private int stackLength = 1;

    /**
     * 递归方法,导致栈深度过大异常
     */
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    /**
     * 启动方法
     * 测试结果:当-Xss 512k为512k时,stackLength~=5606,随着-Xss参数变大时stackLength值随之变大
     * @param args
     */
    public static void main(String[] args) {
        TestStackOverflowErrorDemo demo = new TestStackOverflowErrorDemo();
        try {
            demo.stackLeak();
        } catch (Throwable e) {
            System.out.println("当前栈深度:stackLength=" + demo.stackLength);
            e.printStackTrace();
        }
    }
}
