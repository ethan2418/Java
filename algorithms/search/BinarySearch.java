package algorithms.search;

import java.util.Arrays;

class BinarySearch {
    static int binarySearch(int[] a, int x) {
        int n = a.length;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (x == a[m]) {
                return m;
            }
            if (x > a[m]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(a));

        System.out.println("4 is at position " + binarySearch(a, 4));
        System.out.println("0 is at position " + binarySearch(a, 0));
        System.out.println("1 is at position " + binarySearch(a, 1));
        System.out.println("8 is at position " + binarySearch(a, 8));
        System.out.println("10 is at position " + binarySearch(a, 10));
    }
}
