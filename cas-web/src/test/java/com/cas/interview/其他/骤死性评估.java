package com.cas.interview.其他;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:24 2020-06-15
 * @version: V1.0
 * @review: 这个特性实际叫做“骤死性评估”,是一种语言特性，即左侧的表达式为假时整个表达式后续将不再进行评估。
 * 这也就是为什么不要重载&&,||,和逗号操作符的原因，因为重载之后变为函数语义，编译器将不再保证骤死性评估。
 */
public class 骤死性评估 {

    public static void main(String[] args) {
        int a = -20;
        System.out.println("开始");
        boolean flag = a < 0 && test() > 0;

    }

    public static int test() {
        System.out.println("骤死性评估成功");
        return 20;
    }


}
