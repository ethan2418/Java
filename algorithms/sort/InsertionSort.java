package algorithms.sort;

import java.util.Arrays;
import java.util.Random;

class InsertionSort {
    static void insertionSort(int[] a) {
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
        Random r = new Random();
        int[] a = new int[15];
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);
        }
        System.out.println("before: \n" + Arrays.toString(a));
        insertionSort(a);
        System.out.println("after: \n" + Arrays.toString(a));
    }
}
