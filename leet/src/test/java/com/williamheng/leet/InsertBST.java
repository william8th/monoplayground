package com.williamheng.leet;

import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class InsertBST {

    @Test
    public void testBST() {

        TreeNode inputTree = new TreeNode(4);
        inputTree.left = new TreeNode(2);
        inputTree.left.left = new TreeNode(1);
        inputTree.left.right = new TreeNode(3);
        inputTree.right = new TreeNode(7);

        TreeNode expectedTree = new TreeNode(4);
        expectedTree.left = new TreeNode(2);
        expectedTree.left.left = new TreeNode(1);
        expectedTree.left.right = new TreeNode(3);
        expectedTree.right = new TreeNode(7);
        expectedTree.right.left = new TreeNode(5);

        assertThat(
                insertIntoBST(inputTree, 5),
                equalTo(expectedTree)
        );
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        boolean inserted = false;
        TreeNode node = root;
        while (!inserted) {

            if (val > node.val) {
                if (node.right != null) {
                    node = node.right;
                } else {
                    node.right = new TreeNode(val);
                    inserted = true;
                }
            } else {
                if (node.left != null) {
                    node = node.left;
                } else {
                    node.left = new TreeNode(val);
                    inserted = true;
                }
            }
        }

        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TreeNode treeNode = (TreeNode) o;
            return val == treeNode.val &&
                    Objects.equals(left, treeNode.left) &&
                    Objects.equals(right, treeNode.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, left, right);
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
