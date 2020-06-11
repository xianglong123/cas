package com.cas.dataStructrue.LinearList;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 14:53 2020-06-10
 * @version: V1.0
 * @review: 顺序表，底层数组，保存地址连续
 */
public class SqList implements IList{
    private Object[] listElem; // 线性表存储空间

    private int curLen; // 当前长度

    public SqList(int maxSize) {
        curLen = 0;
        listElem = new Object[maxSize];
    }

    @Override
    public void clear() {
        curLen = 0;
    }

    @Override
    public boolean isEmpty() {
        return curLen == 0;
    }

    @Override
    public int length() {
        return curLen;
    }

    @Override
    public Object get(int i) throws Exception {
        if(i < 0 || i > curLen - 1)
            throw new Exception("元素不存在");
        return listElem[i];
    }

    @Override
    public void insert(int i, Object x) throws Exception {
        if(curLen == listElem.length) // 判断顺序表是否已满
            throw new Exception("顺序表已满");

        if(i < 0 || i > curLen) // i小于0或者大于表长
            throw new Exception("插入位置不合理");

        for(int j = curLen; j > i; j --) {
            listElem[j] = listElem[j - 1];
        }
        listElem[i] = x;
        curLen ++;
    }

    @Override
    public void add(Object x) {
        try {
            insert(curLen, x);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int i) throws Exception {
        if(i < 0 || i > curLen - 1) // i小于1或者大于表长-1
            throw new Exception("删除位置不合理");
        for(int j = i; j < curLen - 1; j ++) {
            listElem[j] = listElem[j + 1];
        }
        curLen --;
    }

    @Override
    public int indexOf(Object x) {
        int j = 0;
        while (j < curLen && !listElem[j].equals(x))
            j ++;
        if (j < curLen)
            return j;
        else
            return -1;
    }

    @Override
    public void display() {
        for (int j = 0; j < curLen; j++) {
            System.out.print(listElem[j] + " ");
        }
        System.out.println();
    }
}
