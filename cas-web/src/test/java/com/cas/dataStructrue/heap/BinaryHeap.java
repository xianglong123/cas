package com.cas.dataStructrue.heap;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 19:48 2020-06-12
 * @version: V1.0
 * @review: 堆的实现
 */
public class BinaryHeap {

    private static final int DEFAULT_CAPACITY = 100;

    private int currentSize;
    private Comparable[] array;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = new Comparable[capacity + 1];
    }

    public BinaryHeap(Comparable[] items) {
        currentSize = items.length;
        array = new Comparable[(currentSize + 2) * 11 / 10];
        int i = 1;
        for (Comparable item : items) {
            array[i++] = item;
        }
        buildHeap();
    }

    public void insert(Comparable x) {
        int hole = ++currentSize;
        for (; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2)
            array[hole] = array[hole / 2];
        array[hole] = x;
    }

    public Comparable findMin() {
        if (isEmpty())
            return null;
        return array[1];
    }

    public Comparable deleteMin() {
        if(isEmpty())
            return null;
        Comparable minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--)
            percolateDown(1);
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == array.length - 1;
    }

    public void makeEmpty() {
        currentSize = 0;
    }

    private void percolateDown(int hole) {
        int child;
        Comparable tmp = array[hole];
        for(; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && array[child + 1].compareTo(array[child]) < 0)
                child++;//child指向右子树
            if (array[child].compareTo(tmp) < 0)//如果孩子比tmp小
                array[hole] = array[child];//交换值
            else
                break;
        }
        array[hole] = tmp;
    }


    public static void main(String[] args) {
        int numItems = 10;
        BinaryHeap h = new BinaryHeap(numItems);
        int i = 37;

        try {
            for (i = 37; i != 0; i = (i + 37) % numItems){
                h.insert(i);
            }

            for (int j=1;j<=h.currentSize;j++){
                System.out.print(h.array[j]+" ");
            }

            System.out.println("\n"+h.findMin());
            h.deleteMin();
            for (int j=1;j<=h.currentSize;j++){
                System.out.print(h.array[j]+" ");
            }
        } catch (Exception e) {
            System.out.println("Overflow (expected)! " + i);
        }
    }

}
