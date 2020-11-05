package com.study.algorithm.list.array;

import com.study.algorithm.list.Deque;

public class MyArrayDeque<E> implements Deque<E> {

    private Object[] data;

    private int head;

    private int tail;

    private static final int MIN_INITIAL_CAPACITY = 8;

    public MyArrayDeque(int capacity) {
        this.data = new Object[calculateSize(capacity)];
    }

    private static int calculateSize(int numElements) {
        int initialCapacity = MIN_INITIAL_CAPACITY;
        //计算大于numElements的最小的2的整数次方，同HashMap
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>> 1);
            initialCapacity |= (initialCapacity >>> 2);
            initialCapacity |= (initialCapacity >>> 4);
            initialCapacity |= (initialCapacity >>> 8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            //超过了int类型的最大值变成负数了
            if (initialCapacity < 0)   // Too many elements, must back off
                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        }
        return initialCapacity;
    }

    @Override
    public boolean offerFirst(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        data[(head = (head - 1) & (data.length - 1))] = e;
        if (head == tail) {
            doubleCapacity();
        }
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        data[tail] = e;
        if ((tail = (tail + 1) & (data.length - 1)) == head) {
            doubleCapacity();
        }
        return true;
    }

    private void doubleCapacity() {
        assert head == tail;
        int p = head;
        int n = data.length;
        int r = n - p;
        int newCapacity = n << 1;
        if (newCapacity < 0) {
            throw new IllegalStateException();
        }
        Object[] newData = new Object[newCapacity];
        System.arraycopy(data, p, newData, 0, r);
        System.arraycopy(data, 0, newData, r, p);
        data = newData;
        head = 0;
        tail = n;
    }

    @Override
    public E pollFirst() {
        E res = elementData(head);
        if (res == null) {
            return null;
        }
        data[head] = null;
        head = (head + 1) & (data.length - 1);
        return res;
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) data[index];
    }

    @Override
    public E pollLast() {
        int t = (tail - 1) & (data.length - 1);
        E res = elementData(t);
        if (res == null) {
            return null;
        }
        data[t] = null;
        tail = t;
        return res;
    }

    @Override
    public E peekFirst() {
        return elementData(head);
    }

    @Override
    public E peekLast() {
        return elementData((tail - 1) & (data.length - 1));
    }
}
