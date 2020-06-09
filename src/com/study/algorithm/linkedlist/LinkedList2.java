package com.study.algorithm.linkedlist;

import com.study.algorithm.linkedlist.LinkedList.Node;

/**
 * @author yjx
 * @date 2020/5/24 0024 20:50
 * @description 头插法  虚拟头结点
 */
public class LinkedList2<E> {

    private final Node<E> dummyHead;
    private int size;

    public LinkedList2() {
        this.dummyHead = new Node<>(null);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index range error");
        }
        Node<E> prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node<>(e, prev.next);
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index range error");
        }
        Node<E> prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node<E> res = prev.next;
        prev.next = res.next;
        size--;
        E ret = res.value;
        res.value = null;
        res.next = null;
        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public boolean contains(E e) {
        Node<E> cur = dummyHead.next;
        if (e == null) {
            for (; cur != null; cur = cur.next) {
                if (cur.value == null) {
                    return true;
                }
            }
        } else {
            for (; cur != null; cur = cur.next) {
                if (e.equals(cur.value)) {
                    return true;
                }
            }
        }
        return false;
    }

}
