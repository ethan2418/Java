package algorithms.sort;

import java.util.Arrays;

class InsertionSort {
    private static void insertionSort(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int t = a[i];
            int j;
            for (j = i - 1; j >= 0 && t < a[j]; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = t;
        }
    }

    public static void main(String[] args) {
        int[] a = {7, 9, 3, 6, 4, 0, 1, 2, 5, 8};
        System.out.println("before: \n" + Arrays.toString(a));
        insertionSort(a);
        System.out.println("after: \n" + Arrays.toString(a));
    }
}
