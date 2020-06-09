package com.study.algorithm.array;

/**
 * @author yjx
 * @date 2020/5/25 0025 20:13
 * @description
 */
public class ArrayDeque2<E> {

    private Object[] data;
    private int head;
    private int tail;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayDeque2(int capacity) {
        data = new Object[capacity + 1];
    }

    public ArrayDeque2() {
        this(DEFAULT_CAPACITY);
    }

    public void add(E e) {
        data[tail] = e;
        tail = (tail + 1) % data.length;
        if (head == tail) {
            grow(size() + 1);
        }
    }

    public E poll() {
        if (head == tail) {
            return null;
        }
        E res = (E) data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        return res;
    }

    private void grow(int minCapacity) {
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }

        int p = head;
        int n = data.length;
        int r = n - p;
        Object[] temp = new Object[newCapacity];
        System.arraycopy(data, p, temp, 0, r);
        System.arraycopy(data, 0, temp, r, p);
        data = temp;
        head = 0;
        tail = n;
    }

    public int size() {
        return tail >= head ? tail - head : tail - head + data.length;
    }

    public boolean isEmpty() {
        return head == tail;
    }

}
