package com.study.algorithm.list.array;

import com.study.algorithm.list.Stack;

public class ArrayListStack<E> implements Stack<E> {

    private MyArrayList<E> arrayList;

    public ArrayListStack(int capacity) {
        this.arrayList = new MyArrayList<>(capacity);
    }

    public ArrayListStack() {
        this.arrayList = new MyArrayList<>();
    }

    @Override
    public boolean push(E e) {
        return arrayList.add(e);
    }

    @Override
    public E pop() {
        return arrayList.remove();
    }

    @Override
    public E peek() {
        return arrayList.get(arrayList.size() - 1);
    }
}
