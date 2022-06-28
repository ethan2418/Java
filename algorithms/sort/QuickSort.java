package algorithms.sort;

import java.util.Arrays;

class QuickSort {
    private static int partition(int[] a, int l, int r) {
        int pivot = a[r];
        int i = l - 1;

        for (int j = l; j < r; j++) {

            if (a[j] <= pivot) {
                i++;

                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[i + 1];
        a[i + 1] = a[r];
        a[r] = t;

        return i + 1;
    }

    private static void quickSort(int[] a, int l, int r) {
        if (l < r) {
            int p = partition(a, l, r);
            quickSort(a, l, p - 1);
            quickSort(a, p + 1, r);
        }
    }

    private static void quickSort(int[] a) {
        int n = a.length;
        quickSort(a, 0, n - 1);
    }

    public static void main(String[] args) {
        int[] a = {7, 9, 3, 6, 4, 0, 1, 2, 5, 8};
        System.out.println("before: \n" + Arrays.toString(a));
        quickSort(a);
        System.out.println("after: \n" + Arrays.toString(a));
    }
}