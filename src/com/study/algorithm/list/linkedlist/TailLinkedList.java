package com.study.algorithm.list.linkedlist;

public class TailLinkedList<E> {

    private Node last;
    private int size;

    public TailLinkedList() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            addLast(e);
        } else {
            Node suc = elementData(index + 1);
            suc.prev = new Node(e, suc.prev);
            size++;
        }
    }

    public E remove(int index) {
        checkIndexRange(index);
        if (index == size - 1) {
            return removeLast();
        } else {
            Node suc = elementData(index + 1);
            Node cur = suc.prev;
            suc.prev = cur.prev;
            size--;
            cur.prev = null;
            return cur.e;
        }
    }

    public boolean remove(Object obj) {
        Node suc = new Node(null, last);
        if (obj == null) {
            for (Node cur; (cur = suc.prev) != null; suc = suc.prev) {
                if (cur.e == null) {
                    suc.prev = cur.prev;
                    size--;
                    cur.prev = null;
                    return true;
                }
            }
        } else {
            for (Node cur; (cur = suc.prev) != null; suc = suc.prev) {
                if (obj.equals(cur.e)) {
                    suc.prev = cur.prev;
                    size--;
                    cur.prev = null;
                    return true;
                }
            }
        }
        return false;
    }

    private E removeLast() {
        Node res = last;
        last = res.prev;
        size--;
        res.prev = null;
        return res.e;
    }

    private void checkIndexRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node elementData(int index) {
        Node cur = last;
        for (int i = size - 1; i > index; i--) {
            cur = cur.prev;
        }
        return cur;
    }

    private void addLast(E e) {
        if (last == null) {
            last = new Node(e);
        } else {
            last = new Node(e, last);
        }
        size++;
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    private class Node {
        E e;
        Node prev;

        Node(E e, Node prev) {
            this.e = e;
            this.prev = prev;
        }

        Node(E e) {
            this.e = e;
        }
    }
}
