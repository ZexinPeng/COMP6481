package assignment1.part2.Q2;

import java.util.Scanner;

// -----------------------------------------------------
// Assignment 1
// © Sijie Min, Zexin Peng
// Written by: Sijie Min 40152234, Zexin Peng 40166520
// -----------------------------------------------------

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
