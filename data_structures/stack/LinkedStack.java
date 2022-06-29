package data_structures.stack;

import java.util.NoSuchElementException;
import java.util.Random;

class LinkedStack<E> {
    static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    int size = 0;
    Node<E> first;
    Node<E> last;

    LinkedStack() {
        first = null;
        last = null;
        size = 0;
    }

    void push(E x) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, x, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    E pop() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return element;
    }

    E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    void show() {
        System.out.println("-----");

        if (empty()) {
            System.out.println("empty stack");
        } else {
            Node<E> f = first;
            for (int i = getSize(); i > 0; i--) {
                System.out.println(f.item);
                f = f.next;
            }
        }

        System.out.println("-----");
    }

    boolean empty() {
        return size == 0;
    }

    int getSize() {
        return size;
    }

    public static void main(String[] args) {
        Random r = new Random();

        LinkedStack<Integer> stack = new LinkedStack<>();

        for (int i = 0; i < 5; i++) {
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
