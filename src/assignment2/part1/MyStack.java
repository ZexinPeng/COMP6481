package assignment2.part1;

import java.util.LinkedList;

public class MyStack {
    private int[] arr;
    private int first;
    private int second;

    public static void main(String[] args) {
        MyStack ms = new MyStack(4);
        ms.pushFirst(1);
        System.out.println(ms.sizeFirst());
        ms.pushFirst(2);
        ms.pushFirst(3);
        System.out.println(ms.sizeFirst());
        ms.popFirst();
        System.out.println(ms.sizeFirst());
        ms.popFirst();
        ms.popFirst();
        System.out.println(ms.sizeFirst());

        ms.pushSecond(1);
        System.out.println(ms.sizeSecond());
        ms.pushSecond(2);
        ms.pushSecond(3);
        System.out.println(ms.sizeSecond());
        ms.popSecond();
        System.out.println(ms.sizeSecond());
        ms.popSecond();
        ms.popSecond();
        System.out.println(ms.sizeSecond());
    }

    public MyStack(int size) {
        arr = new int[size];
        first = arr.length / 2 - 1;
        second = arr.length / 2;
    }

    public void pushFirst(int element) {
        if (isFirstFull()) {
            System.out.println("stack first is full!");
            return;
        }
        arr[first--] = element;
    }

    public int popFirst() {
        if (isFirstEmpty()) {
            System.out.println("stack first is empty!");
            return -1;
        }
        return arr[++first];
    }

    public int sizeFirst() {
        return arr.length / 2 - 1 - first;
    }

    public void pushSecond(int element) {
        if (isSecondFull()) {
            System.out.println("stack second is full!");
            return;
        }
        arr[second++] = element;
    }

    public boolean isFirstEmpty() {
        return first == arr.length / 2 - 1? true: false;
    }

    public boolean isFirstFull() {
        return first == -1 ? true: false;
    }

    public int popSecond() {
        if (isSecondEmpty()) {
            System.out.println("stack second is empty!");
            return -1;
        }
        return arr[--second];
    }

    public int sizeSecond() {
        return second - arr.length / 2;
    }

    public boolean isSecondEmpty() {
        return second == arr.length / 2 ? true: false;
    }

    public boolean isSecondFull() {
        return second == arr.length? true: false;
    }
}
