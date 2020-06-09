package com.study.algorithm.linkedlist;

/**
 * @author yjx
 * @date 2020/5/26 0026 22:49
 * @description
 */
public class LinkedStack<E> {

    private LinkedList<E> list;

    public LinkedStack() {
    }

    public E pop() {
        return list.removeFirst();
    }

    public void push(E e) {
        list.addFirst(e);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}
