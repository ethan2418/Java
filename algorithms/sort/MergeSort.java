package algorithms.sort;

import java.util.Arrays;
import java.util.Random;

class MergeSort {
    private static void merge(int[] a, int l, int m, int r) {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] left = new int[n1], right = new int[n2];

        for (i = 0; i < n1; i++) {
            left[i] = a[l + i];
        }
        for (j = 0; j < n2; j++) {
            right[j] = a[m + j + 1];
        }

        i = 0;
        j = 0;
        k = l;
        while (i < n1 || j < n2) {
            if (j >= n2 || (i < n1 && left[i] <= right[j])) {
                a[k] = left[i];
                i++;
            } else {
                a[k] = right[j];
                j++;
            }
            k++;
        }
    }

    private static void mergeSort(int[] a, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(a, l, m);
            mergeSort(a, m + 1, r);
            merge(a, l, m, r);
        }
    }

    private static void mergeSort(int[] a) {
        int n = a.length;
        mergeSort(a, 0, n - 1);
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] a = new int[15];
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);
        }
        System.out.println("before: \n" + Arrays.toString(a));
        mergeSort(a);
        System.out.println("after: \n" + Arrays.toString(a));
    }
}
