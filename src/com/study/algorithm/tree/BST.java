package com.study.algorithm.tree;

public class BST<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node<K, V> put(Node<K, V> node, K key, V val) {
        if (node == null) {
            size++;
            return new Node<>(key, val);
        }

        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = put(node.left, key, val);
        } else if (compare > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val;
        }

        return node;
    }

    public Node<K, V> maximum() {
        if (root == null) {
            return null;
        }

        return maximum(root);
    }

    private Node<K, V> maximum(Node<K, V> node) {
        if (node.right == null) {
            return node;
        }

        return maximum(node.right);
    }

    private Node<K, V> minimum() {
        if (root == null) {
            return null;
        }

        return minimum(root);
    }

    private Node<K, V> minimum(Node<K, V> node) {
        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
    }

    public Node<K, V> removeMax() {
        Node<K, V> max = maximum();
        root = removeMax(root);
        return max;
    }

    private Node<K, V> removeMax(Node<K, V> node) {
        if (node.right == null) {
            Node<K, V> left = node.left;
            size--;
            node.left = null;
            return left;
        }

        node.right = removeMax(node.right);
        return node;
    }

    public Node<K, V> removeMin() {
        Node<K, V> min = minimum();
        root = removeMin(root);
        return min;
    }

    private Node<K, V> removeMin(Node<K, V> node) {
        if (node.left == null) {
            Node<K, V> right = node.right;
            node.right = null;
            size--;
            return right;
        }

        node.left = removeMin(node.left);
        return node;
    }

    private static class Node<K, V> {
        K key;
        V val;
        Node<K, V> left;
        Node<K, V> right;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        Node(K key, V val, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
