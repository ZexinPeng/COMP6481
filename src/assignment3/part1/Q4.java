package assignment3.part1;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Q4 {
    public static void main(String[] args) {
        Node node1 = new Node(1, null, null, null, 0);
        Node node2 = new Node(2, node1, null, null, 1);
        Node node4 = new Node(4, null, null, null, 0);
        Node node5 = new Node(5, null, node4, null, -1);
        Node node7 = new Node(7, null, null, null, 0);
        Node node6 = new Node(6, node5, node7, null, 1);
        Node root = new Node(3, node2, node6, null, -1);
        node1.parent = node2;
        node2.parent = root;
        node4.parent = node5;
        node5.parent = node6;
        node7.parent = node6;
        node6.parent = root;
        System.out.println(new Q4().getHeight(root));
    }

    private int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        int height = 1;
        Node node = root;
        while (node.balanceFactor != 0) {
            if (node.balanceFactor == -1) {
                node = node.right;
            } else if (node.balanceFactor == 1) {
                node = node.left;
            }
            height++;
        }
        return height;
    }

    static class Node {
        protected int val;
        protected Node left;
        protected Node right;
        protected Node parent;
        protected int balanceFactor;

        public Node() {

        }

        public Node(int val, Node left, Node right, Node parent, int balanceFactor) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.balanceFactor = balanceFactor;
        }
    }
}
