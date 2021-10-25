package assignment2.part1.q3;

public class Main {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.max());
        stack.push(4);
        System.out.println(stack.max());
        stack.push(3);
        System.out.println(stack.max());
        stack.push(2);
        System.out.println(stack.max());

        stack.pop();
        System.out.println(stack.max());
        stack.pop();
        System.out.println(stack.max());
        stack.pop();
        System.out.println(stack.max());
        stack.pop();
        System.out.println(stack.max());
        stack.pop();
        System.out.println(stack.max());
        stack.pop();
    }
}
