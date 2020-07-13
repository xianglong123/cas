package com.cas.owner.dataStructrue.tree.norecursive;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 23:40 2020-06-11
 * @version: V1.0
 * @review:
 */
public class Psvm {


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        treeNode.left = left;
        treeNode.right = right;
        TreeReader treeReader = new TreeReader();
        treeReader.preOrder(treeNode);
        treeReader.inOrder(treeNode);
        treeReader.lastOrder(treeNode);
        treeReader.rangeOrder(treeNode);




    }
}
