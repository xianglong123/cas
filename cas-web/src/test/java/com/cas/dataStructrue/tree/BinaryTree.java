package com.cas.dataStructrue.tree;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 19:59 2020-06-11
 * @version: V1.0
 * @review:
 */
public class BinaryTree {
    private BinaryTreeNode[] btn;
    private BinaryTreeNode rootNode;
    private int nodeSize;

    public BinaryTree(int[] arrayNode) {
        nodeSize = arrayNode.length;
        btn = new BinaryTreeNode[nodeSize];

        // 把arrayNode元素转化为节点
        for (int i = 0; i < nodeSize; i++) {
            btn[i] = new BinaryTreeNode();
            btn[i].setBinaryTreeNode(arrayNode[i]);
            if (i == 0) {
                rootNode = btn[i];
            }
        }

        // 把二叉树的左右子树节点补全
        for (int j = 0; j <= (nodeSize - 2) / 2; j++) {
            btn[j].setLeftNode(btn[2 * j + 1]);
            btn[j].setRightNode(btn[2 * j + 2]);
        }
    }

    // 递归方法前序遍历
    public void preOrder(BinaryTreeNode btn) {
        BinaryTreeNode root = btn;
        if (root != null) {
            printNode(root);
            preOrder(root.leftNode);
            preOrder(root.rightNode);
        }
    }

    // 递归方法中序遍历
    void inOrder(BinaryTreeNode btn) {
        BinaryTreeNode root = btn;

        if (root != null) {
            inOrder(root.leftNode);
            printNode(root);
            inOrder(root.rightNode);
        }
    }

    //递归方法后序遍历
    void postOrder(BinaryTreeNode btn) {
        BinaryTreeNode root = btn;

        if (root != null) {
            postOrder(root.leftNode);
            postOrder(root.rightNode);
            printNode(root);
        }
    }

    //打印节点信息
    static void printNode(BinaryTreeNode btn) {
        int a = btn.data;
        System.out.print(a + "，");
    }

    public static void main(String[] args) {
        int[] arrayNode = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        BinaryTree bt = new BinaryTree(arrayNode);
        System.out.println("inOrder:");
        bt.inOrder(bt.rootNode);
        System.out.println("\npreOrder:");
        bt.preOrder(bt.rootNode);
        System.out.println("\npostOrder:");
        bt.postOrder(bt.rootNode);
    }

}
