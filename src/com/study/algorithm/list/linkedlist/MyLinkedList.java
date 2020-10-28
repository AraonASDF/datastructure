package com.study.algorithm.list.linkedlist;

public class MyLinkedList<E> {

    private Node first;
    private Node last;
    private int size;

    public MyLinkedList() {
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
            linkLast(e);
        } else {
            linkBefore(e, elementData(index));
        }
    }

    public boolean add(E e) {
        add(size, e);
        return true;
    }

    public E remove(int index) {
        checkIndexRange(index);
        if (index == size - 1) {
            return unlinkLast();
        } else {
            return unlinkBefore(elementData(index));
        }
    }

    public boolean remove(Object obj) {
        Node dummyHead = new Node(null, null, first);
        Node prev = dummyHead;
        if (obj == null) {
            for (Node cur; (cur = prev.next) != null; prev = prev.next) {
                if (cur.e == null) {
                    Node suc = cur.next;
                    prev.next = suc;
                    suc.prev = prev;
                    size--;
                    cur.next = null;
                    cur.prev = null;

                    dummyHead.next = null;
                    return true;
                }
            }
        } else {
            for (Node cur; (cur = prev.next) != null; prev = prev.next) {
                if (obj.equals(cur.e)) {
                    Node suc = cur.next;
                    prev.next = suc;
                    suc.prev = prev;
                    size--;
                    cur.next = null;
                    cur.prev = null;

                    dummyHead.next = null;
                    return true;
                }
            }
        }
        return false;
    }

    private E unlinkBefore(Node cur) {
        Node prev = cur.prev;
        if (prev == null) {
            first = first.next;
            size--;
            first.prev = null;
            cur.next = null;
            return cur.e;
        } else {
            Node suc = cur.next;
            prev.next = suc;
            suc.prev = prev;
            size--;
            cur.prev = null;
            cur.next = null;
            return cur.e;
        }
    }

    private E unlinkLast() {
        Node res = last;
        last = last.prev;
        size--;
        last.next = null;
        res.prev = null;
        return res.e;
    }

    private void checkIndexRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void linkBefore(E e, Node suc) {
        Node prev = suc.prev;
        if (prev == null) {
            first = new Node(null, e, first);
            suc.prev = first;
            size++;
        } else {
            Node node = new Node(prev, e, suc);
            prev.next = node;
            suc.prev = node;
            size++;
        }
    }

    private Node elementData(int index) {
        Node cur;
        if (index < (size >> 1)) {
            cur = first;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
        } else {
            cur = last;
            for (int i = size - 1; i > index; i--) {
                cur = cur.prev;
            }
        }
        return cur;
    }

    private void linkLast(E e) {
        if (last == null) {
            first = last = new Node(e);
        } else {
            Node l = last;
            last = new Node(last, e, null);
            l.next = last;
        }
        size++;
    }

    private class Node {
        Node prev;
        E e;
        Node next;

        Node(Node prev, E e, Node next) {
            this.prev = prev;
            this.e = e;
            this.next = next;
        }

        Node(E e) {
            this.e = e;
        }
    }

}
