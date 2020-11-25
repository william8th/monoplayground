package com.williamheng.leet.y2019;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class BinaryTreeLevelOrderTraversal {

    static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    //           3
    //          / \
    //         9  20
    //           /  \
    //          15   7
    private static TreeNode tree1() {
        TreeNode d1lNode = n(9);

        TreeNode d1rNode = n(20);
        d1rNode.left = n(15);
        d1rNode.right = n(7);

        TreeNode rootNode = n(3);
        rootNode.left = d1lNode;
        rootNode.right = d1rNode;

        return rootNode;
    }

    //     4
    //    / \
    //   2   5
    private static TreeNode tree2() {
        TreeNode root = n(4);
        root.left = n(2);
        root.right = n(5);
        return root;
    }

    private static TreeNode n(int x) {
        return new TreeNode(x);
    }

    @Parameters
    public static Collection<Object[]> data() {


        return asList(new Object[][]{
                {null, null},
                {
                        tree2(),
                        asList(
                                singletonList(4),
                                asList(2, 5)
                        )
                },
                {
                        tree1(),
                        asList(
                                singletonList(3),
                                asList(9, 20),
                                asList(15, 7)
                        )
                }
        });
    }

    @Parameter
    public TreeNode root;

    @Parameter(1)
    public List<List<Integer>> expected;

    @Test
    public void test() {
        assertThat(levelOrder(root), equalTo(expected));
    }

    List<List<Integer>> levelOrder(TreeNode node) {
        if (node == null) return null;

        List<List<Integer>> result = new ArrayList<>();
        dfs(node, 0, result);

        return result;
    }

    private void dfs(TreeNode node, int depth, List<List<Integer>> result) {
        List<Integer> level;
        if (depth >= result.size()) {
            level = new ArrayList<>();
            result.add(depth, level);
        } else {
            level = result.get(depth);
        }

        level.add(node.val);

        if (node.left != null) {
            dfs(node.left, depth+1, result);
        }

        if (node.right != null) {
            dfs(node.right, depth+1, result);
        }
    }

}
