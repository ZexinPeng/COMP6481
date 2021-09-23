package assignment1.part1;

import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(q2(sc.next()));
    }

    private static String q2(String str) {
        if (str.length() == 0) {
            return "";
        }
        int maxCount = 0;
        char mostOccurred = str.charAt(0);
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < str.length() - 1; i++) {
            sb.append(str.charAt(i));
            while (i < str.length() - 1 && str.charAt(i + 1) == str.charAt(i)) {
                i++;
                count++;
            }
            if (count > maxCount) {
                maxCount = count;
                mostOccurred = str.charAt(i);
            }
            if (count != 1) {
                sb.append(count);
                if (i == str.length() - 1) {
                    break;
                }
            }
            count = 1;
        }
        if (count == 1) {
            sb.append(str.charAt(str.length() - 1));
        }
        System.out.println("The character that most occurred: " + mostOccurred);
        return sb.toString();
    }
}
