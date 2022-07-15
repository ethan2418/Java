package algorithms.sort;

import java.util.Arrays;
import java.util.Random;

class HeapSort {
    static void heapify(int[] a, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && a[l] > a[largest]) {
            largest = l;
        }

        if (r < n && a[r] > a[largest]) {
            largest = r;
        }

        if (largest != i) {
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;

            heapify(a, n, largest);
        }
    }

    static void heapSort(int[] a) {
        int n = a.length;
        for (int i = n - 1; i >= 0; i--) {
            heapify(a, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;

            heapify(a, i, 0);
        }
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] a = new int[15];
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);
        }
        System.out.println("before: \n" + Arrays.toString(a));
        heapSort(a);
        System.out.println("after: \n" + Arrays.toString(a));
    }
}
