package com.study.algorithm.list.linkedlist;

public class HeadLinkedList<E> {

    private Node first;

    private int size;

    public HeadLinkedList() {
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(e);
        } else {
            Node prev = elementData(index - 1);
            prev.next = new Node(e, prev.next);
            size++;
        }
    }

    public boolean add(E e) {
        add(size, e);
        return true;
    }

    public E get(int index) {
        checkIndexRange(index);
        return elementData(index).e;
    }

    public E set(int index, E e) {
        checkIndexRange(index);
        Node cur = elementData(index);
        E oldValue = cur.e;
        cur.e = e;
        return oldValue;
    }

    public E remove(int index) {
        checkIndexRange(index);

        if (index == 0) {
            Node res = first;
            first = res.next;
            size--;
            res.next = null;
            return res.e;
        } else {
            Node prev = elementData(index - 1);
            Node cur = prev.next;
            prev.next = cur.next;
            size--;
            cur.next = null;
            return cur.e;
        }
    }

    public boolean remove(Object obj) {
        if (obj == null) {
            Node dummyHead = new Node(null, first);
            Node prev = dummyHead;
            Node cur;
            while ((cur = prev.next) != null) {
                if (cur.e == null) {
                    prev.next = cur.next;
                    size--;
                    cur.next = null;
                    dummyHead.next = null;
                    return true;
                }
                prev = prev.next;
            }
        } else {
            Node dummyHead = new Node(null, first);
            Node prev = dummyHead;
            Node cur;
            while ((cur = prev.next) != null) {
                if (obj.equals(cur.e)) {
                    prev.next = cur.next;
                    size--;
                    cur.next = null;
                    dummyHead.next = null;
                    return true;
                }
                prev = prev.next;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        Node cur = first;
        if (obj == null) {
            for (int i = 0; i < size; i++) {
                if (cur.e == null) {
                    return i;
                }
                cur = cur.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (obj.equals(cur.e)) {
                    return i;
                }
                cur = cur.next;
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    private void checkIndexRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void addFirst(E e) {
        if (first == null) {
            first = new Node(e);
        } else {
            first = new Node(e, first);
        }
        size++;
    }

    private Node elementData(int index) {
        Node cur = first;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    private class Node {
        E e;
        Node next;

        Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        Node(E e) {
            this.e = e;
        }
    }
}
