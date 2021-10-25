package assignment2.part1.q3;

import java.util.Stack;

public class MyStack<Iteger> extends Stack {
    int maxNum = Integer.MIN_VALUE;
    Stack<Integer> maxStack = new Stack<>();
    public int max() {
        return maxStack.peek();
    }

    public int push(int item) {
        if (item > maxNum) {
            maxNum = item;
        }
        addElement(item);
        maxStack.push(maxNum);
        return item;
    }

    public Object pop() {
        int len = size();
        int topElement = (int) peek();
        removeElementAt(len - 1);

        int max = maxStack.pop();

        if (max == maxNum) {
            if (maxStack.size() == 0) {
                maxNum = Integer.MIN_VALUE;
            } else {
                maxNum = maxStack.peek();
            }
        }

        return topElement;
    }
}
