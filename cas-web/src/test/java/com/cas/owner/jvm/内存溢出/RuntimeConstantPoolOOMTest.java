package com.cas.owner.jvm.内存溢出;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 21:18 2020-03-21
 * @version: V1.0
 * @review: VM Args: -XX:PermSize=6M -XX:MaxPermSize=6M  测试运行时常量池在哪个区域
 *
 * 结论：
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=6M; support was removed in 8.0
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=6M; support was removed in 8.0
 *
 * 设置永久代的参数已经被移除
 */
public class RuntimeConstantPoolOOMTest {

    public static void main(String[] args) {
        // 使用Set保持着常量池的引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<>();
        // 在short范围内足以让6MB的PermSize产生OOM
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
