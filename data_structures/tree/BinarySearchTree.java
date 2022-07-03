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
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Random r = new Random();

        for (int i = 0; i < 11; i++) {
            bst.insert(r.nextInt(100));
        }

        BinaryTreePrinter<Integer> printer = new BinaryTreePrinter<>();
        printer.printNode(bst.root);

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