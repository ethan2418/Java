package algorithms.sort;

import java.util.Arrays;
import java.util.Random;

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
        Random r = new Random();
        int[] a = new int[15];
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);
        }
        System.out.println("before: \n" + Arrays.toString(a));
        bubbleSort(a);
        System.out.println("after: \n" + Arrays.toString(a));
    }
}
