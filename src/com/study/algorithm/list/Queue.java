package com.study.algorithm.list;

public interface Queue<E> {

    boolean offer(E e);

    boolean add(E e);

    E poll();

    E remove();

    E peek();

    E element();

}
