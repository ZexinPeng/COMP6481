package test.assignment3.part2;

import assignment3.part2.LinkedList;

public class LinkedListTest {
    private static final String val = "val";
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.setSIDCThreshold(100);
        list.add(100, val);
        list.add(2330, val);
        list.add(30, val);
        list.add(1, val);
        list.add(33210, val);
        list.add(10, val);
        list.add(8, val);
        list.add(1000, val);


        int[] keys = list.allKeys();
        System.out.println("key sets");
        for (int key: keys) {
            System.out.print(" " + key);
        }
        System.out.println();


        System.out.println("prev key test");
        for (int key: keys) {
            System.out.print(" " + list.prevKey(key));
        }
        System.out.println();

        System.out.println("next key test");
        for (int key: keys) {
            System.out.print(" " + list.nextKey(key));
        }
        System.out.println();

        System.out.println("range key test");
        System.out.println(list.rangeKey(1,8));
        System.out.println(list.rangeKey(0,9));
        System.out.println(list.rangeKey(1,33211));
        System.out.println(list.rangeKey(1,33210));
        System.out.println(list.rangeKey(2,7));
    }
}
