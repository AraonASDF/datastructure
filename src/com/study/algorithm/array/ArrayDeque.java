package com.study.algorithm.array;

/**
 * @author yjx
 * @date 2020/5/24 0024 17:43
 * @description 双向动态数组队列
 */
public class ArrayDeque<E> {

    private Object[] data;
    private int size;
    private int head, tail;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayDeque(int capacity) {
        this.data = new Object[capacity];
    }

    public ArrayDeque() {
        this(DEFAULT_CAPACITY);
    }

    public void offer(E e) {
        if (size == data.length) {
            resize(size + 1);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    public E poll() {
        if (size == 0) {
            return null;
        }
        E res = (E) data[head];
        head = (head + 1) % data.length;
        size--;
        return res;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int minCapacity) {
        Object[] temp = new Object[newCapacity(minCapacity)];
        for (int i = 0; i < size; i++) {
            temp[i] = data[(i + head) % data.length];
        }

        this.data = temp;
        head = 0;
        tail = size;
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return newCapacity;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, data.length));
        res.append("front [");
        for (int i = 0; i < size; i++) {
            res.append(data[(head + i) % data.length]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            queue.offer(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.poll();
                System.out.println(queue);
            }
        }
    }


}
