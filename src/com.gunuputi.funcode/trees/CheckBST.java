package com.gunuputi.funcode.tree;

public class CheckBST {

    public boolean isBST(TreeNode root) {
        if (root == null) {
            return false;
        }
        BstInfo bstInfo = isBSTHelper(root);
        return bstInfo.isBST;
    }

    private BstInfo isBSTHelper(TreeNode node) {
        if (node.left == null && node.right == null) {
            return new BstInfo(true, node.val, node.val);
        }

        BstInfo leftSubTreeBstInfo = null;
        BstInfo rightSubTreeBstInfo = null;
        if (node.left != null) {
            leftSubTreeBstInfo = isBSTHelper(node.left);
        }
        if (node.right != null) {
            rightSubTreeBstInfo = isBSTHelper(node.right);
        }

        if (leftSubTreeBstInfo != null && rightSubTreeBstInfo != null) {
            boolean isNodeBst = leftSubTreeBstInfo.max <= node.val && rightSubTreeBstInfo.min >= node.val;
            return new BstInfo(leftSubTreeBstInfo.isBST && rightSubTreeBstInfo.isBST && isNodeBst,
                leftSubTreeBstInfo.min, rightSubTreeBstInfo.max);
        } else if (leftSubTreeBstInfo != null && rightSubTreeBstInfo == null) {
            boolean isNodeBst = leftSubTreeBstInfo.max <= node.val;
            return new BstInfo(leftSubTreeBstInfo.isBST && isNodeBst, leftSubTreeBstInfo.min, node.val);
        } else {
            boolean isNodeBst = rightSubTreeBstInfo.min >= node.val;
            return new BstInfo(rightSubTreeBstInfo.isBST && isNodeBst, node.val, rightSubTreeBstInfo.max);
        }
    }

    public static class BstInfo {
        boolean isBST;
        int min;
        int max;

        public BstInfo(boolean isBST, int leftMax, int rightMin) {
            this.isBST = isBST;
            this.min = leftMax;
            this.max = rightMin;
        }
    }

    public static void main(String[] args) {
        CheckBST checkBST = new CheckBST();
        // TC1 expected = true
        checkBST.tc1();
        // TC1 expected = true
        checkBST.tc2();
        checkBST.tc3();
        checkBST.tc4();
        checkBST.tc5();
    }

    private void assertResult(boolean expected, boolean actual, int testCaseId) {
        if (expected == actual) {
            System.out.println("Test case [" + testCaseId + "] passed!");
        } else {
            String errMsg =
                String.format("Test case [%s] FAILED!! Expected value: [%s], Actual Value: [%s]", testCaseId, expected,
                    actual);
            System.out.println(errMsg);
        }
    }

    private void tc1() {

        // level 1
        TreeNode root = TreeNode.newNode(100);

        // level 2
        root.left = TreeNode.newNode(60);
        root.right = TreeNode.newNode(150);

        // level 3
        root.left.left = TreeNode.newNode(50);

        root.right.left = TreeNode.newNode(125);
        root.right.right = TreeNode.newNode(175);

        // level 4
        root.left.left.left = TreeNode.newNode(25);
        root.left.left.right = TreeNode.newNode(55);

        // level 5
        root.left.left.right.left = TreeNode.newNode(52);

        assertResult(true, isBST(root), 1);
    }

    private void tc2() {

        // level 1
        TreeNode root = TreeNode.newNode(100);

        // level 2
        root.left = TreeNode.newNode(60);
        root.right = TreeNode.newNode(150);

        // level 3
        root.left.left = TreeNode.newNode(50);

        root.right.left = TreeNode.newNode(125);
        root.right.right = TreeNode.newNode(175);

        // level 4
        root.left.left.left = TreeNode.newNode(25);
        root.left.left.right = TreeNode.newNode(55);

        // level 5
        root.left.left.right.left = TreeNode.newNode(101);

        assertResult(false, isBST(root), 2);
    }

    private void tc3() {

        // level 1
        TreeNode root = TreeNode.newNode(100);

        // level 2
        root.left = TreeNode.newNode(60);

        // level 3
        root.left.left = TreeNode.newNode(50);

        // level 4
        root.left.left.left = TreeNode.newNode(25);

        assertResult(true, isBST(root), 3);
    }

    private void tc4() {

        // level 1
        TreeNode root = TreeNode.newNode(100);

        // level 2
        root.right = TreeNode.newNode(120);

        // level 3
        root.right.right = TreeNode.newNode(130);

        // level 4
        root.right.right.right = TreeNode.newNode(130);

        assertResult(true, isBST(root), 4);
    }

    private void tc5() {

        // level 1
        TreeNode root = TreeNode.newNode(100);

        // level 2
        root.left = TreeNode.newNode(100);
        root.right = TreeNode.newNode(100);

        // level 3
        root.left.left = TreeNode.newNode(100);

        root.right.left = TreeNode.newNode(100);
        root.right.right = TreeNode.newNode(100);

        // level 4
        root.left.left.left = TreeNode.newNode(100);
        root.left.left.right = TreeNode.newNode(100);

        // level 5
        root.left.left.right.left = TreeNode.newNode(100);

        assertResult(true, isBST(root), 5);
    }

    private void tc6() {
        assertResult(false, isBST(null), 6);
    }
}
