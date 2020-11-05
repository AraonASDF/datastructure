package com.study.algorithm.tree;

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

    public static void main(String[] args) {
        Integer[] arr = {4, 2, 5, 1, 3, 6, 0};
        TreeNode root = new TreeNode(arr);
        root = convertBiNode(root);
        System.out.println(root);
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
            for (int i = 1; i < arr.length; i++) {
                put(this, arr[i]);
            }
        }

        private TreeNode put(TreeNode node, int val) {
            if (node == null) {
                return new TreeNode(val);
            }

            if (val < node.val) {
                node.left = put(node.left, val);
            } else if (val > node.val) {
                node.right = put(node.right, val);
            }
            return node;
        }
    }

}
