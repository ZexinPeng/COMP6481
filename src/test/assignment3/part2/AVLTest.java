package test.assignment3.part2;

import assignment3.part2.AVL;

public class AVLTest {
    private static final String val = "val";
    public static void main(String[] args) {
        AVL cleverSIDC = new AVL();
        cleverSIDC.setSIDCThreshold(100);
        cleverSIDC.add(100, val);
        cleverSIDC.add(2330, val);
        cleverSIDC.add(30, val);
        cleverSIDC.add(1, val);
        cleverSIDC.add(33210, val);
        cleverSIDC.add(10, val);
        cleverSIDC.add(8, val);
        cleverSIDC.add(1000, val);

        cleverSIDC.print();

        int[] keys = cleverSIDC.allKeys();
        System.out.println("key sets");
        for (int key: keys) {
            System.out.print(" " + key);
        }
        System.out.println();

        System.out.println("prev node test");
        AVL.Node node = cleverSIDC.searchTree(cleverSIDC.getRoot(), 33210);
        while (node != null) {
            System.out.print(" " + node.getKey());
            node = cleverSIDC.prevNode(node);
        }
        System.out.println();

        System.out.println("next node test");
        node = cleverSIDC.searchTree(cleverSIDC.getRoot(), 1);
        while (node != null) {
            System.out.print(" " + node.getKey());
            node = cleverSIDC.nextNode(node);
        }
        System.out.println();

        System.out.println("prev key test");
        for (int key: keys) {
            System.out.print(" " + cleverSIDC.prevKey(key + 5));
        }
        System.out.println();

        System.out.println("next key test");
        for (int key: keys) {
            System.out.print(" " + cleverSIDC.nextKey(key - 5));
        }
        System.out.println();

        System.out.println("range key test");
        System.out.println(cleverSIDC.rangeKey(1,8));
        System.out.println(cleverSIDC.rangeKey(0,9));
        System.out.println(cleverSIDC.rangeKey(1,33211));
        System.out.println(cleverSIDC.rangeKey(1,33210));
        System.out.println(cleverSIDC.rangeKey(2,7));
    }
}
