package com.williamheng.leet.y2019;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class BinaryTreePaths {

    static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {null, null},
                {new TreeNode(6), Arrays.asList("6")},
                {tree1(), Arrays.asList("1->2->5", "1->3")},
                {tree2(), Arrays.asList("10->1->6", "10->11->12")}
        });
    }

    //        1
    //     /   \
    //    2     3
    //     \
    //      5
    private static TreeNode tree1() {
        TreeNode d1lNode = new TreeNode(2);
        d1lNode.right = new TreeNode(5);

        TreeNode d1rNode = new TreeNode(3);

        TreeNode rootNode = new TreeNode(1);
        rootNode.left = d1lNode;
        rootNode.right = d1rNode;

        return rootNode;
    }

    //        10
    //     /    \
    //    1      11
    //     \       \
    //      6       12
    private static TreeNode tree2() {
        TreeNode d1lNode = new TreeNode(1);
        d1lNode.right = new TreeNode(6);

        TreeNode d1rNode = new TreeNode(11);
        d1rNode.right = new TreeNode(12);

        TreeNode rootNode = new TreeNode(10);
        rootNode.left = d1lNode;
        rootNode.right = d1rNode;

        return rootNode;
    }

    @Parameter
    public TreeNode root;

    @Parameter(1)
    public List<String> expected;

    @Test
    public void test() {
        assertThat(binaryTreePaths(root), equalTo(expected));
    }

    List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return null;

        List<String> result = new ArrayList<>();
        dfs(root, null, result);

        return result;
    }

    void dfs(TreeNode node, String path, List<String> result) {
        String curr = Integer.toString(node.val);

        if (path != null) {
            path = path + "->" + curr;
        } else {
            path = curr;
        }

        if (node.left == null && node.right == null)  {
            // Leaf node
            result.add(path);
        }

        if (node.left != null) {
            dfs(node.left, path, result);
        }

        if (node.right != null) {
            dfs(node.right, path, result);
        }
    }

}
