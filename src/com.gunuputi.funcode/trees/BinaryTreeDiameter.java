package com.gunuputi.funcode.tree;

public class BinaryTreeDiameter {

    public static void main(String[] args) {
        BinaryTreeDiameter diameter = new BinaryTreeDiameter();
        TreeNode root = initializeTreeTC1();
        System.out.println("Diameter of TC1: " + diameter.computeDiameter(root));
        System.out.println("\n");
        root = initializeTreeTC2();
        System.out.println("Diameter of TC2: " + diameter.computeDiameter(root));
        System.out.println("\n");
        root = initializeTreeTC3();
        System.out.println("Diameter of TC3: " + diameter.computeDiameter(root));
        System.out.println("\n");
        root = initializeTreeTC4();
        System.out.println("Diameter of TC4: " + diameter.computeDiameter(root));
        System.out.println("\n");
        root = initializeTreeTC5();
        System.out.println("Diameter of TC5: " + diameter.computeDiameter(root));
        System.out.println("\n");
        root = initializeTreeTC6();
        System.out.println("Diameter of TC6: " + diameter.computeDiameter(root));
        System.out.println("\n");
        root = initializeTreeTC7();
        System.out.println("Diameter of TC7: " + diameter.computeDiameter(root));
        System.out.println("\n");
        root = initializeTreeTC8();
        System.out.println("Diameter of TC8: " + diameter.computeDiameter(root));
        root = initializeTreeTC9();
        System.out.println("Diameter of TC9: " + diameter.computeDiameter(root));
        root = initializeTreeTC10();
        System.out.println("Diameter of TC10: " + diameter.computeDiameter(root));

    }

    public int computeDiameter(TreeNode root) {

        if (root == null) {
            return 0;
        }

        DepthAndDiameterData temp = calculateDiameter(root);

        return temp.diameter;

    }

    private DepthAndDiameterData calculateDiameter(TreeNode node) {
        if (node.left == null && node.right == null) {
            String infoMsg = String.format("Leaf Node [%s], Dep & Dia are [1,0] ", node.val);
            System.out.println(infoMsg);
            return DepthAndDiameterData.newInstance(1, 0);
        }

        DepthAndDiameterData leftData = null;
        if (node.left != null) {
            leftData = calculateDiameter(node.left);
        }
        DepthAndDiameterData rightData = null;
        if (node.right != null) {
            rightData = calculateDiameter(node.right);
        }

        if (node.left != null && node.right != null) {
            int maxDepth = Math.max(leftData.maxDepth, rightData.maxDepth) + 1;
            int diameterTmp = 1 + leftData.maxDepth + rightData.maxDepth;
            int diameter = Math.max(Math.max(leftData.diameter, rightData.diameter), diameterTmp);
            String infoMsg = String
                .format("Non-leaf node [%s], Dep & Dia are [%s,%s]", node.val, maxDepth, diameter);
            System.out.println(infoMsg);
            return DepthAndDiameterData.newInstance(maxDepth, diameter);
        } else if (node.left != null) {
            int maxDepth = leftData.maxDepth + 1;
            String infoMsg = String
                .format("Left Non-Leaf node [%s], Dep & Dia are [%s,%s]", node.val, maxDepth,
                    leftData.diameter);
            System.out.println(infoMsg);
            return DepthAndDiameterData.newInstance(maxDepth, leftData.diameter);
        } else {
            int maxDepth = rightData.maxDepth + 1;
            String infoMsg = String
                .format("Right Non-Leaf node [%s], Dep & Dia are [%s,%s]", node.val, maxDepth,
                    rightData.diameter);
            System.out.println(infoMsg);
            return DepthAndDiameterData.newInstance(maxDepth, rightData.diameter);
        }
    }

    public static class DepthAndDiameterData {
        int maxDepth;
        int diameter;

        public static DepthAndDiameterData newInstance(int maxDepth, int diameter) {
            DepthAndDiameterData diameterTemp = new DepthAndDiameterData();
            diameterTemp.maxDepth = maxDepth;
            diameterTemp.diameter = diameter;
            return diameterTemp;
        }
    }

