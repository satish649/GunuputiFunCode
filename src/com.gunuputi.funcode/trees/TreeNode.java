package com.gunuputi.funcode.tree;

public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int val;

    public TreeNode(int val) {
        this.val = val;
    }

    public static TreeNode newNode(int val) {
        return new TreeNode(val);
    }
}
