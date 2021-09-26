package assignment1.part2.Q2;

import java.util.Scanner;

public class Scan {
    private static Scanner scanner;

    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void reset() {
        scanner = new Scanner(System.in);
    }
}