    private static TreeNode initializeTreeTC1() {

        // level 1
        TreeNode root = TreeNode.newNode(1);

        // level 2
        root.left = TreeNode.newNode(2);
        root.right = TreeNode.newNode(3);

        // level 3
        root.left.left = TreeNode.newNode(4);
        root.left.right = TreeNode.newNode(5);

        root.right.right = TreeNode.newNode(6);

        // level 4
        root.left.right.left = TreeNode.newNode(7);
        root.left.right.right = TreeNode.newNode(8);

        root.right.right.right = TreeNode.newNode(9);

        // level 5
        root.right.right.right.left = TreeNode.newNode(10);
        root.right.right.right.right = TreeNode.newNode(11);

        // level 6
        root.right.right.right.left.left = TreeNode.newNode(12);
        root.right.right.right.left.right = TreeNode.newNode(13);

        return root;
    }

    private static TreeNode initializeTreeTC2() {

        // only root -- expected diameter = 0
        // level 1
        TreeNode root = TreeNode.newNode(1);

        return root;
    }

    private static TreeNode initializeTreeTC3() {

        // there is no secondary leaf (all nodes are on left) -- expected diameter = 0
        // level 1
        TreeNode root = TreeNode.newNode(1);

        // level 2
        root.left = TreeNode.newNode(2);

        // level 3
        root.left.left = TreeNode.newNode(3);

        return root;
    }

    private static TreeNode initializeTreeTC4() {

        // there is no secondary leaf (all nodes are on right) -- expected diameter = 0
        // level 1
        TreeNode root = TreeNode.newNode(1);

        // level 2
        root.right = TreeNode.newNode(2);

        // level 3
        root.right.right = TreeNode.newNode(3);

        return root;
    }

    private static TreeNode initializeTreeTC5() {

        // there is no secondary leaf node (root->left->right) -- expected diameter = 0
        // level 1
        TreeNode root = TreeNode.newNode(1);

        // level 2
        root.left = TreeNode.newNode(2);

        // level 3
        root.left.right = TreeNode.newNode(3);

        return root;
    }

    private static TreeNode initializeTreeTC6() {

        // there is no secondary leaf node (root->left->right) -- expected diameter = 0
        // level 1
        TreeNode root = TreeNode.newNode(1);

        // level 2
        root.right = TreeNode.newNode(2);

        // level 3
        root.right.left = TreeNode.newNode(3);

        return root;
    }

    private static TreeNode initializeTreeTC7() {

        // max diameter without involving root of the tree

        // level 1
        TreeNode root = TreeNode.newNode(1);

        // level 2
        root.left = TreeNode.newNode(2);
        root.right = TreeNode.newNode(3);

        // level 3
        root.left.left = TreeNode.newNode(4);
        root.left.right = TreeNode.newNode(5);

        root.right.right = TreeNode.newNode(6);

        // level 4
        root.left.left.left = TreeNode.newNode(7);
        root.left.left.right = TreeNode.newNode(8);

        root.left.right.right = TreeNode.newNode(9);

        // level 5
        root.left.left.right.left = TreeNode.newNode(10);
        root.left.right.right.left = TreeNode.newNode(11);
        root.left.right.right.right = TreeNode.newNode(12);

        // level 6
        root.left.left.right.left.left = TreeNode.newNode(13);
        root.left.left.right.left.right = TreeNode.newNode(14);
        root.left.right.right.right.right = TreeNode.newNode(15);

        return root;
    }

    private static TreeNode initializeTreeTC8() {

        // max diameter through root but every non-leaf node (other than root) has either left or right nodes --
        // expected diameter : 7

        // level 1
        TreeNode root = TreeNode.newNode(1);

        // level 2
        root.left = TreeNode.newNode(2);
        root.right = TreeNode.newNode(3);

        // level 3
        root.left.left = TreeNode.newNode(4);
        root.right.right = TreeNode.newNode(5);

        // level 4
        root.left.left.right = TreeNode.newNode(6);
        root.right.right.left = TreeNode.newNode(7);

        return root;
    }

    private static TreeNode initializeTreeTC9() {
        // root is null, so diameter is null -- expected diameter : 0
        return null;
    }

    private static TreeNode initializeTreeTC10() {
        // only root node -- expected diameter : 0
        return TreeNode.newNode(1);
    }

}
