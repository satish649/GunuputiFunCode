package com.gunuputi.funcode.trees;

/**
 * Leet code : 563 Binary Tree Tilt
 */
public class BinaryTreeTilt {

    public int findTilt(BTreeNode root) {
        NodeTiltInfo t = findTiltHelper(root);
        return t.totalTilt;
    }

    private NodeTiltInfo findTiltHelper(BTreeNode node) {
        if (node == null) {
            return new NodeTiltInfo(0, 0, 0);
        }

        NodeTiltInfo leftNodeTiltInfo = findTiltHelper(node.left);
        NodeTiltInfo rightNodeTiltInfo = findTiltHelper(node.right);

        int nodeTilt = Math.abs(leftNodeTiltInfo.weight - rightNodeTiltInfo.weight);
        int weight = node.val + leftNodeTiltInfo.weight + rightNodeTiltInfo.weight;
        int totalTilt = nodeTilt + leftNodeTiltInfo.tilt + rightNodeTiltInfo.tilt;

        return new NodeTiltInfo(nodeTilt, weight, totalTilt);
    }

    public static class NodeTiltInfo {
        int tilt;
        int weight;
        int totalTilt;

        public NodeTiltInfo(int tilt, int weight, int totalTilt) {
            this.tilt = tilt;
            this.weight = weight;
            this.totalTilt = totalTilt;
        }
    }

    public static void main(String[] args) {
        BinaryTreeTilt binaryTreeTilt = new BinaryTreeTilt();
        binaryTreeTilt.sampleTest1();
        binaryTreeTilt.sampleTest2();
        binaryTreeTilt.sampleTest3();
        binaryTreeTilt.sampleTest4();
        binaryTreeTilt.sampleTest5();
    }

    private void sampleTest1() {
        /*
         *         null    --> root
         */
        System.out.println("Test 1: Null root");
        System.out.println("Total Tilt Of Tree: [" + findTilt(null) + "]");
        System.out.println("------------------------------------------");
    }

    private void sampleTest2() {
        /*
         *         8    --> root
         */
        BTreeNode root = new BTreeNode(8);

        System.out.println("Test 2: Only one Vertex");
        System.out.println("Total Tilt Of Tree: [" + findTilt(root) + "]");
        System.out.println("------------------------------------------");
    }

    private void sampleTest3() {
        /*
         *         8    --> root
         *        /
         *       3
         */
        BTreeNode root = new BTreeNode(8);
        root.left = new BTreeNode(3);

        System.out.println("Test 1: Tree with only left child");
        System.out.println("Total Tilt Of Tree: [" + findTilt(root) + "]");
        System.out.println("------------------------------------------");
    }

    private void sampleTest4() {
        /*
         *         8    --> root
         *        /
         *       3
         *      /
         *     1
         */
        BTreeNode root = new BTreeNode(8);
        root.left = new BTreeNode(3);
        root.left.left = new BTreeNode(1);

        System.out.println("Test 4: Tree with two left child");
        System.out.println("Total Tilt Of Tree: [" + findTilt(root) + "]");
        System.out.println("------------------------------------------");
    }

    private void sampleTest5() {
        /*
         *         8    --> root
         *        / \
         *       3   2
         */
        BTreeNode root = new BTreeNode(8);
        root.left = new BTreeNode(3);
        root.right = new BTreeNode(2);

        System.out.println("Test 5: Balanced tree with two child");
        System.out.println("Total Tilt Of Tree: [" + findTilt(root) + "]");
        System.out.println("------------------------------------------");
    }

    private void sampleTest6() {
        /*
        /*
         *         8    --> root
         *        / \
         *       4   9
         *      /   / \
         *     1   2  13
         */
        BTreeNode root = new BTreeNode(8);
        root.left = new BTreeNode(4);
        root.left.left = new BTreeNode(1);

        root.right = new BTreeNode(9);
        root.right.left = new BTreeNode(2);
        root.right.right = new BTreeNode(13);

        System.out.println("Test 5: Balanced tree with two level");
        System.out.println("Total Tilt Of Tree: [" + findTilt(root) + "]");
        System.out.println("------------------------------------------");
    }
}
