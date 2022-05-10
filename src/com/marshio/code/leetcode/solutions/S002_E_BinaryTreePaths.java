package com.marshio.code.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author masuo
 * @data 10/5/2022 上午10:28
 * @Description 二叉树的所有路径
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binary-tree-paths/
 */

// Solution 002 Easy
public class S002_E_BinaryTreePaths {

    // 给你一个二叉树的根节点 root ，按 任意顺序 返回所有从根节点到叶子节点的路径。
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> rt = new ArrayList<>();
        getAllPath(root, "", rt);
        return rt;
    }

    private void getAllPath(TreeNode root, String path, List<String> paths) {
        if (root == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(path);
        sb.append(root.val);
        if (isChildNull(root)) {
            // 子节点都为空，到底，为一条路径
            paths.add(sb.toString());
        } else {
            sb.append("->");
            getAllPath(root.left, sb.toString(),paths);
            getAllPath(root.right, sb.toString(),paths);
        }
    }

    private boolean isChildNull(TreeNode root) {
        return root.left == null && root.right == null;
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
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1,new TreeNode(2,null,new TreeNode(5)),new TreeNode(3));

        List<String> list = binaryTreePaths(root);

        for (String s : list) {
            System.out.print(s + " ");
        }
    }
}
