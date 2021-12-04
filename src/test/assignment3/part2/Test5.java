package test.assignment3.part2;

import assignment3.part2.CleverSIDCImpl;

// This class test the function of LinkedList
public class Test5 {

    public static void main(String[] args) {
        CleverSIDCImpl cleverSIDC = new CleverSIDCImpl();
        cleverSIDC.setSIDCThreshold(1000);
        System.out.println("================");
        System.out.println("test insertion");
        Tool.loadFromFile(cleverSIDC, "src/assignment3/NASTA_test_files/NASTA_test_file3.txt");

        Tool.printKeys(cleverSIDC.allKeys());

        System.out.println("==============");
        System.out.println("test generate");
        for (int i = 0; i < 5; i++) {
            int key = cleverSIDC.generate();
            System.out.println("generate key is " + key);
            Tool.printValue(cleverSIDC, key);
        }

        System.out.println("================");
        System.out.println("test getValue");
        Tool.printValue(cleverSIDC, 99995476);
        Tool.printValue(cleverSIDC, 99998162);
        Tool.printValue(cleverSIDC, 99998627);

        System.out.println("==============");
        System.out.println("test remove");
        Tool.remove(cleverSIDC, 99995476);
        Tool.remove(cleverSIDC, 99998162);
        Tool.remove(cleverSIDC, 99998627);
        Tool.printValue(cleverSIDC, 99995476);
        Tool.printValue(cleverSIDC, 99998162);
        Tool.printValue(cleverSIDC, 99998627);

        System.out.println("==============");
        System.out.println("test nextKey");
        Tool.printNextKey(cleverSIDC, 99986796);
        Tool.printNextKey(cleverSIDC, 99989947);
        Tool.printNextKey(cleverSIDC, 99993932);
        Tool.printNextKey(cleverSIDC, 99995170);


        System.out.println("==============");
        System.out.println("test prevKey");
        Tool.printPrevKey(cleverSIDC, 99986796);
        Tool.printPrevKey(cleverSIDC, 99989947);
        Tool.printPrevKey(cleverSIDC, 99993932);
        Tool.printPrevKey(cleverSIDC, 99995170);


        System.out.println("==============");
        System.out.println("test rangeKey");
        Tool.printRangeKey(cleverSIDC, 99986796, 99995170);
        Tool.printRangeKey(cleverSIDC, 1, (int)Math.pow(10, 8) - 1);
    }
}
