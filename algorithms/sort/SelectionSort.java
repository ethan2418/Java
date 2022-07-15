package algorithms.sort;

import java.util.Arrays;
import java.util.Random;

class SelectionSort {
    static void selectionSort(int[] a) {
        int n = a.length;
        boolean sorted = false;
        for (int size = n; !sorted && size > 1; size--) {
            int indexOfMax = 0;
            sorted = true;

            for (int i = 1; i < size; i++) {
                if (a[indexOfMax] <= a[i]) {
                    indexOfMax = i;
                } else {
                    sorted = false;
                }
            }
            int t = a[indexOfMax];
            a[indexOfMax] = a[size - 1];
            a[size - 1] = t;
        }
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] a = new int[15];
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);
        }
        System.out.println("before: \n" + Arrays.toString(a));
        selectionSort(a);
        System.out.println("after: \n" + Arrays.toString(a));
    }
}
