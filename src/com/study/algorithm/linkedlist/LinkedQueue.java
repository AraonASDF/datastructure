package com.study.algorithm.linkedlist;

/**
 * @author yjx
 * @date 2020/5/26 0026 22:55
 * @description
 */
public class LinkedQueue<E> {

    private LinkedList<E> list;

    public void add(E e) {
        list.addFirst(e);
    }

    public E poll() {
        if (list.isEmpty()) {
            return null;
        }
        return list.remove(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}
