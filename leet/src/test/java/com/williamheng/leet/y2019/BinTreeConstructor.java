package com.williamheng.leet.y2019;

import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class BinTreeConstructor {

    @Test
    public void testBinTree() {
        TreeNode treeNode = buildTree(
                new int[]{3, 9, 20, 15, 7},
                new int[]{9, 3, 15, 20, 7}
        );

        TreeNode expectedTree = new TreeNode(3);
        expectedTree.left = new TreeNode(9);
        expectedTree.right = new TreeNode(20);
        expectedTree.right.left = new TreeNode(15);
        expectedTree.right.right = new TreeNode(7);

        assertThat(expectedTree, equalTo(expectedTree)); // Test that equals method works
        assertThat(treeNode, equalTo(expectedTree));
    }

    @Test
    public void testTwoElements() {
        TreeNode treeNode = buildTree(
                new int[]{1, 2},
                new int[]{1, 2}
        );

        TreeNode expectedTree = new TreeNode(1);
        expectedTree.left = new TreeNode(2);

        assertThat(treeNode, equalTo(expectedTree));
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder.length == 0) return null;

//        int currentValue = preorder[0];
        int currentValue = getCurrentValue(preorder, inorder);

        TreeNode currentNode = new TreeNode(currentValue);

        if (preorder.length == 1) return currentNode;

        int currentValueIndex = indexOf(inorder, currentValue);
        if (currentValueIndex != -1) {
//            int[] leftPreorder = Arrays.copyOfRange(preorder, 1, preorder.length);
//            int[] rightPreorder = Arrays.copyOfRange(preorder, 2, preorder.length);
            int[] nextPreorder = Arrays.copyOfRange(preorder, 1, preorder.length);
            int[] leftTreeInorder = Arrays.copyOfRange(inorder, 0, currentValueIndex);
            int[] rightTreeInorder = Arrays.copyOfRange(inorder, currentValueIndex + 1, inorder.length);
            currentNode.left = buildTree(nextPreorder, leftTreeInorder);
            currentNode.right = buildTree(nextPreorder, rightTreeInorder);
        }

        return currentNode;
    }

    static int indexOf(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    static int getCurrentValue(int[] preorder, int[] inorder) {
        for (int i : preorder) {
            for (int j : inorder) {
                if (i == j) return i;
            }
        }

        throw new RuntimeException("Unable to find element");
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
