package algorithms.sort;

import java.util.Arrays;

class BubbleSort {
    private static void bubbleSort(int[] a) {
        int n = a.length;
        boolean swapped = true;
        for (int i = 0; i < n - 1 && swapped; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;

                    swapped = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {7, 9, 3, 6, 4, 0, 1, 2, 5, 8};
        System.out.println("before: \n" + Arrays.toString(a));
        bubbleSort(a);
        System.out.println("after: \n" + Arrays.toString(a));
    }
}
