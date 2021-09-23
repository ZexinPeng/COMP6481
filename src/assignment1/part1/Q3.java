package assignment1.part1;

public class Q3 {
    public static void main(String[] args) {
        int[] arr = new int[]{20, 52, 400, 3, 30, 70, 72, 47, 28, 38, 41, 53, 20};
        q3(arr);
    }

    private static void q3(int[] arr) {
        int maxDifference = 0;
        int minDifference = Integer.MAX_VALUE;
        int maxIndex = -1;
        int minIndex = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            int diff = Math.abs(arr[i + 1] - arr[i]);
            if (diff <= minDifference) {
                minIndex = i;
                minDifference = diff;
            }
            else if (diff >= maxDifference) {
                maxIndex = i;
                maxDifference = diff;
            }
        }
        System.out.println("The two conductive indices with smallest difference between their values are: index " + minIndex + " and index " + (minIndex + 1)
                + ", storing values " + arr[minIndex] + " and " + arr[minIndex + 1] + ".");
        System.out.println("The two conductive indices with largest difference between their values are: index " + maxIndex + " and index " + (maxIndex + 1)
                + ", storing values " + arr[maxIndex] + " and " + arr[maxIndex + 1] + ".");
    }
}
