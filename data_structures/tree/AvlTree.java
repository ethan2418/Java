package data_structures.tree;

import java.util.*;

class AvlTree<E extends Comparable<E>> {
    static class Node<E> {
        Node(E theElement) {
            this(theElement, null, null);
        }

        Node(E item, Node<E> left, Node<E> right) {
            this.item = item;
            this.left = left;
            this.right = right;
            height = 0;
        }

        E item;
        Node<E> left;
        Node<E> right;
        int height;
    }

    Node<E> root;

    AvlTree() {
        root = null;
    }

    void insert(E x) {
        root = insert(x, root);
    }

    Node<E> insert(E x, Node<E> t) {
        if (t == null) {
            return new Node<>(x, null, null);
        }

        int compareResult = x.compareTo(t.item);

        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        }
        return balance(t);
    }

    void remove(E x) {
        root = remove(x, root);
    }

    Node<E> remove(E x, Node<E> t) {
        if (t == null) {
            return null;
        }

        int compareResult = x.compareTo(t.item);

        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.item = findMin(t.right).item;
            t.right = remove(t.item, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return balance(t);
    }

    static final int ALLOWED_IMBALANCE = 1;

    Node<E> balance(Node<E> t) {
        if (t == null) {
            return null;
        }

        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
            if (height(t.left.left) >= height(t.left.right)) {
                t = rotateWithLeftChild(t);
            } else {
                t = doubleWithLeftChild(t);
            }
        } else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
            if (height(t.right.right) >= height(t.right.left)) {
                t = rotateWithRightChild(t);
            } else {
                t = doubleWithRightChild(t);
            }
        }

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    void checkBalance() {
        checkBalance(root);
    }

    int checkBalance(Node<E> t) {
        if (t == null) {
            return -1;
        }

        int hl = checkBalance(t.left);
        int hr = checkBalance(t.right);
        if (Math.abs(height(t.left) - height(t.right)) > 1 ||
                height(t.left) != hl || height(t.right) != hr) {
            System.out.println("OOPS!!");
        }

        return height(t);
    }

    int height(Node<E> t) {
        return t == null ? -1 : t.height;
    }

    Node<E> rotateWithLeftChild(Node<E> k2) {
        Node<E> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    Node<E> rotateWithRightChild(Node<E> k1) {
        Node<E> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    Node<E> doubleWithLeftChild(Node<E> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    Node<E> doubleWithRightChild(Node<E> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    E findMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty tree");
        }
        return findMin(root).item;
    }

    Node<E> findMin(Node<E> t) {
        if (t == null) {
            return null;
        }
        while (t.left != null) {
            t = t.left;
        }
        return t;
    }

    E findMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty tree");
        }
        return findMax(root).item;
    }

    Node<E> findMax(Node<E> t) {
        if (t == null) {
            return null;
        }
        while (t.right != null) {
            t = t.right;
        }
        return t;
    }

    boolean contains(E x) {
        return contains(x, root);
    }

    boolean contains(E x, Node<E> t) {
        while (t != null) {
            int compareResult = x.compareTo(t.item);

            if (compareResult < 0) {
                t = t.left;
            } else if (compareResult > 0) {
                t = t.right;
            } else {
                return true;
            }
        }
        return false;
    }

    void makeEmpty() {
        root = null;
    }

    boolean isEmpty() {
        return root == null;
    }

    static class BinaryTreePrinter<E> {
        void printNode(Node<E> root) {
            int maxLevel = maxLevel(root);

            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        void printNodeInternal(List<Node<E>> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || BinaryTreePrinter.isAllElementsNull(nodes)) {
                return;
            }

            int floor = maxLevel - level;
            int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            BinaryTreePrinter.printWhitespaces(firstSpaces);

            List<Node<E>> newNodes = new ArrayList<>();
            for (Node<E> node : nodes) {
                if (node != null) {
                    System.out.print(node.item);
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                BinaryTreePrinter.printWhitespaces(betweenSpaces);
            }
            System.out.println();

            for (int i = 1; i <= edgeLines; i++) {
                for (Node<E> node : nodes) {
                    BinaryTreePrinter.printWhitespaces(firstSpaces - i);
                    if (node == null) {
                        BinaryTreePrinter.printWhitespaces(edgeLines + edgeLines + i + 1);
                        continue;
                    }

                    if (node.left != null) {
                        System.out.print("/");
                    } else {
                        BinaryTreePrinter.printWhitespaces(1);
                    }

                    BinaryTreePrinter.printWhitespaces(i + i - 1);

                    if (node.right != null) {
                        System.out.print("\\");
                    } else {
                        BinaryTreePrinter.printWhitespaces(1);
                    }

                    BinaryTreePrinter.printWhitespaces(edgeLines + edgeLines - i);
                }

                System.out.println();
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++) {
                System.out.print(" ");
            }
        }

        int maxLevel(Node<E> node) {
            if (node == null) {
                return 0;
            }
            return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
        }

        static <E> boolean isAllElementsNull(List<E> list) {
            for (Object object : list) {
                if (object != null) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        AvlTree<Integer> avlt = new AvlTree<>();
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            avlt.insert(r.nextInt(100));
        }

        BinaryTreePrinter<Integer> printer = new BinaryTreePrinter<>();
        printer.printNode(avlt.root);
    }
}