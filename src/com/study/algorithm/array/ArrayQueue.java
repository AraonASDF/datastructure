package com.study.algorithm.array;

/**
 * @author yjx
 * @date 2020/5/24
 * @description 单向动态数组队列
 */
public class ArrayQueue<E> {

    private ArrayList<E> arrayList;

    public ArrayQueue(int capacity) {
        arrayList = new ArrayList<>(capacity);
    }

    public ArrayQueue() {
        this(10);
    }

    public boolean offer(E e) {
        return arrayList.add(e);
    }

    public E poll() {
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList.remove(0);
    }

    public E peek() {
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList.get(0);
    }

}
