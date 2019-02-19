package com.gunuputi.funcode.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Leet Code# 431
 */
public class EncodeNArrayTree {

    public BTreeNode encodeDFS(NTreeNode root) {
        if (root == null) {
            return null;
        }

        BTreeNode bTreeNode = BTreeNode.of(root.val);
        BTreeNode tmp = null;
        for (int i = 0; i < root.children.size(); i++) {
            if (i == 0) {
                bTreeNode.left = encodeDFS(root.children.get(i));
                tmp = bTreeNode.left;
            } else {
                tmp.right = encodeDFS(root.children.get(i));
                tmp = tmp.right;
            }
        }

        return bTreeNode;
    }

    public NTreeNode decodeDFS(BTreeNode root) {
        if (root == null) {
            return null;
        }

        NTreeNode nTreeNode = NTreeNode.of(root.val);
        if (root.left != null) {
            nTreeNode.children.add(decodeDFS(root.left));
            BTreeNode tmp = root.left;
            while (tmp.right != null) {
                nTreeNode.children.add(decodeDFS(tmp.right));
                tmp = tmp.right;
            }
        }

        return nTreeNode;

    }

    public BTreeNode encodeBFS(NTreeNode root) {

        if (root == null) {
            return null;
        }
        Queue<NTreeNode> nTreeNodeQueue = new LinkedList<>();
        Queue<BTreeNode> bTreeNodeQueue = new LinkedList<>();

        nTreeNodeQueue.add(root);
        BTreeNode bTreeRootNode = BTreeNode.of(root.val);
        bTreeNodeQueue.add(bTreeRootNode);

        while (!nTreeNodeQueue.isEmpty()) {
            NTreeNode visitedNode = nTreeNodeQueue.poll();
            BTreeNode visitedBTreeNode = bTreeNodeQueue.poll();
            addNTreeNodeChildrenToBTreeNode(visitedNode, visitedBTreeNode, bTreeNodeQueue, nTreeNodeQueue);
        }

        return bTreeRootNode;

    }

    private void addNTreeNodeChildrenToBTreeNode(NTreeNode nTreeNode, BTreeNode bTreeNode,
        Queue<BTreeNode> bTreeNodeQueue,
        Queue<NTreeNode> nTreeNodeQueue) {

        if (nTreeNode.children == null || nTreeNode.children.size() == 0) {
            return;
        }

        // left child
        NTreeNode leftMostChild = nTreeNode.children.get(0);
        nTreeNodeQueue.add(leftMostChild);
        bTreeNode.left = BTreeNode.of(leftMostChild.val);
        bTreeNodeQueue.add(bTreeNode.left);
        BTreeNode tmp = bTreeNode.left;

        // build right child tree
        for (int i = 1; i < nTreeNode.children.size(); i++) {
            NTreeNode childNTreeNode = nTreeNode.children.get(i);
            nTreeNodeQueue.add(childNTreeNode);
            BTreeNode childBTreeNode = BTreeNode.of(childNTreeNode.val);
            bTreeNodeQueue.add(childBTreeNode);
            tmp.right = childBTreeNode;
            tmp = tmp.right;
        }
    }

    public NTreeNode decodeBFS(BTreeNode bTreeRoot) {
        if (bTreeRoot == null) {
            return null;
        }

        Queue<BTreeNode> bTreeNodeQueue = new LinkedList<>();
        Queue<NTreeNode> nTreeNodeQueue = new LinkedList<>();

        bTreeNodeQueue.add(bTreeRoot);
        NTreeNode nTreeRoot = NTreeNode.of(bTreeRoot.val);
        nTreeNodeQueue.add(nTreeRoot);

        while (!bTreeNodeQueue.isEmpty()) {
            BTreeNode visitedBTreeNode = bTreeNodeQueue.poll();
            NTreeNode nTreeNode = nTreeNodeQueue.poll();
            addBTreeNodeChildrenToNTreeNode(visitedBTreeNode, nTreeNode, nTreeNodeQueue, bTreeNodeQueue);
        }

        return nTreeRoot;

    }

