package algorithms.sort;

import java.util.Arrays;

class SelectionSort {
    private static void selectionSort(int[] a) {
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
        int[] a = {7, 9, 3, 6, 4, 0, 1, 2, 5, 8};
        System.out.println("before: \n" + Arrays.toString(a));
        selectionSort(a);
        System.out.println("after: \n" + Arrays.toString(a));
    }
}
