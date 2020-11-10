package com.study.algorithm.tree;


import java.util.*;

public class Solution {

    public static TreeNode convertBiNode(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = convertBiNode(root.left);
        if (left == null) {
            left = root;
        } else {
            TreeNode cur = left;
            while (cur.right != null) {
                cur = cur.right;
            }
            cur.right = root;
            cur.left = null;
        }
        root.right = convertBiNode(root.right);
        root.left = null;
        return left;
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSymmetric(root.left, root.right);
    }

    private static boolean isSymmetric(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }

        return node1.val == node2.val && isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left);
    }

    public static boolean isSymmetricNoCur(TreeNode root) {
        if (root == null) {
            return true;
        }

        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        int index = 0;
        while (index < list.size()) {
            if (!isSymmetric(list, index)) {
                return false;
            }

            int n = list.size();
            for (int i = index; i < n; i++) {
                TreeNode head = list.get(i);
                if (head == null) {
                    continue;
                }

                list.add(head.left);
                list.add(head.right);
            }
            index = n;
        }
        return true;
    }

    private static boolean isSymmetric(List<TreeNode> list, int start) {
        int end = list.size() - 1;
        int middle = start + ((end - start) >> 1);
        for (int i = start; i <= middle; i++) {
            if (!isSymmetricNoCur(list.get(i), list.get(end + start - i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSymmetricNoCur(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }

        return node1.val == node2.val;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 2, 3, 4, 4, 3, 1, null, null, 1};
        TreeNode root = new TreeNode(arr);
        System.out.println(isSymmetricNoCur(root));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(Integer[] arr) {
            if (arr == null || arr.length == 0) {
                throw new IllegalArgumentException();
            }

            this.val = arr[0];
            List<TreeNode> list = new ArrayList<>();
            list.add(this);
            for (int i = 0; i < list.size(); i++) {
                TreeNode head = list.get(i);
                int p;
                if ((p = 2 * i + 1) < arr.length && arr[p] != null) {
                    head.left = new TreeNode(arr[p]);
                    list.add(head.left);
                }
                if ((p = 2 * i + 2) < arr.length && arr[p] != null) {
                    head.right = new TreeNode(arr[p]);
                    list.add(head.right);
                }
            }
        }
    }

}
