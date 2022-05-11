package com.marshio.code.nowcoder.solutions;

/**
 * @author masuo
 * @data 10/5/2022 下午5:39
 * @Description 反转链表
 */

public class S001_E_ReverseList {

    public ListNode reverseListI(ListNode head) {
        // 双指针
        ListNode newHead = new ListNode(0);

        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead.next;
    }


    public ListNode reverseListII(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        // 递归
        ListNode rtNode = reverseListII(head.next);
        // 程序走到这里的时候，说明rtNode是最后一个节点
        rtNode.next = head;
        // 断开
        head.next = null;
        return rtNode;
    }


    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
