package data_structures.stack;

import java.util.NoSuchElementException;
import java.util.Random;

class ArrayStack {
    int[] stack;
    final int initialCapacity = 10;
    int capacity;
    int top;

    ArrayStack() {
        top = -1;
        capacity = initialCapacity;
        stack = new int[capacity];
    }

    ArrayStack(int capacity) {
        top = -1;
        this.capacity = capacity;
        stack = new int[capacity];
    }

    void push(int x) {
        if (!(top + 1 == capacity)) {
            top++;
            stack[top] = x;
        } else {
            resize(capacity * 2);
            push(x);
        }
    }

    int pop() {
        if (!isEmpty()) {
            return stack[top--];
        }
        if (top < capacity / 4) {
            resize(capacity / 2);
            return pop();
        } else {
            throw new NoSuchElementException("empty stack");
        }
    }

    int peek() {
        if (!isEmpty()) {
            return stack[top];
        } else {
            throw new NoSuchElementException("empty stack");
        }
    }

    void resize(int newCapacity) {
        int[] targetStack = new int[newCapacity];

        System.arraycopy(stack, 0, targetStack, 0, stack.length);

        stack = targetStack;
        capacity = newCapacity;
    }

    void show() {
        System.out.println("-----");

        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }

        System.out.println("-----");

    }

    boolean isEmpty() {
        return top == -1;
    }

    int stackSize() {
        return top + 1;
    }

    void makeEmpty() {
        top = -1;
    }

    public static void main(String[] args) {
        Random r = new Random();

        ArrayStack stack = new ArrayStack(5);

        for (int i = 0; i < stack.capacity; i++) {
            stack.push(r.nextInt(100));
        }

        stack.show();

        stack.push(r.nextInt(1000, 2000));
        stack.show();

        stack.pop();
        stack.pop();

        stack.show();

        System.out.println(stack.peek());
    }
}