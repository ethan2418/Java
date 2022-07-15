package algorithms.search;

import java.util.Arrays;

class LinearSearch {
    static int linearSearch(int[] a, int x) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {7, 9, 3, 6, 4, 0, 1, 2, 5, 8};
        System.out.println(Arrays.toString(a));

        System.out.println("4 is at position " + linearSearch(a, 4));
        System.out.println("0 is at position " + linearSearch(a, 0));
        System.out.println("1 is at position " + linearSearch(a, 1));
        System.out.println("8 is at position " + linearSearch(a, 8));
        System.out.println("10 is at position " + linearSearch(a, 10));
    }
}
