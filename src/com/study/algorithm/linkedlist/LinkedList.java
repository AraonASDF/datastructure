package com.study.algorithm.linkedlist;

/**
 * @author yjx
 * @date 2020/5/24
 * @description 头插法  无虚拟头结点
 */
public class LinkedList<E> {

    private Node<E> head;
    private int size;

    public void addFirst(E e) {
        head = new Node<E>(e, head);
        size++;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index range error");
        }

        if (index == 0) {
            addFirst(e);
        } else {
            Node<E> prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }

            prev.next = new Node<E>(e, prev.next);
            size++;
        }
    }

    public E removeFirst() {
        if (head == null) {
            return null;
        }
        Node<E> res = head;
        head = head.next;
        size--;
        E ret = res.value;
        res.value = null;
        res.next = null;
        return ret;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index range error");
        }
        if (index == 0) {
            return removeFirst();
        } else {
            Node<E> prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            Node<E> res = prev.next;
            prev.next = res.next;
            size--;
            E ret = res.value;
            res.next = null;
            res.value = null;
            return ret;
        }
    }

    public int indexOf(E e) {
        Node<E> cur = head;
        if (e == null) {
            for (int i = 0; i < size; i++) {
                if (cur.value == null) {
                    return i;
                }
                cur = cur.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (e.equals(cur.value)) {
                    return i;
                }
                cur = cur.next;
            }
        }
        return -1;
    }

    public boolean contains(E e) {
        return indexOf(e) > 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    static class Node<E> {
        E value;
        Node<E> next;

        Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }

        public Node(E value) {
            this.value = value;
        }
    }

}
