package test.assignment3.part2;

import assignment3.part2.CleverSIDCImpl;

// This class test the function of LinkedList
public class Test4 {

    public static void main(String[] args) {
        CleverSIDCImpl cleverSIDC = new CleverSIDCImpl();
        cleverSIDC.setSIDCThreshold(1000);
        System.out.println("================");
        System.out.println("test insertion");
        Tool.loadFromFile(cleverSIDC, "src/assignment3/NASTA_test_files/NASTA_test_file2.txt");

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
        Tool.printValue(cleverSIDC, 99998162);
        Tool.printValue(cleverSIDC, 99998726);
        Tool.printValue(cleverSIDC, 99998814);

        System.out.println("==============");
        System.out.println("test remove");
        Tool.remove(cleverSIDC, 99998162);
        Tool.remove(cleverSIDC, 99998726);
        Tool.remove(cleverSIDC, 99998814);
        Tool.printValue(cleverSIDC, 99998162);
        Tool.printValue(cleverSIDC, 99998726);
        Tool.printValue(cleverSIDC, 99998814);

//        Tool.printKeys(cleverSIDC.allKeys());

        System.out.println("==============");
        System.out.println("test nextKey");
        Tool.printNextKey(cleverSIDC, 99997242);
        Tool.printNextKey(cleverSIDC, 99997569);
        Tool.printNextKey(cleverSIDC, 99997726);
        Tool.printNextKey(cleverSIDC, 99997901);


        System.out.println("==============");
        System.out.println("test prevKey");
        Tool.printPrevKey(cleverSIDC, 99997242);
        Tool.printPrevKey(cleverSIDC, 99997569);
        Tool.printPrevKey(cleverSIDC, 99997726);
        Tool.printPrevKey(cleverSIDC, 99997901);


        System.out.println("==============");
        System.out.println("test rangeKey");
        Tool.printRangeKey(cleverSIDC, 99997242, 99997901);
        Tool.printRangeKey(cleverSIDC, 1, (int)Math.pow(10, 8) - 1);
    }
}
