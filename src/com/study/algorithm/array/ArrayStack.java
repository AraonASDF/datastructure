package com.study.algorithm.array;

/**
 * @author yjx
 * @date 2020/5/24
 * @description 动态数组栈
 */
public class ArrayStack<E> {

    private ArrayList<E> arrayList;

    public ArrayStack(int capacity) {
        arrayList = new ArrayList<>(capacity);
    }

    public ArrayStack() {
        this(10);
    }

    public void push(E e) {
        arrayList.add(e);
    }

    public E peek() {
        return arrayList.get(size() - 1);
    }

    public E pop() {
        return arrayList.remove(size() - 1);
    }

    public int size() {
        return arrayList.size();
    }

    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("stack[");
        for (int i = 0; i < size(); i++) {
            sb.append(arrayList.get(i));
            if (i != size() - 1) {
                sb.append(",");
            }
        }
        return sb.append("]").toString();
    }

}
