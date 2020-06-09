package com.study.algorithm.linkedlist;

/**
 * @author yjx
 * @date 2020/6/3 0003 21:38
 * @description
 */
public class Solution {

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        return reverse(head);
    }

    private ListNode reverse(ListNode node) {
        if (node.next == null) {
            return node;
        }

        ListNode res = reverseList(node.next);
        node.next.next = node;
        node.next = null;
        return res;
    }

    private ListNode reverseNoneCur(ListNode head) {
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

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
