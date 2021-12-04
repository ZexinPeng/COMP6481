package test.assignment3.part2;

import assignment3.part2.CleverSIDCImpl;

// This class test the function of LinkedList
public class Test1 {

    public static void main(String[] args) {
        CleverSIDCImpl cleverSIDC = new CleverSIDCImpl();
        System.out.println("================");
        System.out.println("test insertion");
        Tool.add(cleverSIDC, 100, Tool.generateValue(100));
        Tool.add(cleverSIDC, 2330, Tool.generateValue(2330));
        Tool.add(cleverSIDC, 30, Tool.generateValue(30));
        Tool.add(cleverSIDC, 1, Tool.generateValue(1));
        Tool.add(cleverSIDC, 33210, Tool.generateValue(33210));
        Tool.add(cleverSIDC, 10, Tool.generateValue(10));
        Tool.add(cleverSIDC, 8, Tool.generateValue(8));
        Tool.add(cleverSIDC, 1000, Tool.generateValue(1000));

        System.out.println("================");
        System.out.println("test getValue");
        Tool.printValue(cleverSIDC, 10);
        Tool.printValue(cleverSIDC, 8);
        Tool.printValue(cleverSIDC, 1000);

        Tool.printKeys(cleverSIDC.allKeys());

        System.out.println("==============");
        System.out.println("test remove");
        Tool.remove(cleverSIDC, 10);
        Tool.remove(cleverSIDC, 8);
        Tool.remove(cleverSIDC, 1000);
        Tool.printValue(cleverSIDC, 10);
        Tool.printValue(cleverSIDC, 8);
        Tool.printValue(cleverSIDC, 1000);

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
        Tool.printNextKey(cleverSIDC, 4);
        Tool.printNextKey(cleverSIDC, 8);
        Tool.printNextKey(cleverSIDC, 100);
        Tool.printNextKey(cleverSIDC, 33210);

        System.out.println("==============");
        System.out.println("test nextKey");
        Tool.printPrevKey(cleverSIDC, 4);
        Tool.printPrevKey(cleverSIDC, 8);
        Tool.printPrevKey(cleverSIDC, 30);
        Tool.printPrevKey(cleverSIDC, 1);

        System.out.println("==============");
        System.out.println("test rangeKey");
        Tool.printRangeKey(cleverSIDC, 1, 2);
        Tool.printRangeKey(cleverSIDC, 28, 32);
        Tool.printRangeKey(cleverSIDC, 1, 30);
        Tool.printRangeKey(cleverSIDC, -1, (int)Math.pow(10, 9));
    }
}
