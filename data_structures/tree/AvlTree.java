package data_structures.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

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

    void print(Node<E> root) {
        List<List<String>> lines = new ArrayList<>();

        List<Node<E>> level = new ArrayList<>();
        List<Node<E>> next = new ArrayList<>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (Node<E> n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = Integer.toString((Integer) n.item);
                    line.add(aa);
                    if (aa.length() > widest) {
                        widest = aa.length();
                    }

                    next.add(n.left);
                    next.add(n.right);

                    if (n.left != null) {
                        nn++;
                    }
                    if (n.right != null) {
                        nn++;
                    }
                }
            }

            if (widest % 2 == 1) {
                widest++;
            }

            lines.add(line);

            List<Node<E>> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) {
                                c = '└';
                            }
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            for (String f : line) {

                if (f == null) {
                    f = "";
                }
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }

    public static void main(String[] args) {
        AvlTree<Integer> avlt = new AvlTree<>();
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            avlt.insert(r.nextInt(100));
        }

        avlt.print(avlt.root);
    }
}