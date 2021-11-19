/* *****************************************************************************
 *  Name: errorfatal89@gmail.com
 *  Date: 2021.11.19
 *  Description: 插入排序
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class InsertionSort {

    public static void sort(Comparable[] a) {
        if (a == null) { // c1 1
            return; // c2 1
        }

        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j].compareTo(a[j-1]) < 0) {
                    exchange(a, j, j-1);
                } else {
                    break;
                }
            }
            for (int k = 0; k < a.length; k++) {
                StdOut.print(a[k] + " ");
            }
            StdOut.println();
        }
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    public static void main(String[] args) {
        Integer[] nums = {7, 1, 0, 3, 2, 5, 4};
        sort(nums);

        for (int i = 0; i < nums.length; i++) {
            StdOut.print(nums[i] + " ");
        }
        StdOut.println();
    }
}
