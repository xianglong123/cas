package com.cas.dataStructrue.LinearList;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 14:46 2020-06-10
 * @version: V1.0
 * @review:
 */
public interface IList {

    void clear(); // 判断一个已经存在的线性表置cheng空表

    boolean isEmpty(); // 判断当前线性表中的数据个数是否为0

    int length(); // 求线性表中的数据元素个数并由函数返回其值

    Object get(int i) throws Exception; // 读取到线性表中的第i个元素

    void add(Object x);

    void insert(int i, Object x) throws Exception;// 在线性表的第i个数据元素之前插入一个值为x的数据元素。其中i取值范围为：0≤i≤length()。如果i值不在此范围则抛出异常,当i=0时表示在表头插入一个数据元素x,当i=length()时表示在表尾插入一个数据元素x

    void remove(int i) throws Exception;// 将线性表中第i个数据元素删除。其中i取值范围为：0≤i≤length()- 1,如果i值不在此范围则抛出异常

    int indexOf(Object x); // 返回线性表中首次出现指定元素的索引，如果列表不包含此元素，则返回-1

    void display(); // 输出线性表中的数据元素

}
