package test.assignment3.part2;

import assignment3.part2.CleverSIDC;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Tool {
    static public void printKeys(int[] keys) {
        System.out.println("==============");
        System.out.println("key sets");
        for (int key: keys) {
            System.out.print(" " + key);
        }
        System.out.println();
    }
    static public void add(CleverSIDC cleverSIDC, int key, String value) {
        cleverSIDC.add(key, value);
        System.out.println("Insertion of element with key " + key + " value " + value);
    }

    static public void remove(CleverSIDC cleverSIDC, int key) {
        cleverSIDC.remove(key);
        System.out.println("Deletion of element with key " + key);
    }


    static public void printValue(CleverSIDC cleverSIDC, int key) {
        String value = cleverSIDC.getValues(key);
        if (value == null) {
            System.out.println("Element " + key +  " does not exist!");
        } else {
            System.out.println("The value with key " + key + " is " + value);
        }
    }

    static public void printNextKey(CleverSIDC cleverSIDC, int key) {
        System.out.println("The next key of " + key + " is " + cleverSIDC.nextKey(key));
    }

    static public void printPrevKey(CleverSIDC cleverSIDC, int key) {
        System.out.println("The prev key of " + key + " is " + cleverSIDC.prevKey(key));
    }

    static public void printRangeKey(CleverSIDC cleverSIDC, int key1, int key2) {
        System.out.println("The number of key between " + key1 + " and " + key2 + " is " + cleverSIDC.rangeKey(key1, key2));
    }

    static public String generateValue(int key) {
        return key + " Zexin Peng 1997/12/04";
    }

    /**
     * load data from test file, add all pairs into sidc
     * @param sidc CleverSIDC
     * @param filePath String
     */
    public static void loadFromFile(CleverSIDC sidc,String filePath) {
        try {
            Scanner scan = new Scanner(new File(filePath));
            while(scan.hasNextLine()) {
                int key=scan.nextInt();
                add(sidc, key, generateValue(key));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (NoSuchElementException e) {
            return;
        }
    }
}
