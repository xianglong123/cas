package com.cas.owner.dataStructrue.tree.norecursive;

import lombok.Data;

@Data
public class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    public TreeNode(int val) {
        this.val = val;
    }
}