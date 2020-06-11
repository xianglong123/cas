package com.cas.dataStructrue.tree;

import lombok.Data;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 19:55 2020-06-11
 * @version: V1.0
 * @review: 二叉树结点结构
 */
@Data
public class BinaryTreeNode {
    int data;
    BinaryTreeNode leftNode = null, rightNode = null;

    public void setBinaryTreeNode(int data) {
        this.data = data;
    }

    public void setLeftNode(BinaryTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(BinaryTreeNode rightNode) {
        this.rightNode = rightNode;
    }
}
