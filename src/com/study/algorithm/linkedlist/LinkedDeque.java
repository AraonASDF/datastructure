package com.study.algorithm.linkedlist;

import java.io.DataInputStream;

/**
 * @author yjx
 * @date 2020/5/26 0026 23:03
 * @description
 */
public class LinkedDeque<E> {

    private Node head;
    private Node tail;
    private int size;

    public void add(E e) {
        final Node l = tail;
        tail = new Node(e);
        if (l == null) {
            head = tail;
        } else {
            l.next = tail;
        }
        size++;
    }

    public E poll() {
        if (size == 0) {
            return null;
        }
        Node ret = head;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        E res = ret.value;
        ret.value = null;
        ret.next = null;
        return res;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        E value;
        Node next;

        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(E value) {
            this.value = value;
        }
    }
}
