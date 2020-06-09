package com.study.algorithm.tree;

/**
 * @author yjx
 * @date 2020/6/8 0008 19:48
 * @description
 */
public class BST<E extends Comparable<E>> {

    private Node root;
    private int size;

    public void add(E e) {
        this.root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (node.e.compareTo(e) > 0) {
            node.left = add(node.left, e);
        } else if (node.e.compareTo(e) < 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    public void addNoCur(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
        }

        Node prev = null;
        Node cur = root;
        while (cur != null) {
            if (cur.e.compareTo(e) > 0) {
                prev = cur;
                cur = cur.left;
            } else if (cur.e.compareTo(e) < 0) {
                prev = cur;
                cur = cur.right;
            } else {
                return;
            }
        }

        if (prev.e.compareTo(e) > 0) {
            prev.left = new Node(e);
        } else {
            prev.right = new Node(e);
        }
        size++;
    }

    public boolean remove(E e) {
        if (root == null) {

        }

        return false;
    }

    public Node removeMax() {
        if (root == null) {
            return null;
        }
        return removeMax(root);
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            node.e = null;
            size--;
            return left;
        }

        node.right = removeMax(node.right);
        return node;
    }

    public E removeNoCur() {
        if (root == null) {
            return null;
        }

        Node dummyHead = new Node(null);
        dummyHead.right = root;

        Node prev = dummyHead;
        Node cur = root;

        while (cur.right != null) {
            prev = cur;
            cur = cur.right;
        }

        prev.right = cur.left;
        size--;
        E res = cur.e;
        cur.left = null;
        cur.e = null;
        dummyHead.right = null;
        return res;
    }

    public E maximum() {
        if (root == null) {
            return null;
        }

        return maximum(root).e;
    }

    public E maximumNoCur() {
        if (root == null) {
            return null;
        }

        Node cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.e;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private class Node {
        E e;
        Node left;
        Node right;

        Node(E e) {
            this.e = e;
        }
    }
}
