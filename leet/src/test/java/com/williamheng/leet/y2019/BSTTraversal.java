package com.williamheng.leet.y2019;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Stack;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class BSTTraversal {

    //             10
    //          /    \
    //         1      11
    //          \       \
    //           6       12

    static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    class BSTIterator {
        TreeNode root;
        Stack<TreeNode> stack = new Stack<>();

        public BSTIterator(TreeNode root) {
            this.root = root;

            TreeNode node = root;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            TreeNode curr = stack.pop();
            if (curr.right != null) {
                TreeNode node = curr.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }

            return curr.val;
        }
    }

    @Parameters
    public static Collection<Object[]> data() {

        TreeNode d1lNode = new TreeNode(1);
        d1lNode.right = new TreeNode(6);

        TreeNode d1rNode = new TreeNode(11);
        d1rNode.right = new TreeNode(12);

        TreeNode rootNode = new TreeNode(10);
        rootNode.left = d1lNode;
        rootNode.right = d1rNode;

        return Arrays.asList(new Object[][]{
                {null, null},
                {rootNode, new int[]{1, 6, 10, 11, 12}}
        });
    }

    @Parameter
    public TreeNode root;

    @Parameter(1)
    public int[] expected;

    @Test
    public void test() {
        BSTIterator iterator = new BSTIterator(root);

        int i = 0;
        while (iterator.hasNext()) {
            assertThat(iterator.next(), equalTo(expected[i]));
            i++;
        }
    }

}
