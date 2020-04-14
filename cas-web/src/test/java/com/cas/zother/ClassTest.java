package com.cas.zother;

/**
 *
 * 总结：String不具有传递性，向下传递的不是常量池的值而是一个引用，
 * 如果这个引用被改变，那么只是在常量池中多一个常量，原来的引用不具有强关联性
 */
public class ClassTest {
    String str = null;
    char[] ch = {'a', 'b', 'c'};

    public void fun(String str, char ch[]) {
        str = "world";
        ch[0] = 'd';
    }

    public static void main(String[] args) {
        ClassTest test1 = new ClassTest();
        test1.fun(test1.str, test1.ch);
        System.out.print(test1.str + " and ");
        System.out.print(test1.ch);
    }
}