package data_structures.queue;

import java.util.NoSuchElementException;
import java.util.Random;

class LinkedQueue<E> {
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

    int size;
    Node<E> first;
    Node<E> last;

    LinkedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    void enqueue(E x) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, x, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    E dequeue() {
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

    E peekFront() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    E peekRear() {
        final Node<E> l = last;
        return (l == null) ? null : l.item;
    }

    void show() {
        System.out.print("rear-> ");
        if (empty()) {
            System.out.println("empty queue");
        } else {
            Node<E> l = last;
            for (int i = size(); i > 0; i--) {
                System.out.print(l.item + " ");
                l = l.prev;
            }
        }
        System.out.print(" <-front");
        System.out.println();
    }

    boolean empty() {
        return size == 0;
    }

    int size() {
        return size;
    }

    public static void main(String[] args) {
        Random r = new Random();

        LinkedQueue<Integer> stack = new LinkedQueue<>();

        for (int i = 0; i < 5; i++) {
            stack.enqueue(r.nextInt(100));
        }

        stack.show();

        stack.enqueue(r.nextInt(1000, 2000));
        stack.show();

        stack.dequeue();
        stack.dequeue();

        stack.show();

        System.out.println(stack.peekFront());
        System.out.println(stack.peekRear());
    }
}
