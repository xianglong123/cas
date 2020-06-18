package com.cas.dataStructrue.tree.norecursive;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 23:19 2020-06-11
 * @version: V1.0
 * @review: 树的遍历 非递归实现
 * 很棒的博客：https://www.cnblogs.com/xiaolovewei/p/7763867.html
 */
public class TreeReader {

    public void preOrder(TreeNode t) { // 先序遍历
        Stack<TreeNode> s = new Stack<>();
        while (t != null || !s.isEmpty()) {
            while (t != null) {
                System.out.println(t.val);
                s.push(t);
                t = t.left;
            }
            if (!s.isEmpty()) {
                t = s.pop();
                t = t.right;
            }
        }
    }

    public void inOrder(TreeNode t) {   //中序，与先序很像，只是在第二次碰到结点才访问
        Stack<TreeNode> s = new Stack<>();
        while (t != null || !s.isEmpty()) {
            while (t != null) {
                s.push(t);  // 第一次碰到不访问，只保存
                t = t.left;
            }
            if (!s.isEmpty()) {
                t = s.pop();
                System.out.println(t.val); // 左子树访问完了，弹出父结点，第二次碰到访问
                t = t.right;    // 准备访问右子树
            }
        }
    }

    public void lastOrder(TreeNode t) { // 后序与上述两种方式有区别，只有左右结点都被访问，当前结点才能被访问
        TreeNode preNode = null;
        Stack<TreeNode> s = new Stack<>();
        s.push(t);
        while (!s.isEmpty()) {
            TreeNode current = s.peek(); // 获取栈顶元素
            if ((current.left == null && current.right == null) || (preNode != null && (preNode == current.left || preNode == current.right))) {
                System.out.println(current.val);
                s.pop();
                preNode = current; // 每访问一个结点都记录一下，方便下次判断结点的左右汉子是否已被访问
            } else {
                if (current.right != null) {
                    s.push(current.right);
                }
                if (current.left != null) {
                    s.push(current.left);
                }
            }
        }
    }

    /**
     * 广度优先遍历
     */
    public void rangeOrder(TreeNode t) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(t);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left != null)
                queue.add(poll.left);
            if (poll.right != null)
                queue.add(poll.right);
            System.out.println(poll.val + ", ");
        }


    }


}
