package com.study.algorithm.tree;

import java.util.Optional;

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

    public V putNoCur(K key, V val) {
        if (root == null) {
            root = new Node<>(key, val);
            return null;
        }

        Node<K, V> prev = root;
        Node<K, V> cur;
        do {
            int result = key.compareTo(prev.key);
            if (result < 0) {
                cur = prev.left;
                if (cur != null) {
                    prev = cur;
                }
            } else if (result > 0) {
                cur = prev.right;
                if (cur != null) {
                    prev = cur;
                }
            } else {
                V res = prev.val;
                prev.val = val;
                return res;
            }
        } while (cur != null);

        int result = key.compareTo(prev.key);
        if (result < 0) {
            prev.left = new Node<>(key, val);
        } else if (result > 0) {
            prev.right = new Node<>(key, val);
        }
        return null;
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

    public void remove(K key) {
        root = remove(root, key);
    }

    public V removeNoCur(K key) {
        if (root == null) {
            return null;
        }

        Node<K, V> prev = root;
        Node<K, V> cur;
        int result = key.compareTo(prev.key);
        if (result == 0) {
            if (root.left == null) {
                Node<K, V> res = root;
                root = root.right;
                size--;
                res.right = null;
                return res.val;
            }

            if (root.right == null) {
                Node<K, V> res = root;
                root = root.left;
                size--;
                res.left = null;
                return res.val;
            }

            Node<K, V> suc = removeMax(root.left);
            suc.left = root.left;
            root.left = null;
            suc.right = root.right;
            root.right = null;
            V res = root.val;
            root = suc;
            return res;
        } else if (result < 0) {
            cur = prev.left;
        } else {
            cur = prev.right;
        }

        Node<K, V> next;
        do {
            result = key.compareTo(cur.key);
            if (result < 0) {
                next = cur.left;
                if (next != null && next.key.compareTo(key) != 0) {
                    prev = cur;
                    cur = next;
                }
            } else if (result > 0) {
                next = cur.right;
                if (next != null && next.key.compareTo(key) != 0) {
                    prev = cur;
                    cur = next;
                }
            } else {
                return removeNode(key, prev, cur);
            }
        } while (next != null && next.key.compareTo(key) != 0);
        if (next == null) {
            return null;
        }
        return removeNode(key, cur, next);
    }

    private V removeNode(K key, Node<K, V> prev, Node<K, V> cur) {
        int result = key.compareTo(prev.key);
        if (cur.left == null) {
            Node<K, V> right = cur.right;
            cur.right = null;
            size--;
            if (result < 0) {
                prev.left = right;
            } else {
                prev.right = right;
            }
            return cur.val;
        }
        if (cur.right == null) {
            Node<K, V> left = cur.left;
            cur.left = null;
            size--;
            if (result < 0) {
                prev.left = left;
            } else {
                prev.right = left;
            }
            return cur.val;
        }
        Node<K, V> suc = removeMax(cur.left);
        suc.left = cur.left;
        suc.right = cur.right;
        if (result < 0) {
            prev.left = suc;
        } else {
            prev.right = suc;
        }
        cur.left = null;
        cur.right = null;
        return cur.val;
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        int result = key.compareTo(node.key);
        if (result < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (result > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            if (node.left == null) {
                Node<K, V> right = node.right;
                size--;
                node.right = null;
                return right;
            }

            if (node.right == null) {
                Node<K, V> left = node.left;
                size--;
                node.left = null;
                return left;
            }

            Node<K, V> res = removeMin(node.right);
            res.left = node.left;
            res.right = node.right;
            node.left = null;
            node.right = null;
            return res;
        }
    }

    public V get(K key) {
        return Optional.ofNullable(get(root, key)).map(node -> node.val).orElse(null);
    }

    private Node<K, V> get(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        int result = key.compareTo(node.key);
        if (result < 0) {
            return get(node.left, key);
        } else if (result > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
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
