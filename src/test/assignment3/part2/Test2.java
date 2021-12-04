package test.assignment3.part2;

import assignment3.part2.CleverSIDCImpl;

// This class test the function of LinkedList
public class Test2 {

    public static void main(String[] args) {
        CleverSIDCImpl cleverSIDC = new CleverSIDCImpl();
        cleverSIDC.setSIDCThreshold(1);
        System.out.println("================");
        System.out.println("test insertion");
        Tool.add(cleverSIDC, 10, Tool.generateValue(10));
        Tool.add(cleverSIDC, 9, Tool.generateValue(9));
        Tool.add(cleverSIDC, 8, Tool.generateValue(8));
        Tool.add(cleverSIDC, 7, Tool.generateValue(7));
        Tool.add(cleverSIDC, 6, Tool.generateValue(6));
        Tool.add(cleverSIDC, 5, Tool.generateValue(5));
        Tool.add(cleverSIDC, 4, Tool.generateValue(4));
        Tool.add(cleverSIDC, 3, Tool.generateValue(3));
        Tool.add(cleverSIDC, 2, Tool.generateValue(2));
        Tool.add(cleverSIDC, 1, Tool.generateValue(1));

        System.out.println("================");
        System.out.println("test getValue");
        Tool.printValue(cleverSIDC, 10);
        Tool.printValue(cleverSIDC, 5);
        Tool.printValue(cleverSIDC, 1);

        Tool.printKeys(cleverSIDC.allKeys());

        System.out.println("==============");
        System.out.println("test remove");
        Tool.remove(cleverSIDC, 10);
        Tool.remove(cleverSIDC, 5);
        Tool.remove(cleverSIDC, 1);
        Tool.printValue(cleverSIDC, 10);
        Tool.printValue(cleverSIDC, 5);
        Tool.printValue(cleverSIDC, 1);

        Tool.printKeys(cleverSIDC.allKeys());

        System.out.println("==============");
        System.out.println("test generate");
        for (int i = 0; i < 5; i++) {
            int key = cleverSIDC.generate();
            System.out.println("generate key is " + key);
            Tool.printValue(cleverSIDC, key);
        }

        Tool.printKeys(cleverSIDC.allKeys());
        System.out.println("==============");
        System.out.println("test nextKey");
        Tool.printNextKey(cleverSIDC, 1);
        Tool.printNextKey(cleverSIDC, 4);
        Tool.printNextKey(cleverSIDC, 7);
        Tool.printNextKey(cleverSIDC, 33210);

        System.out.println("==============");
        System.out.println("test prevKey");
        Tool.printPrevKey(cleverSIDC, 4);
        Tool.printPrevKey(cleverSIDC, 8);
        Tool.printPrevKey(cleverSIDC, 10);
        Tool.printPrevKey(cleverSIDC, 6);

        System.out.println("==============");
        System.out.println("test rangeKey");
        Tool.printRangeKey(cleverSIDC, 1, 2);
        Tool.printRangeKey(cleverSIDC, 4, 6);
        Tool.printRangeKey(cleverSIDC, 1, 8);
        Tool.printRangeKey(cleverSIDC, 1, (int)Math.pow(10, 8) - 1);
    }
}
