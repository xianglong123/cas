package com.cas.zother;


/**
 * 构造器 初始化模块 静态模块 运行顺序
 * 结论：静态模块 早于 初始化模块 早于 构造器
 */
public class InitOrder {
    public static void main(String[] args) {
        new InitOrder();
    }

    public InitOrder() {
        new M();
    }

    {
        System.out.println("(2) learning_more 's instance block ");
    }

    static {
        System.out.println("(1) learning_more 's static block");
    }
}

class M extends N {
    M() {
        System.out.println("(8) M 's constructor body");
    }

    {
        System.out.println("(7) M's instance initialzation block");
    }

    static {
        System.out.println("(4) M's static initialzation block");
    }
}

class N {
    N() {
        System.out.println("(6) N's constructor body");
    }

    {
        System.out.println("(5)N's instance initialization block");
    }

    static {
        System.out.println("(3) N's static initialization block");
    }
}