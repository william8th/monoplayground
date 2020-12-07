package com.williamheng.leet.y2020;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MergeTwoLists {

    @Test
    public void test() {
        assertThat(mergeTwoLists(null, null), equalTo(null));

        ListNode l2 = new ListNode(0);
        assertThat(mergeTwoLists(null, l2), equalTo(l2));
        assertThat(mergeTwoLists(l2, null), equalTo(l2));
    }

    @Test
    public void test2() {
        ListNode l11 = ListNode.of(1);
        ListNode l12 = ListNode.of(2);
        ListNode l14 = ListNode.of(4);

        ListNode l21 = ListNode.of(1);
        ListNode l23 = ListNode.of(3);
        ListNode l24 = ListNode.of(4);

        l11.next = l12;
        l12.next = l14;

        l21.next = l23;
        l23.next = l24;

        assertThat(mergeTwoLists(l11, l21), equalTo(l11));
        assertThat(l11.next, equalTo(l21));
        assertThat(l21.next, equalTo(l12));
        assertThat(l12.next, equalTo(l23));
        assertThat(l23.next, equalTo(l14));
        assertThat(l14.next, equalTo(l24));
    }

    public ListNode mergeTwoLists(ListNode first, ListNode second) {
        if (first == null) return second;
        if (second == null) return first;

        ListNode node;
        if (first.val <= second.val) {
            node = first;
            first = first.next;
        } else {
            node = second;
            second = second.next;
        }

        ListNode currNode = node;
        while (first != null || second != null) {
            int v1 = Integer.MAX_VALUE;
            if (first != null) {
                v1 = first.val;
            }

            int v2 = Integer.MAX_VALUE;
            if (second != null) {
                v2 = second.val;
            }

            if (v1 <= v2) {
                currNode.next = first;
                first = first.next;
            } else {
                currNode.next = second;
                second = second.next;
            }
            currNode = currNode.next;
        }

        return node;
    }

    public static class ListNode {
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

        @Override
        public String toString() {
            return String.valueOf(val);
        }

        public static ListNode of(int val) {
            return new ListNode(val);
        }
    }
}
