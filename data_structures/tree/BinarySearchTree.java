package data_structures.tree;

import java.util.*;

class BinarySearchTree<E extends Comparable<E>> {
    static class Node<E> {
        E item;
        Node<E> left;
        Node<E> right;

        Node(E item) {
            this(item, null, null);
        }

        Node(E item, Node<E> left, Node<E> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    Node<E> root;

    BinarySearchTree() {
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
        return t;
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
        return t;
    }

    void preOrder() {
        preOrder(root);
    }

    void preOrder(Node<E> t) {
        Deque<Node<E>> stack = new LinkedList<>();
        stack.push(t);
        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();
            System.out.print(node.item + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }


    void inOrder() {
        inOrder(root);
    }

    void inOrder(Node<E> t) {
        Node<E> current = t;
        Deque<Node<E>> stack = new LinkedList<>();
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            Node<E> node = stack.pop();
            System.out.print(node.item + " ");
            if (node.right != null) {
                current = node.right;
            }
        }
    }

    void postOrder() {
        postOrder(root);
    }

    void postOrder(Node<E> t) {
        Node<E> current = t;
        Deque<Node<E>> stack = new LinkedList<>();
        stack.push(t);
        while (!stack.isEmpty()) {
            Node<E> peek = stack.peek();
            if (peek.left != null && peek.left != current && peek.right != current) {
                stack.push(peek.left);
            } else if (peek.right != null && peek.right != current) {
                stack.push(peek.right);
            } else {
                System.out.print(stack.pop().item + " ");
                current = peek;
            }
        }
    }

    void topdownLevelOrder() {
        topdownLevelOrder(root);
    }

    void topdownLevelOrder(Node<E> n) {
        Deque<Node<E>> queue = new LinkedList<>();
        queue.add(n);
        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();
            System.out.print(current.item + " ");
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    void bottomUpLevelOrder() {
        bottomUpLevelOrder(root);
    }

    void bottomUpLevelOrder(Node<E> n) {
        Deque<Node<E>> queue = new LinkedList<>();
        Deque<E> stack = new LinkedList<>();
        queue.add(n);
        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();
            stack.push(current.item);
            if (current.right != null) {
                queue.add(current.right);
            }
            if (current.left != null) {
                queue.add(current.left);
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
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
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    E findMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty tree");
        }
        return findMax(root).item;
    }

    Node<E> findMax(Node<E> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }

        return t;
    }

    boolean contains(E x) {
        return contains(x, root);
    }

    boolean contains(E x, Node<E> t) {
        if (t == null) {
            return false;
        }

        int compareResult = x.compareTo(t.item);

        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    void makeEmpty() {
        root = null;
    }

    boolean isEmpty() {
        return root == null;
    }

    int height(Node<E> t) {
        if (t == null) {
            return 0;
        } else {
            return 1 + Math.max(height(t.left), height(t.right));
        }
    }

    void print(Node<E> root) {
        List<List<String>> lines = new ArrayList<>();

        List<Node<E>> level = new ArrayList<>();
        List<Node<E>> next = new ArrayList<>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();

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
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            bst.insert(r.nextInt(100));
        }

        bst.print(bst.root);

        System.out.print("preorder: \n");
        bst.preOrder();
        System.out.println();
        System.out.print("inorder: \n");
        bst.inOrder();
        System.out.println();
        System.out.print("postorder: \n");
        bst.postOrder();
        System.out.println();
        System.out.print("topdown level order: \n");
        bst.topdownLevelOrder();
        System.out.println();
        System.out.print("bottom-up level order: \n");
        bst.bottomUpLevelOrder();
        System.out.println();
    }
}