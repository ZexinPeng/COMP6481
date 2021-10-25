package assignment2.part1.q2;

public class MyStackCase2 {
    private int[] arr;
    private int first;
    private int second;

    public static void main(String[] args) {
        MyStackCase2 ms = new MyStackCase2(4);
        System.out.println(ms.popFirst());
        System.out.println(ms.popSecond());
        ms.pushFirst(1);
        ms.pushFirst(2);
        ms.pushFirst(3);
        ms.pushSecond(4);
        ms.pushSecond(5);
        System.out.println(ms.sizeFirst());
        System.out.println(ms.sizeSecond());
        System.out.println(ms.popFirst());
        System.out.println(ms.popSecond());
        System.out.println(ms.sizeFirst());
        System.out.println(ms.sizeSecond());
    }

    public MyStackCase2(int size) {
        arr = new int[size];
        first = 0;
        second = arr.length - 1;
    }

    public void pushFirst(int element) {
        if (isFull()) {
            System.out.println("stack first is full!");
            return;
        }
        arr[first++] = element;
    }

    public int popFirst() {
        if (isFirstEmpty()) {
            System.out.println("stack first is empty!");
            return -1;
        }
        return arr[--first];
    }

    public int sizeFirst() {
        return first;
    }

    public void pushSecond(int element) {
        if (isFull()) {
            System.out.println("stack second is full!");
            return;
        }
        arr[second--] = element;
    }

    public boolean isFirstEmpty() {
        return first == 0 ? true: false;
    }

    public int popSecond() {
        if (isSecondEmpty()) {
            System.out.println("stack second is empty!");
            return -1;
        }
        return arr[++second];
    }

    public int sizeSecond() {
        return arr.length - second - 1;
    }

    public boolean isSecondEmpty() {
        return second == arr.length - 1? true: false;
    }

    public boolean isFull() {
        return first > second? true: false;
    }
}
