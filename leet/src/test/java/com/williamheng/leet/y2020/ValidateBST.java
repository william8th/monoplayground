package com.williamheng.leet.y2020;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ValidateBST {

    @Test
    public void test() {
        TreeNode n1 = TreeNode.of(1);
        TreeNode n3 = TreeNode.of(3);
        TreeNode n2 = new TreeNode(2, n1, n3);

        assertThat(isValidBST(n2), equalTo(true));
        assertThat(isValidBST(n1), equalTo(true));
    }

    @Test
    public void test2() {
        TreeNode n3 = TreeNode.of(3);
        TreeNode n6 = TreeNode.of(6);
        TreeNode n4 = new TreeNode(4, n3, n6);

        TreeNode n1 = TreeNode.of(1);
        TreeNode n5 = new TreeNode(5, n1, n4);

        assertThat(isValidBST(n5), equalTo(false));
    }

    @Test
    public void test3() {
        TreeNode n11 = TreeNode.of(1);
        TreeNode n12 = new TreeNode(1, n11, null);

        assertThat(isValidBST(n12), equalTo(false));
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    boolean isValidBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        static TreeNode of(int val) {
            return new TreeNode(val);
        }
    }
}
