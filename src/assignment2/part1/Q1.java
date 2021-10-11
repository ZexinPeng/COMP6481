package assignment2.part1;

import java.util.Stack;

public class Q1 {
    public static void main(String[] args) {
        new Q1().solution(new int[]{22, 9, 61,61, 61, 21, 0, 9, 9, 9, 9, 35, 81,81, 9, 5, 5});
        new Q1().solution2(new int[]{22, 9, 61,61, 61, 21, 0, 9, 9, 9, 9, 35, 81,81, 9, 5, 5});
    }

    private void solution(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            int times = 1;
            for (int j = i + 1; j < arr.length && arr[j] == arr[i]; j++) {
                times++;
                i = j;
            }
            if (times > 1) {
                System.out.println("Value " + arr[i] + " is repeated " + times + " times starting at Index " + index);
            }
        }
    }

    private void solution2(int[] arr) {
        Stack<Integer> indexStack = new Stack<>();
        Stack<Integer> timesStack = new Stack<>();
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            int times = 1;
            for (int j = i + 1; j < arr.length && arr[j] == arr[i]; j++) {
                times++;
                i = j;
            }
            if (times > 1) {
                indexStack.push(index);
                timesStack.push(times);
            }
        }
        while(!indexStack.empty()) {
            int index = indexStack.pop();
            System.out.println("Value " + arr[index] + " is repeated " + timesStack.pop() + " times starting at Index " + index);
        }

    }
}