    private void addBTreeNodeChildrenToNTreeNode(BTreeNode bTreeNode, NTreeNode nTreeNode,
        Queue<NTreeNode> nTreeNodeQueue, Queue<BTreeNode> bTreeNodeQueue) {

        if (bTreeNode.left == null) {
            return;
        }

        NTreeNode leftMostChild = NTreeNode.of(bTreeNode.left.val);
        nTreeNode.children.add(leftMostChild);
        nTreeNodeQueue.add(leftMostChild);
        bTreeNodeQueue.add(bTreeNode.left);

        BTreeNode tmp = bTreeNode.left.right;
        while (tmp != null) {
            NTreeNode child = NTreeNode.of(tmp.val);
            bTreeNodeQueue.add(tmp);
            nTreeNode.children.add(child);
            nTreeNodeQueue.add(child);
            tmp = tmp.right;
        }
    }

    public static void main(String[] args) {
        EncodeNArrayTree encodeNArrayTree = new EncodeNArrayTree();
        encodeNArrayTree.enccodeTc1();
        encodeNArrayTree.enccodeTc2();
    }

    private void enccodeTc1() {

        NTreeNode nTreeNodeRoot = NTreeNode.of(1);
        for (int i = 2; i <= 5; i++) {
            NTreeNode nTreeNode = NTreeNode.of(i);
            nTreeNodeRoot.children.add(nTreeNode);
        }

        // Level 1 -- node 2
        NTreeNode node2 = nTreeNodeRoot.children.get(0);
        for (int i = 6; i <= 9; i++) {
            NTreeNode nTreeNode = NTreeNode.of(i);
            node2.children.add(nTreeNode);
        }

        // Level 1 -- node 3
        NTreeNode node3 = nTreeNodeRoot.children.get(1);
        for (int i = 10; i <= 12; i++) {
            NTreeNode nTreeNode = NTreeNode.of(i);
            node3.children.add(nTreeNode);
        }

        // Level 1 -- node 4
        NTreeNode node4 = nTreeNodeRoot.children.get(2);

        // Level 1 -- node 5
        NTreeNode node5 = nTreeNodeRoot.children.get(3);
        node5.children.add(NTreeNode.of(13));

        System.out.println("TC1 Results: \n");
        BTreeNode bTreeNodeRoot = encodeBFS(nTreeNodeRoot);
        System.out.println("BFS BTree Node version of NTree Root: ");
        printBTreeNode(bTreeNodeRoot);
        bTreeNodeRoot = encodeDFS(nTreeNodeRoot);
        System.out.println("\nDFS BTree Node version of NTree Root: ");
        printBTreeNode(bTreeNodeRoot);
        System.out.println("\n");

        NTreeNode nTreeNodeTmp = decodeBFS(bTreeNodeRoot);
        System.out.println("BFS NTree Node version of BTree root: ");
        printNTreeNode(nTreeNodeTmp);
        nTreeNodeTmp = decodeDFS(bTreeNodeRoot);
        System.out.println("\nDFS NTree Node version of BTree root: ");
        printNTreeNode(nTreeNodeTmp);

        System.out.println("\n");

    }

    private void printBTreeNode(BTreeNode bTreeNode) {
        if (bTreeNode == null) {
            return;
        }
        System.out.print(bTreeNode.val + ", ");
        printBTreeNode(bTreeNode.left);
        printBTreeNode(bTreeNode.right);
    }

    private void printNTreeNode(NTreeNode nTreeNode) {
        if (nTreeNode == null) {
            return;
        }
        System.out.print(nTreeNode.val + ", ");
        for (NTreeNode node : nTreeNode.children) {
            printNTreeNode(node);
        }
    }

    private void enccodeTc2() {
        System.out.println("TC2 Results: \n");
        BTreeNode bTreeNode = encodeBFS(null);
        if (bTreeNode == null) {
            System.out.println("BTree node is [NULL]\n");
        } else {
            System.out.println("Not able to decodeBFS NULL ntree node! Test Failed");
        }
        NTreeNode nTreeNode = decodeBFS(bTreeNode);
        if (nTreeNode == null) {
            System.out.println("NTree node is [NULL]");
        } else {
            System.out.println("Not able to decodeBFS NULL btree node! Test Failed");
        }

    }
}
