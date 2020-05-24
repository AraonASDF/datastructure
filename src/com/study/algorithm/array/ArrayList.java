package com.study.algorithm.array;

import java.util.Arrays;

/**
 * @author yjx
 * @date 2020/5/23
 * @description 动态数组
 */
public class ArrayList<E> {

    private Object[] data;

    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENT_DATA = {};

    public ArrayList(int capacity) {
        if (capacity > 0) {
            this.data = new Object[capacity];
        } else if (capacity == 0) {
            this.data = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("capacity error");
        }
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public boolean add(E e) {
        if (size == data.length) {
            resize(size + 1);
        }
        data[size++] = e;
        return true;
    }

    public boolean add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("range of index error");
        }
        if (size == data.length) {
            resize(size + 1);
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = e;
        size++;
        return true;
    }

    public void set(int index, E e) {
        ensureIndex(index);
        data[index] = e;
    }

    public E get(int index) {
        ensureIndex(index);
        return (E) data[index];
    }

    public E remove(int index) {
        ensureIndex(index);
        E res = (E) data[index];
        int len = size - index - 1;
        if (len > 0) {
            System.arraycopy(data, index + 1, data, index, len);
        }
        data[--size] = null;
        return res;
    }

    private void ensureIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("range of index error");
        }
    }

    private void resize(int minCapacity) {
        int size;
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            if (data == EMPTY_ELEMENT_DATA) {
                size = Math.max(DEFAULT_CAPACITY, minCapacity);
            } else {
                throw new OutOfMemoryError();
            }
        } else {
            size = newCapacity;
        }
        this.data = Arrays.copyOf(data, size);
    }

    public boolean contains(E e) {
        return indexOf(e) > 0;
    }

    public int indexOf(E e) {
        if (e == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (e.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }

}
