package test.assignment3.part2;

import assignment3.part2.CleverSIDCImpl;

// This class test the function of LinkedList
public class Test3 {

    public static void main(String[] args) {
        CleverSIDCImpl cleverSIDC = new CleverSIDCImpl();
        cleverSIDC.setSIDCThreshold(1000);
        System.out.println("================");
        System.out.println("test insertion");
        Tool.loadFromFile(cleverSIDC, "src/assignment3/NASTA_test_files/NASTA_test_file1.txt");

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
        Tool.printValue(cleverSIDC, 99992825);
        Tool.printValue(cleverSIDC, 99995238);
        Tool.printValue(cleverSIDC, 99997635);

        System.out.println("==============");
        System.out.println("test remove");
        Tool.remove(cleverSIDC, 99992825);
        Tool.remove(cleverSIDC, 99995238);
        Tool.remove(cleverSIDC, 99997635);
        Tool.printValue(cleverSIDC, 99992825);
        Tool.printValue(cleverSIDC, 99995238);
        Tool.printValue(cleverSIDC, 99997635);

//        Tool.printKeys(cleverSIDC.allKeys());

        System.out.println("==============");
        System.out.println("test nextKey");
        Tool.printNextKey(cleverSIDC, 99986617);
        Tool.printNextKey(cleverSIDC, 99986632);
        Tool.printNextKey(cleverSIDC, 99987248);
        Tool.printNextKey(cleverSIDC, 99987554);


        System.out.println("==============");
        System.out.println("test prevKey");
        Tool.printPrevKey(cleverSIDC, 99986617);
        Tool.printPrevKey(cleverSIDC, 99986632);
        Tool.printPrevKey(cleverSIDC, 99987248);
        Tool.printPrevKey(cleverSIDC, 99987554);


        System.out.println("==============");
        System.out.println("test rangeKey");
        Tool.printRangeKey(cleverSIDC, 99986617, 99987554);
        Tool.printRangeKey(cleverSIDC, 1, (int)Math.pow(10, 8) - 1);
    }
}
