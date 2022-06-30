package data_structures.List;

import java.util.Collection;
import java.util.NoSuchElementException;

class LinkedList<E> {

    static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    LinkedList() {
    }

    LinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
    }

    E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return element;
    }

    E unlinkLast(Node<E> l) {
        // assert l == last && l != null;
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return element;
    }

    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    E getFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
    }

    E getLast() {
        final Node<E> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return l.item;
    }

    E removeFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }

    E removeLast() {
        final Node<E> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return unlinkLast(l);
    }

    void addFirst(E e) {
        linkFirst(e);
    }

    void addLast(E e) {
        linkLast(e);
    }

    boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    int size() {
        return size;
    }

    boolean add(E e) {
        linkLast(e);
        return true;
    }

    boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    boolean addAll(int index, Collection<? extends E> c) {
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        }

        Node<E> pred, succ;
        if (index == size) {
            succ = null;
            pred = last;
        } else {
            succ = node(index);
            pred = succ.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null) {
                first = newNode;
            } else {
                pred.next = newNode;
            }
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }

        size += numNew;
        return true;
    }

    void clear() {
        // Clearing all of the links between nodes is "unnecessary", but:
        // - helps a generational GC if the discarded nodes inhabit
        //   more than one generation
        // - is sure to free memory even if there is a reachable Iterator
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    void add(int index, E element) {
        checkPositionIndex(index);

        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
    }

    E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (x.item == null) {
                    return index;
                }
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (o.equals(x.item)) {
                    return index;
                }
            }
        }
        return -1;
    }

    E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    E element() {
        return getFirst();
    }

    E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    E remove() {
        return removeFirst();
    }

    boolean offer(E e) {
        return add(e);
    }

    boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    E peekFirst() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    E peekLast() {
        final Node<E> l = last;
        return (l == null) ? null : l.item;
    }

    E pollFirst() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    E pollLast() {
        final Node<E> l = last;
        return (l == null) ? null : unlinkLast(l);
    }

    void push(E e) {
        addFirst(e);
    }

    E pop() {
        return removeFirst();
    }

    boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    boolean removeLastOccurrence(Object o) {
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (Node<E> x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }
}
