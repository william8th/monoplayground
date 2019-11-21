package com.williamheng.leet;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddTwoNumbers {

    @Test
    public void test() {

        ListNode first1 = new ListNode(2);
        ListNode first2 = new ListNode(4);
        ListNode first3 = new ListNode(3);
        first1.next = first2;
        first2.next = first3;

        ListNode second1 = new ListNode(5);
        ListNode second2 = new ListNode(6);
        ListNode second3 = new ListNode(4);
        second1.next = second2;
        second2.next = second3;

        ListNode expected1 = new ListNode(7);
        ListNode expected2 = new ListNode(0);
        ListNode expected3 = new ListNode(8);
        expected1.next = expected2;
        expected2.next = expected3;

        assertThat(addTwoNumbers(first1, second1), equalTo(expected1));
    }

    @Test
    public void test1() {
        ListNode expected = new ListNode(0);
        expected.next = new ListNode(1);
        assertThat(addTwoNumbers(new ListNode(5), new ListNode(5)), equalTo(expected));
    }

    @Test
    public void test2() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(8);
        assertThat(addTwoNumbers(l1, new ListNode(0)), equalTo(l1));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers(false, l1, l2);
    }

    private ListNode addTwoNumbers(boolean carry, ListNode l1, ListNode l2) {
        if (l1 != null || l2 != null) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int carryVal = carry ? 1 : 0;
            int sum = val1 + val2 + carryVal;

            ListNode l1Next = l1 != null ? l1.next : null;
            ListNode l2Next = l2 != null ? l2.next : null;

            if (sum >= 10) {
                ListNode current = new ListNode(sum - 10);
                current.next = addTwoNumbers(true, l1Next, l2Next);
                return current;
            } else {
                ListNode current = new ListNode(sum);
                current.next = addTwoNumbers(false, l1Next, l2Next);
                return current;
            }
        } else {
            if (carry) {
                return new ListNode(1);
            }

            return null;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ListNode listNode = (ListNode) o;

            if (val != listNode.val) return false;
            return next != null ? next.equals(listNode.next) : listNode.next == null;
        }

        @Override
        public int hashCode() {
            int result = val;
            result = 31 * result + (next != null ? next.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
