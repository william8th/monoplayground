package com.williamheng.leet.y2020;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ModeInBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    @Test
    public void test() {
        TreeNode rightNode = new TreeNode(2, new TreeNode(2), null);
        TreeNode root = new TreeNode(1, null, rightNode);
        assertThat(findMode(root), equalTo(new int[]{2}));
    }

    public int[] findMode(TreeNode root) {
        return null;
    }

    class NodeFrequencies {
        Map<Integer, Integer> frequency = new HashMap<>();

        void increment(int value) {
            if (frequency.containsKey(value)) {
                frequency.put(value, frequency.get(value)+1);
            }
        }

        int maxFrequency() {
            return frequency.values().stream().max(Integer::compareTo).orElse(0);
        }
    }

    NodeFrequencies dfs(TreeNode node) {
//        NodeFrequencies nfLeft = dfs(node.left);
//        NodeFrequencies nfRight = dfs(node.right);

//        nfLeft.increment(node.val);
        return null;
    }

}
