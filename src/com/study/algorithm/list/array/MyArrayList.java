package com.study.algorithm.list.array;

import java.util.Arrays;

public class MyArrayList<E> {

    private Object[] data;
    private int size;

    private static final Object[] EMPTY_CAPACITY_DATA = {};

    private static final Object[] DEFAULT_CAPACITY_DATA = {};

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity error:" + capacity);
        } else if (capacity == 0) {
            this.data = EMPTY_CAPACITY_DATA;
        } else {
            this.data = new Object[capacity];
        }
    }

    public MyArrayList() {
        this.data = DEFAULT_CAPACITY_DATA;
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

        if (size == data.length) {
            grow(size + 1);
        }
        if (size - index > 0) {
            System.arraycopy(data, index, data, index + 1, size - index);
        }
        this.data[index] = e;
        size++;
    }

    public boolean add(E e) {
        if (size == data.length) {
            grow(size + 1);
        }
        this.data[size++] = e;
        return true;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldValue = element(index);
        if (size - 1 - index > 0) {
            System.arraycopy(data, index + 1, data, index, size - 1 - index);
        }
        this.data[--size] = null;
        return oldValue;
    }

    public E remove() {
        return this.remove(size - 1);
    }

    public E get(int index) {
        return element(index);
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    private E element(int index) {
        return (E) this.data[index];
    }

    private void grow(int miniCapacity) {
        if (data == DEFAULT_CAPACITY_DATA) {
            miniCapacity = Math.max(miniCapacity, DEFAULT_CAPACITY);
        }
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + oldCapacity >> 1;
        if (newCapacity - miniCapacity < 0) {
            newCapacity = miniCapacity;
        }
        if (newCapacity < 0) {
            throw new OutOfMemoryError();
        }
        this.data = Arrays.copyOf(data, newCapacity);
    }

}
