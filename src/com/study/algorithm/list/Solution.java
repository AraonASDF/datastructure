package com.study.algorithm.list;

import com.sun.source.tree.Tree;

import java.util.HashSet;
import java.util.List;

public class Solution {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public static ListNode mergeTwoListsNoCur(final ListNode l1, final ListNode l2) {
        ListNode list1 = l1;
        ListNode list2 = l2;

        ListNode dummyHead = new ListNode();
        ListNode prev = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }

        prev.next = list1 == null ? list2 : list1;
        ListNode head = dummyHead.next;
        dummyHead.next = null;
        return head;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(head.val);
        ListNode prev = head;
        ListNode cur;
        while ((cur = prev.next) != null) {
            if (!hashSet.add(cur.val)) {
                prev.next = cur.next;
                cur.next = null;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    public static ListNode deleteNode(ListNode head, int val) {
        while (head != null && head.val == val) {
            ListNode node = head;
            head = head.next;
            node.next = null;
        }

        if (head == null) {
            return null;
        }

        ListNode prev = head;
        ListNode cur;
        while ((cur = prev.next) != null) {
            if (cur.val == val) {
                prev.next = cur.next;
                cur.next = null;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    public static ListNode reverseList(ListNode head) {
        if (head.next == null) {
            return head;
        }

        ListNode next = head.next;
        head.next = null;
        ListNode res = reverseList(next);
        next.next = head;
        return res;
    }

    public static ListNode reverseListNoCur(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode prev = head;
        ListNode cur = prev.next;
        prev.next = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public static ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode list1 = headA;
        ListNode list2 = headB;
        while (list1 != list2) {
            list1 = list1 == null ? headB : list1.next;
            list2 = list2 == null ? headA : list2.next;
        }
        return list1;
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode middle = middleNode(head);

        ListNode reverseNode = reverseList(middle);

        for (ListNode cur = head; cur != middle; cur = cur.next, reverseNode = reverseNode.next) {
            if (cur.val != reverseNode.val) {
                return false;
            }
        }
        return true;
    }

    public static TreeNode sortedListToBST(ListNode head) {
        return sortedListToBST(head, null);
    }

    private static TreeNode sortedListToBST(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }

        ListNode middle = middleNode(head, tail);
        if (middle != null) {
            TreeNode root = new TreeNode(middle.val);
            root.left = sortedListToBST(head, middle);
            root.right = sortedListToBST(middle.next, tail);
            return root;
        }
        return null;
    }

    private static ListNode middleNode(ListNode head, ListNode tail) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        int[] arr = {-10, -3, 0, 5, 9};
        ListNode l1 = new ListNode(arr);
        TreeNode t1 = sortedListToBST(l1);
        System.out.println(t1);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        ListNode(int[] arr) {
            if (arr == null || arr.length == 0) {
                throw new IllegalArgumentException();
            }
            this.val = arr[0];
            ListNode first = this;
            for (int i = 1; i < arr.length; i++) {
                first.next = new ListNode(arr[i]);
                first = first.next;
            }
        }
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
